package com.zjz.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zjz
 * @description 指定试卷增加题目的数据传输类
 * @date 2021-06-09 12:36
 */
@ApiModel("指定试卷增加题目的数据传输类")
public class TopicSaveDTO {

    @ApiModelProperty(value = "试卷id", example = "1da6d1f8-591d-46d1-9d6f-0489a6e6f979")
    private String paperId;

    @ApiModelProperty(value = "题目名称", example = "我的前端为什么效率这么低？")
    private String name;

    @ApiModelProperty(value = "图片url", example = "")
    private String photoUrl;

    @ApiModelProperty(value = "选项一", example = "课程多")
    private String optionA;

    @ApiModelProperty(value = "选项二", example = "懒")
    private String optionB;

    @ApiModelProperty(value = "选项三", example = "不肯熬夜")
    private String optionC;

    @ApiModelProperty(value = "选项四", example = "都有可能")
    private String optionD;

    @ApiModelProperty(value = "答案", example = "D")
    private String answer;

    @ApiModelProperty(value = "分数", example = "3")
    private Integer score;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "TopicSaveDTO{" +
            "paperId='" + paperId + '\'' +
            ", name='" + name + '\'' +
            ", photoUrl='" + photoUrl + '\'' +
            ", optionA='" + optionA + '\'' +
            ", optionB='" + optionB + '\'' +
            ", optionC='" + optionC + '\'' +
            ", optionD='" + optionD + '\'' +
            ", answer='" + answer + '\'' +
            ", score='" + score + '\'' +
            '}';
    }
}
