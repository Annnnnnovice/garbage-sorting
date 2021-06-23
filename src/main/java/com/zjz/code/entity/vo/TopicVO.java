package com.zjz.code.entity.vo;

/**
 * @author zjz
 * @description
 * @date 2021-06-10 14:58
 */
public class TopicVO {

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

    @Override
    public String toString() {
        return "TopicVO{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", photoUrl='" + photoUrl + '\'' +
            ", optionA='" + optionA + '\'' +
            ", optionB='" + optionB + '\'' +
            ", optionC='" + optionC + '\'' +
            ", optionD='" + optionD + '\'' +
            '}';
    }
}
