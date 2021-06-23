package com.zjz.code.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 排行表
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
@TableName("t_ranking")
public class Ranking implements Serializable {


    /**
     * 主键(UUID)
     */
    private String id;

    /**
     * 试卷id(UUID)
     */
    private String paperId;

    /**
     * 做题人名称
     */
    private String name;

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

    public Ranking() {
    }

    public Ranking(String paperId) {
        this.paperId = paperId;
    }

    public Ranking(String id, String paperId, String name, Integer score) {
        this.id = id;
        this.paperId = paperId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Ranking{" +
            "id='" + id + '\'' +
            ", paperId='" + paperId + '\'' +
            ", name='" + name + '\'' +
            ", score=" + score +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            '}';
    }
}
