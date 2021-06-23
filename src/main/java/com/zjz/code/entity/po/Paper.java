package com.zjz.code.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 试卷表
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
@TableName("t_paper")
public class Paper implements Serializable {


    /**
     * 主键(UUID)
     */
    private String id;

    /**
     * 试卷名
     */
    private String name;

    /**
     * 总分
     */
    private Integer totalScore;

    /**
     * 题目数量
     */
    private Integer quantity;

    /**
     * 是否发布
     */
    private Boolean isRelease;

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

    public Paper() {
    }

    public Paper(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Paper(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Boolean getRelease() {
        return isRelease;
    }

    public void setRelease(Boolean release) {
        isRelease = release;
    }

    @Override
    public String toString() {
        return "Paper{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", totalScore=" + totalScore +
            ", quantity=" + quantity +
            ", isRelease=" + isRelease +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            '}';
    }
}
