package com.zjz.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zjz
 * @description 类型或专题修改的数据传输类
 * @date 2021-06-08 20:15
 */
@ApiModel("类型或试卷或专题修改的数据传输类")
public class LabelUpdateDTO {

    @ApiModelProperty(value = "主键(UUID)")
    private String id;

    @ApiModelProperty(value = "试卷或专题名", example = "测试111")
    private String name;

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

    @Override
    public String toString() {
        return "LabelUpdateDTO{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
