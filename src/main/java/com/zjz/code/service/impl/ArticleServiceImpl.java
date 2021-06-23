package com.zjz.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.zjz.code.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjz.code.utils.BeanUtil;
import com.zjz.code.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    TypeMapper typeMapper;

    @Resource
    ArticleMapper articleMapper;

    @Resource
    ArticleLabelMapper articleLabelMapper;

    @Resource
    LabelMapper labelMapper;

    @Override
    public Result getArticlesByType(String typeName, Integer pageNow, Integer pageSize) {
        Type type = typeMapper.selectOne(new QueryWrapper<>(new Type(typeName), "id"));
        if (type == null) {
            return new Result().result404(new ArrayList<>(), "/article/type/get");
        }
        if (pageNow <= 0) {
            pageNow = 1;
        }
        Page<Article> page = new Page<>(pageNow, pageSize);
        List<Article> articles = articleMapper.selectPageByType(page, type.getId());
        if (articles.size() == 0) {
            return new Result().result404(new ArrayList<>(), "/article/type/get");
        }
        List<ArticleVO> articleVOList = BeanUtil.batchCopy(articles, ArticleVO.class);
        for (ArticleVO articleVO : articleVOList) {
            articleVO.setType(typeName);
        }

        for (int i = 0; i < articleVOList.size(); i++) {
            List<ArticleLabel> labels = articleLabelMapper.selectList(new QueryWrapper<>(new ArticleLabel(articleVOList.get(i).getId()), "label_id"));
            String[] arr = new String[labels.size()];
            for (int j = 0; j < labels.size(); j++) {
                Label label = labelMapper.selectById(labels.get(j).getLabelId());
                arr[j] = label.getName();
            }
            articleVOList.get(i).setLabel(arr);
            articleVOList.get(i).setType(typeName);
        }

        Integer pageTotal = Math.toIntExact(page.getTotal() / page.getSize());
        if (page.getTotal() % page.getSize() > 0) {
            pageTotal++;
        }
        return new Result().result200(new PageVO<>(pageNow, pageTotal, pageSize, (int) page.getTotal(), articleVOList), "/article/type/get");
    }
}
