package com.zjz.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zjz
 * @description 类型或专题或试卷增加的数据传输类
 * @date 2021-06-08 16:16
 */
@ApiModel("类型或专题或试卷增加的数据传输类")
public class LabelSaveDTO {

    @ApiModelProperty(value = "名称", example = "测试")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LabelSaveDTO{" +
            "name='" + name + '\'' +
            '}';
    }
}
