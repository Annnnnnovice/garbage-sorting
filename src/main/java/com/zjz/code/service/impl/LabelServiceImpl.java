package com.zjz.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjz.code.entity.po.Article;
import com.zjz.code.entity.po.ArticleLabel;
import com.zjz.code.entity.po.Label;
import com.zjz.code.entity.po.Type;
import com.zjz.code.entity.vo.ArticleVO;
import com.zjz.code.entity.vo.PageVO;
import com.zjz.code.mapper.ArticleLabelMapper;
import com.zjz.code.mapper.ArticleMapper;
import com.zjz.code.mapper.LabelMapper;
import com.zjz.code.mapper.TypeMapper;
import com.zjz.code.service.LabelService;
import com.zjz.code.utils.BeanUtil;
import com.zjz.code.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 专题表 服务实现类
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Resource
    ArticleMapper articleMapper;

    @Resource
    LabelMapper labelMapper;

    @Resource
    ArticleLabelMapper articleLabelMapper;

    @Resource
    TypeMapper typeMapper;

    @Override
    public Result getLabelArticle(String labelName, Integer pageNow, Integer pageSize) {
        Label label = labelMapper.selectOne(new QueryWrapper<>(new Label(labelName)));
        Integer pageTotalCount = label.getNumber();
        // 获得总页数
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        // 当输入的页数大于总页数时会返回null
        if (pageNow > pageTotal) {
            return new Result().result404(new ArrayList<>(), "/default/label/get/article");
        }
        // 创建PageVO对像
        PageVO<ArticleVO> pageVO = new PageVO<>(pageTotal, pageSize, pageTotalCount);
        // 设置当前页数
        pageVO.setPageNow(pageNow);
        // 计算出要获得指定页数的第1条的数据-1是第几个
        int begin = (pageVO.getPageNow() - 1) * pageSize;
        List<String> articleIds = articleLabelMapper.selectByPage(label.getId(), begin, pageSize);
        if (articleIds.size() == 0) {
            return new Result().result404(new ArrayList<>(), "/default/label/get/article");
        }
        List<Article> articles = articleMapper.selectBatchIds(articleIds);
        if (articles.size() == 0) {
            return new Result().result404(new ArrayList<>(), "/default/label/get/article");
        }
        List<ArticleVO> articleVOList = BeanUtil.batchCopy(articles, ArticleVO.class);
        Type type = new Type();
        Map<String, Object> map = new HashMap<>(1);
        for (int i = 0; i < articleIds.size(); i++) {
            type.setId(articles.get(i).getTypeId());
            Type type1 = typeMapper.selectOne(new QueryWrapper<>(type));
            articleVOList.get(i).setType(type1.getName());
            map.put("article_id", articles.get(i).getId());
            List<ArticleLabel> articleLabels = articleLabelMapper.selectByMap(map);
            String[] arr = new String[articleLabels.size()];
            for (int j = 0; j < articleLabels.size(); j++) {
                Label label1 = labelMapper.selectById(articleLabels.get(j).getLabelId());
                arr[j] = label1.getName();
            }
            articleVOList.get(i).setLabel(arr);
        }
        pageVO.setItems(articleVOList);
        return new Result().result200(pageVO, "/default/label/get/article");
    }
}
