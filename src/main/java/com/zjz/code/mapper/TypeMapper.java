package com.zjz.code.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjz.code.entity.po.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 类型表 Mapper 接口
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
public interface TypeMapper extends BaseMapper<Type> {

    /**
     * 分页获得文章类型
     * @param page
     * @return
     */
    List<Type> fuzzyQuery(Page<Type> page);
}
