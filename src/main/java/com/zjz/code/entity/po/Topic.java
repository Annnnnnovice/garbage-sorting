package com.zjz.code.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 题目表
 * </p>
 *
 * @author zjz
 * @since 2021-06-06
 */
@TableName("t_topic")
public class Topic implements Serializable {


    /**
     * 主键(UUID)
     */
    private String id;

    /**
     * 题目名称
     */
    private String name;

    /**
     * 图片url
     */
    private String photoUrl;

    /**
     * 选项一
     */
    private String optionA;

    /**
     * 选项二
     */
    private String optionB;

    /**
     * 选项三
     */
    private String optionC;

    /**
     * 选项四
     */
    private String optionD;

    /**
     * 答案
     */
    private String answer;

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
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }
    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }
    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
        return "Topic{" +
            "id=" + id +
            ", name=" + name +
            ", photoUrl=" + photoUrl +
            ", optionA=" + optionA +
            ", optionB=" + optionB +
            ", optionC=" + optionC +
            ", optionD=" + optionD +
            ", answer=" + answer +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
