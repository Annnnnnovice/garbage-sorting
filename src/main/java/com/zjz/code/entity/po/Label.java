package com.zjz.code.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 专题表
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
@TableName("t_label")
public class Label implements Serializable {


    /**
     * 主键(UUID)
     */
    private String id;

    /**
     * 专题名
     */
    private String name;

    /**
     * 该专题拥有的文章数量
     */
    private Integer number;

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

    public Label() {
    }

    public Label(String name) {
        this.name = name;
    }

    public Label(String id, String name) {
        this.id = id;
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
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
        return "Label{" +
            "id=" + id +
            ", name=" + name +
            ", number=" + number +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
