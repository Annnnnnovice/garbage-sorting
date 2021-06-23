package com.zjz.code.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjz.code.entity.po.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 专题表 Mapper 接口
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
public interface LabelMapper extends BaseMapper<Label> {

    /**
     * 增加数量
     * @param s
     * @return
     */
    int updateNum(String s);

    /**
     * 减少数量
     * @param id
     * @return
     */
    int reduceNum(String id);

    /**
     * 分页查询
     * @param page
     * @param label
     * @return
     */
    List<Label> fuzzyQuery(Page<Label> page, Label label);
}
