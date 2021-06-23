package com.zjz.code.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjz.code.entity.po.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 试卷表 Mapper 接口
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
public interface PaperMapper extends BaseMapper<Paper> {

    /**
     * 分页模糊查询
     * @param page
     * @param paper
     * @return
     */
    List<Paper> fuzzyQuery(Page<Paper> page, Paper paper);

    /**
     * 新增分数和题目数
     * @param score
     * @param id
     * @return
     */
    int updateNumAndScore(Integer score, String id);

    /**
     * 减少题目数和分数
     * @param num 题目数
     * @param score 分数
     * @param id 主键
     * @return
     */
    int reduceNumAndScore(int num, int score, String id);
}
