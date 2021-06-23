package com.zjz.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zjz
 * @description 增加用户答题记录的数据传输类
 * @date 2021-06-09 18:32
 */
@ApiModel("增加用户答题记录的数据传输类")
public class RankingSaveDTO {

    @ApiModelProperty(value = "试卷id(UUID)", example = "1da6d1f8-591d-46d1-9d6f-0489a6e6f979")
    private String paperId;

    @ApiModelProperty(value = "姓名", example = "王五")
    private String name;

    @ApiModelProperty(value = "分数", example = "88")
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "RankingSaveDTO{" +
            "paperId='" + paperId + '\'' +
            ", name='" + name + '\'' +
            ", score=" + score +
            '}';
    }
}
