package com.zjz.code.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章专题关系表
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
@TableName("t_article_label")
public class ArticleLabel implements Serializable {


    /**
     * 主键(UUID)
     */
    private String id;

    /**
     * 文章id(UUID)
     */
    private String articleId;

    /**
     * 专题id(UUID)
     */
    private String labelId;

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

    public ArticleLabel() {
    }

    public ArticleLabel(String articleId) {
        this.articleId = articleId;
    }

    public ArticleLabel(String id, String articleId, String labelId) {
        this.id = id;
        this.articleId = articleId;
        this.labelId = labelId;
    }

    public ArticleLabel(String id, String articleId, String labelId, Date createTime) {
        this.id = id;
        this.articleId = articleId;
        this.labelId = labelId;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
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
        return "ArticleLabel{" +
            "id=" + id +
            ", articleId=" + articleId +
            ", labelId=" + labelId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
