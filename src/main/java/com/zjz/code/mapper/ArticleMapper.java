package com.zjz.code.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjz.code.entity.po.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 模糊查询
     * @param page
     * @param article
     * @return
     */
    List<Article> fuzzyQuery(Page<Article> page, Article article);

    /**
     * 根据typeId查询
     * @param page
     * @param id
     * @return
     */
    List<Article> selectPageByType(Page<Article> page, String id);
}
