package com.zjz.code.mapper;

import com.zjz.code.entity.po.ArticleLabel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 文章专题关系表 Mapper 接口
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
public interface ArticleLabelMapper extends BaseMapper<ArticleLabel> {

    /**
     * 分页查询文章的id集合
     * @param id
     * @param begin
     * @param pageSize
     * @return
     */
    List<String> selectByPage(String id, int begin, Integer pageSize);
}
