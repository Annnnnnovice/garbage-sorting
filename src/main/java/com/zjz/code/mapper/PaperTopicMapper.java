package com.zjz.code.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjz.code.entity.po.PaperTopic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 试卷题目关系表 Mapper 接口
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
public interface PaperTopicMapper extends BaseMapper<PaperTopic> {

    /**
     * 分页查询
     * @param page
     * @param paperTopic
     * @return
     */
    List<PaperTopic> fuzzyQuery(Page<PaperTopic> page, PaperTopic paperTopic);
}
