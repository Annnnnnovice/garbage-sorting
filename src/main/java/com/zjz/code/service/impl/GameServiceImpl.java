package com.zjz.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.zjz.code.entity.dto.GameDTO;
import com.zjz.code.entity.po.Paper;
import com.zjz.code.entity.po.PaperTopic;
import com.zjz.code.entity.po.Ranking;
import com.zjz.code.entity.po.Topic;
import com.zjz.code.entity.vo.GameVO;
import com.zjz.code.entity.vo.TopicVO;
import com.zjz.code.mapper.PaperMapper;
import com.zjz.code.mapper.PaperTopicMapper;
import com.zjz.code.mapper.RankingMapper;
import com.zjz.code.mapper.TopicMapper;
import com.zjz.code.service.GameService;
import com.zjz.code.utils.BeanUtil;
import com.zjz.code.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zjz
 * @description
 * @date 2021-06-06 15:50
 */
@Service
public class GameServiceImpl implements GameService {

    @Resource
    PaperMapper paperMapper;

    @Resource
    PaperTopicMapper paperTopicMapper;

    @Resource
    TopicMapper topicMapper;

    @Resource
    RankingMapper rankingMapper;

    @Override
    public Result getGame() {
        Paper paper = new Paper();
        paper.setRelease(true);
        Paper paper1 = paperMapper.selectOne(new QueryWrapper<>(paper));
        if (paper1 == null) {
            return new Result().result404(new ArrayList<>(), "/game/get");
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put("paper_id", paper1.getId());
        List<PaperTopic> paperTopics = paperTopicMapper.selectByMap(map);
        if (paperTopics.size() == 0) {
            return new Result().result404(new ArrayList<>(), "/game/get");
        }
        List<Topic> topics = new ArrayList<>();
        for (PaperTopic paperTopic : paperTopics) {
            topics.add(topicMapper.selectById(paperTopic.getTopicId()));
        }
        if (topics.size() != paperTopics.size()) {
            return new Result().result404(new ArrayList<>(), "/game/get");
        }
        List<TopicVO> topicVOList = BeanUtil.batchCopy(topics, TopicVO.class);
        return new Result().result200(new GameVO(paper1.getId(), topicVOList), "/game/get");
    }

    @Override
    public Result saveGameRecords(GameDTO gameDTO) {
        AtomicInteger score = new AtomicInteger();
        QueryWrapper<PaperTopic> paperTopicQueryWrapper = new QueryWrapper<>();
        gameDTO.getMap().forEach((k, v) -> {
            Topic topic = topicMapper.selectById(k);
            if (v.equals(topic.getAnswer())) {
                paperTopicQueryWrapper.eq("topic_id", k);
                PaperTopic paperTopic = paperTopicMapper.selectOne(paperTopicQueryWrapper.select("score"));
                score.addAndGet(paperTopic.getScore());
            }
        });
        Paper paper = paperMapper.selectById(gameDTO.getPaperId());
        if (paper == null) {
            return new Result().result500("保存失败", "/ranking/save");
        }
        Ranking ranking = new Ranking(UUID.randomUUID().toString(), gameDTO.getPaperId(), gameDTO.getName(), score.get());
        int insert = rankingMapper.insert(ranking);
        if (insert != 1) {
            return new Result().result500("保存失败", "/game/save");
        }
        return new Result().result200("保存成功", "/game/save");
    }
}
