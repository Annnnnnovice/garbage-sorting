package com.zjz.code.service;

import com.zjz.code.entity.po.Label;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjz.code.utils.Result;

/**
 * <p>
 * 专题表 服务类
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
public interface LabelService extends IService<Label> {

    /**
     * 根据专题名查询文章
     * @param labelName 专题名
     * @param pageNow 当前页面
     * @param pageSize 显示大小
     * @return result类
     */
    Result getLabelArticle(String labelName, Integer pageNow, Integer pageSize);
}
