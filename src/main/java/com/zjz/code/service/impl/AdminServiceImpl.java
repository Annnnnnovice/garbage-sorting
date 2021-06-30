package com.zjz.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjz.code.entity.dto.*;
import com.zjz.code.entity.po.*;
import com.zjz.code.entity.vo.ArticleVO;
import com.zjz.code.entity.vo.PageVO;
import com.zjz.code.entity.vo.PaperTopicVO;
import com.zjz.code.entity.vo.RankingVO;
import com.zjz.code.mapper.*;
import com.zjz.code.service.AdminService;
import com.zjz.code.utils.BeanUtil;
import com.zjz.code.utils.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author zjz
 * @description
 * @date 2021-06-06 15:51
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Resource
    TypeMapper typeMapper;

    @Resource
    LabelMapper labelMapper;

    @Resource
    ArticleMapper articleMapper;

    @Resource
    ArticleLabelMapper articleLabelMapper;

    @Resource
    PaperMapper paperMapper;

    @Resource
    TopicMapper topicMapper;

    @Resource
    PaperTopicMapper paperTopicMapper;

    @Resource
    RankingMapper rankingMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Result saveArticle(ArticleSaveDTO articleSaveDTO, HttpServletRequest httpServletRequest) {
        Type type = typeMapper.selectOne(new QueryWrapper<>(new Type(articleSaveDTO.getType()), "id"));
        if (type == null) {
            throw new RuntimeException("类型不存在");
        }
        Article copy = BeanUtil.copy(articleSaveDTO, Article.class);
        String token = httpServletRequest.getHeader("token");
        User user = (User) redisTemplate.opsForValue().get(token);
        String uuid = UUID.randomUUID().toString();
        copy.setId(uuid);
        copy.setAuthor(user.getName());
        copy.setTypeId(type.getId());
        int insert = articleMapper.insert(copy);
        if (insert != 1) {
            throw new RuntimeException("文章插入失败");
        }
        if (articleSaveDTO.getLabel() != null) {
            String[] arr = articleSaveDTO.getLabel().split(",");
            for (int i = 0; i < arr.length; i++) {
                Label label = labelMapper.selectOne(new QueryWrapper<>(new Label(arr[i]), "id"));
                if (label == null) {
                    throw new RuntimeException("专题不存在");
                }
                arr[i] = label.getId();
            }
            for (String s : arr) {
                int insert1 = articleLabelMapper.insert(new ArticleLabel(UUID.randomUUID().toString(), uuid, s));
                if (insert1 == 0) {
                    throw new RuntimeException("文章专题插入失败");
                }
                int update = labelMapper.updateNum(s);
                if (update == 0) {
                    throw new RuntimeException("专题拥有文章数量更新失败");
                }
            }
        }
        return new Result().result200("插入成功", "/admin/article/save");
    }

    @Override
    public Result getArticle(String name, Integer pageNow, Integer pageSize) {
        if (pageNow <= 0) {
            pageNow = 1;
        }
        Page<Article> page = new Page<>(pageNow, pageSize);
        List<Article> articles = articleMapper.fuzzyQuery(page, new Article(name));
        if (articles.size() == 0) {
            return new Result().result404(new ArrayList<>(), "/admin/article/get");
        }
        List<ArticleVO> articleVOList = BeanUtil.batchCopy(articles, ArticleVO.class);
        for (int i = 0; i < articleVOList.size(); i++) {
            List<ArticleLabel> labels = articleLabelMapper.selectList(new QueryWrapper<>(new ArticleLabel(articleVOList.get(i).getId()), "label_id"));
            String[] arr = new String[labels.size()];
            for (int j = 0; j < labels.size(); j++) {
                Label label = labelMapper.selectById(labels.get(j).getLabelId());
                arr[j] = label.getName();
            }
            articleVOList.get(i).setLabel(arr);
            Type type = typeMapper.selectById(articles.get(i).getTypeId());
            articleVOList.get(i).setType(type.getName());
        }
        Integer pageTotal = Math.toIntExact(page.getTotal() / page.getSize());
        if (page.getTotal() % page.getSize() > 0) {
            pageTotal++;
        }
        return new Result().result200(new PageVO<>(pageNow, pageTotal, pageSize, (int) page.getTotal(), articleVOList), "/admin/article/get");
    }

    // 修改的同时要修改数量
    @Override
    public Result updateArticle(ArticleUpdateDTO articleUpdateDTO) {
        Type type = typeMapper.selectOne(new QueryWrapper<>(new Type(articleUpdateDTO.getType()), "id"));
        if (type == null) {
            return new Result().result500("修改失败", "/admin/article/update");
        }
        // 获得专题的id
        String[] arr = articleUpdateDTO.getLabel().split(",");
        for (int i = 0; i < arr.length; i++) {
            Label label = labelMapper.selectOne(new QueryWrapper<>(new Label(arr[i]), "id"));
            if (label == null) {
                return new Result().result500("修改失败", "/admin/article/update");
            }
            arr[i] = label.getId();
        }
        Article copy = BeanUtil.copy(articleUpdateDTO, Article.class);
        copy.setTypeId(type.getId());
        int i = articleMapper.updateById(copy);
        if (i == 0) {
            return new Result().result500("修改失败", "/admin/article/update");
        }
        // 先删再增加
        // 获得该文章没有修改前的所有的专题
        // 删除
        List list = new ArrayList();
        List<ArticleLabel> articleLabels = articleLabelMapper.selectList(new QueryWrapper<>(new ArticleLabel(articleUpdateDTO.getId()), "id", "label_id"));
        for (ArticleLabel articleLabel : articleLabels) {
            int i3 = articleLabelMapper.deleteById(articleLabel.getId());
            if (i3 == 0) {
                return new Result().result500("修改失败", "/admin/article/update");
            }
            int j = labelMapper.reduceNum(articleLabel.getLabelId());
            if (j == 0) {
                return new Result().result500("修改失败", "/admin/article/update");
            }
        }
        // 增加
        for (String s : arr) {
            int insert1 = articleLabelMapper.insert(new ArticleLabel(UUID.randomUUID().toString(), articleUpdateDTO.getId(), s));
            if (insert1 == 0) {
                return new Result().result500("修改失败", "/admin/article/update");
            }
            int i2 = labelMapper.updateNum(s);
            if (i2 == 0) {
                return new Result().result500("修改失败", "/admin/article/update");
            }
        }
        return new Result().result200("修改成功", "/admin/article/update");
        /*// 获得所有的专题和文章的关系列表
        List<ArticleLabel> articleLabels = articleLabelMapper.selectList(new QueryWrapper<>(new ArticleLabel(articleUpdateDTO.getId())));
        if (arr.length == articleLabels.size()) {
            for (int j = 0; j < arr.length; j++) {
                ArticleLabel articleLabel = new ArticleLabel();
                articleLabel.setLabelId(s);
                int update = articleLabelMapper.update(articleLabel, new UpdateWrapper<>(new ArticleLabel(articleUpdateDTO.getId())));
            }
        }
        // 查看那些专题已经有了
        for (String s : arr) {
            ArticleLabel articleLabel = new ArticleLabel();
            articleLabel.setLabelId(s);
            int update = articleLabelMapper.update(articleLabel, new UpdateWrapper<>(new ArticleLabel(articleUpdateDTO.getId())));
            System.out.println(update);
        }*/
    }

    @Override
    public Result removeArticle(List<String> ids) {
        for (String id : ids) {
            List<ArticleLabel> articleLabels = articleLabelMapper.selectList(new QueryWrapper<>(new ArticleLabel(id), "id", "label_id"));
            for (ArticleLabel articleLabel : articleLabels) {
                int i = articleLabelMapper.deleteById(articleLabel.getId());
                if (i == 0) {
                    return new Result().result500("删除失败", "/admin/article/remove");
                }
                int j = labelMapper.reduceNum(articleLabel.getLabelId());
                if (j == 0) {
                    return new Result().result500("删除失败", "/admin/article/remove");
                }
            }
        }
        int i1 = articleMapper.deleteBatchIds(ids);
        if (i1 == 0) {
            return new Result().result500("删除失败", "/admin/article/remove");
        }
        return new Result().result200("删除成功", "/admin/article/remove");
    }

    @Override
    public Result getLabel(String name, Integer pageNow,  Integer pageSize) {
        if (pageNow <= 0) {
            pageNow = 1;
        }
        Page<Label> page = new Page<>(pageNow, pageSize);
        List<Label> labels = labelMapper.fuzzyQuery(page, new Label(name));
        if (labels.size() == 0) {
            return new Result().result404(new ArrayList<>(), "/admin/label/get");
        }
        Integer pageTotal = Math.toIntExact(page.getTotal() / page.getSize());
        if (page.getTotal() % page.getSize() > 0) {
            pageTotal++;
        }
        PageVO<Label> labelPageVO = new PageVO<>(pageNow, pageTotal, pageSize, (int) page.getTotal(), labels);
        return new Result().result200(labelPageVO, "/admin/label/get");
    }

    @Override
    public Result removeLabel(List<String> ids) {
        Map<String, Object> map = new HashMap<>(1);
        ArticleLabel articleLabel = new ArticleLabel();
        for (String id : ids) {
            articleLabel.setLabelId(id);
            Integer id1 = articleLabelMapper.selectCount(new QueryWrapper<>(articleLabel, "id"));
            if (id1 != 0) {
                map.put("label_id", id);
                int i = articleLabelMapper.deleteByMap(map);
                if (i == 0) {
                    return new Result().result500("删除失败", "/admin/label/remove");
                }
            }
        }
        int i1 = labelMapper.deleteBatchIds(ids);
        if (i1 == 0) {
            return new Result().result500("删除失败", "/admin/label/remove");
        }
        return new Result().result200("删除成功", "/admin/label/remove");
    }

    @Override
    public Result getPaper(String name, Integer pageNow, Integer pageSize) {
        if (pageNow <= 0) {
            pageNow = 1;
        }
        Page<Paper> page = new Page<>(pageNow, pageSize);
        List<Paper> papers = paperMapper.fuzzyQuery(page, new Paper(name));
        if (papers.size() == 0) {
            return new Result().result404(new ArrayList<>(), "/admin/paper/get");
        }
        int pageTotal = Math.toIntExact(page.getTotal() / page.getSize());
        if (page.getTotal() % page.getSize() > 0) {
            pageTotal++;
        }
        return new Result().result200(new PageVO<>(pageNow, pageTotal, pageSize, (int)page.getTotal(), papers), "/admin/paper/get");
    }

    @Override
    public Result savePaper(String name) {
        int insert = paperMapper.insert(new Paper(UUID.randomUUID().toString(), name));
        if (insert == 0) {
            return new Result().result500("增加失败", "/admin/paper/save");
        }
        return new Result().result200("增加成功", "/admin/paper/save");
    }

    @Override
    public Result updatePaper(LabelUpdateDTO labelUpdateDTO) {
        int i = paperMapper.updateById(new Paper(labelUpdateDTO.getId(), labelUpdateDTO.getName()));
        if (i == 0) {
            return new Result().result500("修改失败", "/admin/paper/update");
        }
        return new Result().result200("修改成功", "/admin/paper/update");
    }

    @Override
    public Result removePaper(List<String> ids) {
        int i = paperMapper.deleteBatchIds(ids);
        if (i != ids.size()) {
            throw new RuntimeException("试卷删除失败");
        }
        Map<String, Object> map = new HashMap<>(1);
        for (String id : ids) {
            map.put("paper_id", id);
            List<PaperTopic> paperTopics = paperTopicMapper.selectByMap(map);
            if (paperTopics.size() != 0) {
                for (PaperTopic paperTopic : paperTopics) {
                    int j = topicMapper.deleteById(paperTopic.getTopicId());
                    if (j == 0) {
                        throw new RuntimeException("删除题目失败");
                    }
                    int i1 = paperTopicMapper.deleteById(paperTopic.getId());
                    if (i1 != 1) {
                        throw new RuntimeException("删除试卷题目失败");
                    }
                }
            }
        }
        return new Result().result200("删除成功", "/admin/paper/remove");
    }

    @Override
    public Result getTopic(String id, Integer pageNow, Integer pageSize) {
        if (pageNow <= 0) {
            pageNow = 1;
        }
        Page<PaperTopic> page = new Page<>(pageNow, pageSize);
        List<PaperTopic> paperTopics = paperTopicMapper.fuzzyQuery(page, new PaperTopic(id));
        if (paperTopics.size() == 0) {
            return new Result().result404(new ArrayList<>(), "/admin/topic/get");
        }
        List<PaperTopicVO> paperTopicVOList = new ArrayList<>();
        for (PaperTopic paperTopic : paperTopics) {
            Topic topic = topicMapper.selectById(paperTopic.getTopicId());
            PaperTopicVO copy = BeanUtil.copy(topic, PaperTopicVO.class);
            copy.setScore(paperTopic.getScore());
            paperTopicVOList.add(copy);
        }
        int pageTotal = Math.toIntExact(page.getTotal() / page.getSize());
        if (page.getTotal() % page.getSize() > 0) {
            pageTotal++;
        }
        return new Result().result200(new PageVO<>(pageNow, pageTotal, pageSize, (int)page.getTotal(), paperTopicVOList), "/admin/topic/get");
    }

    @Override
    public Result saveTopic(TopicSaveDTO topicSaveDTO) {
        Paper paper = paperMapper.selectById(topicSaveDTO.getPaperId());
        if (paper == null) {
            return new Result().result500("增加失败", "/admin/topic/save");
        }
        Topic copy = BeanUtil.copy(topicSaveDTO, Topic.class);
        String uuid = UUID.randomUUID().toString();
        copy.setId(uuid);
        int insert = topicMapper.insert(copy);
        if (insert == 0) {
            return new Result().result500("增加失败", "/admin/topic/save");
        }
        int insert1 = paperTopicMapper.insert(new PaperTopic(UUID.randomUUID().toString(), topicSaveDTO.getPaperId(), uuid, topicSaveDTO.getScore()));
        if (insert1 == 0) {
            return new Result().result500("增加失败", "/admin/topic/save");
        }
        int insert2 = paperMapper.updateNumAndScore(topicSaveDTO.getScore(), topicSaveDTO.getPaperId());
        return new Result().result200("增加成功", "/admin/topic/save");
    }

    @Override
    public Result updateTopic(TopicUpdateDTO topicUpdateDTO) {
        Integer integer = paperTopicMapper.selectCount(new QueryWrapper<>(new PaperTopic(topicUpdateDTO.getPaperId(), topicUpdateDTO.getId()), "id"));
        if (integer == null || integer == 0) {
            return new Result().result500("修改失败", "/admin/topic/update");
        }
        Topic copy = BeanUtil.copy(topicUpdateDTO, Topic.class);
        int i = topicMapper.updateById(copy);
        if (i == 0) {
            return new Result().result500("修改失败", "/admin/topic/update");
        }
        PaperTopic paperTopic1 = new PaperTopic();
        paperTopic1.setTopicId(topicUpdateDTO.getId());
        PaperTopic paperTopic2 = new PaperTopic();
        paperTopic2.setScore(topicUpdateDTO.getScore());
        int update = paperTopicMapper.update(paperTopic2, new UpdateWrapper<>(paperTopic1));
        if (update == 0) {
            return new Result().result500("修改失败", "/admin/topic/save");
        }
        return new Result().result200("修改成功", "/admin/topic/update");
    }

    @Override
    public Result removeTopic(TopicRemoveDTO topicRemoveDTO) {
        // 删除题目
        List<String> ids = topicRemoveDTO.getIds();
        int i1 = topicMapper.deleteBatchIds(ids);
        if (i1 != ids.size()) {
            throw new RuntimeException("题目删除失败");
        }
        int score = 0;
        Map<String, Object> map = new HashMap<>(1);
        PaperTopic paperTopic = new PaperTopic();
        for (String id : ids) {
            paperTopic.setScore(null);
            paperTopic.setTopicId(id);
            paperTopic = paperTopicMapper.selectOne(new QueryWrapper<>(paperTopic, "score"));
            score += paperTopic.getScore();
            map.put("topic_id", id);
            // 删除试卷题目
            int i = paperTopicMapper.deleteByMap(map);
            if (i != 1) {
                throw new RuntimeException("试卷题目删除失败");
            }
        }
        // 删除试卷的分数和题目数量
        int update = paperMapper.reduceNumAndScore(ids.size(), score, topicRemoveDTO.getPaperId());
        if (update != 1) {
            throw new RuntimeException("试卷分数和题目修改失败");
        }
        return new Result().result200("删除成功", "/admin/topic/remove");
    }

    @Override
    public Result getRanking(String id, Integer pageNow, Integer pageSize) {
        // Map<String, Object> map = new HashMap<>(1);
        // map.put("paper_id", id);
        // List<Ranking> rankings = rankingMapper.selectByMap(map);
        if (pageNow <= 0) {
            pageNow = 1;
        }
        Page<Ranking> page = new Page<>(pageNow, pageSize);
        List<Ranking> rankings = rankingMapper.fuzzyQuery(page, new Ranking(id));
        if (rankings.size() == 0) {
            return new Result().result404(new ArrayList<>(), "/admin/ranking/get");
        }
        List<RankingVO> rankingVOList = BeanUtil.batchCopy(rankings, RankingVO.class);
        int pageTotal = Math.toIntExact(page.getTotal() / page.getSize());
        if (page.getTotal() % page.getSize() > 0) {
            pageTotal++;
        }
        return new Result().result200(new PageVO<RankingVO>(pageNow, pageTotal, pageSize, (int)page.getTotal(), rankingVOList), "/admin/ranking/get");
    }

    @Override
    public Result saveRanking(RankingSaveDTO rankingSaveDTO) {
        Paper paper = paperMapper.selectById(rankingSaveDTO.getPaperId());
        if (paper == null) {
            return new Result().result500("增加失败", "/admin/ranking/save");
        }
        Ranking copy = BeanUtil.copy(rankingSaveDTO, Ranking.class);
        copy.setId(UUID.randomUUID().toString());
        int insert = rankingMapper.insert(copy);
        if (insert != 1) {
            return new Result().result500("增加失败", "/admin/ranking/save");
        }
        return new Result().result200("增加成功", "/admin/ranking/save");
    }

    @Override
    public Result updateRanking(RankingUpdateDTO rankingUpdateDTO) {
        Ranking copy = BeanUtil.copy(rankingUpdateDTO, Ranking.class);
        int i = rankingMapper.updateById(copy);
        if (i != 1) {
            return new Result().result500("修改失败", "/admin/ranking/update");
        }
        return new Result().result200("修改成功", "/admin/ranking/update");
    }

    @Override
    public Result removeRanking(List<String> ids) {
        int i = rankingMapper.deleteBatchIds(ids);
        if (i != ids.size()) {
            throw new RuntimeException("删除排行记录异常");
        }
        return new Result().result200("删除成功", "/admin/ranking/remove");
    }

    @Override
    public Result getType(Integer pageNow, Integer pageSize) {
        if (pageNow <= 0) {
            pageNow = 1;
        }
        Page<Type> page = new Page<>(pageNow, pageSize);
        List<Type> types = typeMapper.fuzzyQuery(page);
        if (types.size() == 0) {
            return new Result().result404(new ArrayList<>(), "/admin/type/get");
        }
        int pageTotal = Math.toIntExact(page.getTotal() / page.getSize());
        if (page.getTotal() % page.getSize() > 0) {
            pageTotal++;
        }
        return new Result().result200(new PageVO<Type>(pageNow, pageTotal, pageSize, (int)page.getTotal(), types), "/admin/type/get");
    }

    @Override
    public Result saveType(LabelSaveDTO labelSaveDTO) {
        int insert = typeMapper.insert(new Type(UUID.randomUUID().toString(), labelSaveDTO.getName()));
        if (insert != 1) {
            return new Result().result500("增加失败", "admin/type/save");
        }
        return new Result().result200("增加成功", "admin/type/save");
    }

    @Override
    public Result updateType(LabelUpdateDTO labelUpdateDTO) {
        Type copy = BeanUtil.copy(labelUpdateDTO, Type.class);
        int i = typeMapper.updateById(copy);
        if (i != 1) {
            return new Result().result500("修改失败", "admin/type/update");
        }
        return new Result().result200("增加成功", "admin/type/update");
    }

    @Override
    public Result removeType(List<String> ids) {
        int i = typeMapper.deleteBatchIds(ids);
        if (i != ids.size()) {
            throw new RuntimeException("删除类型失败");
        }
        Article article = new Article();
        Map<String, Object> map = new HashMap<>(1);
        for (String id : ids) {
            article.setTypeId(id);
            Integer integer = articleMapper.selectCount(new QueryWrapper<>(article));
            if (integer != 0) {
                map.put("type_id", id);
                int i1 = articleMapper.deleteByMap(map);
                if (i1 != integer) {
                    throw new RuntimeException("删除文章失败");
                }
            }
        }
        return new Result().result200("删除成功", "admin/type/remove");
    }
}
