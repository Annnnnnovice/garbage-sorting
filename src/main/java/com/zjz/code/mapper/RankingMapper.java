package com.zjz.code.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjz.code.entity.po.Ranking;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 排行表 Mapper 接口
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
public interface RankingMapper extends BaseMapper<Ranking> {

    /**
     * 分页查询指定试卷的排行榜
     * @param page
     * @param ranking
     * @return
     */
    List<Ranking> fuzzyQuery(Page<Ranking> page, Ranking ranking);
}
