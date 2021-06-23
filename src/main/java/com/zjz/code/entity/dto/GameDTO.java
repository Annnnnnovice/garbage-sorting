package com.zjz.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @author zjz
 * @description
 * @date 2021-06-10 15:05
 */
@ApiModel("游戏记录保存数据传输类")
public class GameDTO {

    @ApiModelProperty("试卷id")
    private String paperId;

    @ApiModelProperty("玩家姓名")
    private String name;

    @ApiModelProperty("题目id和答案的键值对")
    private Map<String, String> map;

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

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "GameDTO{" +
            "paperId='" + paperId + '\'' +
            ", name='" + name + '\'' +
            ", map=" + map +
            '}';
    }
}
