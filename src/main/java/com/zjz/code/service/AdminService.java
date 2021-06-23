package com.zjz.code.service;

import com.zjz.code.entity.dto.*;
import com.zjz.code.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zjz
 * @description 管理模块业务逻辑层
 * @date 2021-06-06 15:51
 */
public interface AdminService {

    /**
     * 增加文章
     * @param articleSaveDTO 文章的数据传输类
     * @param httpServletRequest
     * @return Result类
     */
    Result saveArticle(ArticleSaveDTO articleSaveDTO, HttpServletRequest httpServletRequest);

    /**
     * 分页模糊查询文章
     * @param name 关键字
     * @param pageNow 当前页
     * @param pageSize 显示数量
     * @return Result类
     */
    Result getArticle(String name, Integer pageNow, Integer pageSize);

    /**
     * 修改文章
     * @param articleUpdateDTO 文章的数据传输类
     * @return Result类
     */
    Result updateArticle(ArticleUpdateDTO articleUpdateDTO);

    /**
     * 删除文章
     * @param ids 文章id集合
     * @return Result类
     */
    Result removeArticle(List<String> ids);

    /**
     * 查询专题
     * @param name 关键词
     * @param pageNow 当前页
     * @param pageSize 显示数量
     * @return Result类
     */
    Result getLabel(String name, Integer pageNow, Integer pageSize);

    /**
     * 删除专题
     * @param ids 专题id集合
     * @return Result类
     */
    Result removeLabel(List<String> ids);

    /**
     * 分页模糊查询试卷
     * @param name 关键词
     * @param pageNow 当前页
     * @param pageSize 显示数量
     * @return Result类
     */
    Result getPaper(String name, Integer pageNow, Integer pageSize);

    /**
     * 增加试卷
     * @param name 试卷名称
     * @return Result类
     */
    Result savePaper(String name);

    /**
     * 修改试卷名
     * @param labelUpdateDTO 试卷或专题的数据传输类
     * @return Result类
     */
    Result updatePaper(LabelUpdateDTO labelUpdateDTO);

    /**
     * 删除试卷
     * @param ids 试卷id集合
     * @return Result类
     */
    Result removePaper(List<String> ids);

    /**
     * 分页查询试卷的题目
     * @param id 试卷id
     * @param pageNow 当前页
     * @param pageSize 显示数量
     * @return Result类
     */
    Result getTopic(String id, Integer pageNow, Integer pageSize);

    /**
     * 增加指定试卷的题目
     * @param topicSaveDTO 指定试卷增加题目的数据传输类
     * @return Result类
     */
    Result saveTopic(TopicSaveDTO topicSaveDTO);

    /**
     * 修改指定试卷的题目
     * @param topicUpdateDTO 指定试卷修改题目的数据传输类
     * @return Result类
     */
    Result updateTopic(TopicUpdateDTO topicUpdateDTO);

    /**
     * 删除指定试卷的题目
     * @param topicRemoveDTO 删除题目的数据传输类
     * @return Result类
     */
    Result removeTopic(TopicRemoveDTO topicRemoveDTO);

    /**
     * 查询指定试卷得分排行榜
     * @param id 试卷id
     * @param pageNow 当前页
     * @param pageSize 显示数量
     * @return Result类
     */
    Result getRanking(String id, Integer pageNow, Integer pageSize);

    /**
     * 增加用户做题记录
     * @param rankingSaveDTO 增加用户答题记录的数据传输类
     * @return Result类
     */
    Result saveRanking(RankingSaveDTO rankingSaveDTO);

    /**
     * 修改用户做题记录
     * @param rankingUpdateDTO 修改用户答题记录的数据传输类
     * @return Result类
     */
    Result updateRanking(RankingUpdateDTO rankingUpdateDTO);

    /**
     * 删除指定试卷的排行记录
     * @param ids 试卷id
     * @return Result类
     */
    Result removeRanking(List<String> ids);

    /**
     * 获得文章类型
     * @param pageNow 当前页
     * @param pageSize 显示数量
     * @return Result类
     */
    Result getType(Integer pageNow, Integer pageSize);

    /**
     * 增加文章类型
     * @param labelSaveDTO 增加文章类型的数据传输类
     * @return Result类
     */
    Result saveType(LabelSaveDTO labelSaveDTO);

    /**
     * 修改文章类型
     * @param labelUpdateDTO 修改文章类型的数据传输类
     * @return Result类
     */
    Result updateType(LabelUpdateDTO labelUpdateDTO);

    /**
     * 删除文章类型
     * @param ids 文章类型id集合
     * @return Result类
     */
    Result removeType(List<String> ids);
}
