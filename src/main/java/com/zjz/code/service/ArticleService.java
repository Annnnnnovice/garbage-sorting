package com.zjz.code.service;

import com.zjz.code.entity.po.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjz.code.utils.Result;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
public interface ArticleService extends IService<Article> {

    /**
     * 根据类别的文章分页查询
     * @param typeName
     * @param pageNow
     * @param pageSize
     * @return
     */
    Result getArticlesByType(String typeName, Integer pageNow, Integer pageSize);
}
