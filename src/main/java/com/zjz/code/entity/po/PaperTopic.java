package com.zjz.code.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 试卷题目关系表
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
@TableName("t_paper_topic")
public class PaperTopic implements Serializable {


    /**
     * 主键(UUID)
     */
    private String id;

    /**
     * 试卷id(UUID)
     */
    private String paperId;

    /**
     * 题目id(UUID)
     */
    private String topicId;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public PaperTopic() {
    }

    public PaperTopic(String paperId) {
        this.paperId = paperId;
    }

    public PaperTopic(String topicId, Integer score) {
        this.topicId = topicId;
        this.score = score;
    }

    public PaperTopic(String paperId, String topicId) {
        this.paperId = paperId;
        this.topicId = topicId;
    }

    public PaperTopic(String id, String paperId, String topicId, Integer score) {
        this.id = id;
        this.paperId = paperId;
        this.topicId = topicId;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PaperTopic{" +
            "id=" + id +
            ", paperId=" + paperId +
            ", topicId=" + topicId +
            ", score=" + score +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
