package com.zjz.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zjz
 * @description
 * @date 2021-06-09 18:55
 */
@ApiModel("修改用户答题记录的数据传输类")
public class RankingUpdateDTO {

    @ApiModelProperty(value = "排行id", example = "3473ba73-eace-4105-8468-d0f9e80da5cb")
    private String id;

    @ApiModelProperty(value = "试卷id(UUID)", example = "1da6d1f8-591d-46d1-9d6f-0489a6e6f979")
    private String paperId;

    @ApiModelProperty(value = "姓名", example = "李四")
    private String name;

    @ApiModelProperty(value = "分数", example = "99")
    private Integer score;

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

    @Override
    public String toString() {
        return "RankingUpdateDTO{" +
            "id='" + id + '\'' +
            ", paperId='" + paperId + '\'' +
            ", name='" + name + '\'' +
            ", score=" + score +
            '}';
    }
}
