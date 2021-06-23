package com.zjz.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author zjz
 * @description 删除题目的数据传输类
 * @date 2021-06-09 16:31
 */
@ApiModel("删除题目的数据传输类")
public class TopicRemoveDTO {

    @ApiModelProperty(value = "试卷id", example = "1da6d1f8-591d-46d1-9d6f-0489a6e6f979")
    private String paperId;

    @ApiModelProperty("题目id集合")
    private List<String> ids;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "TopicRemoveDTO{" +
            "paperId='" + paperId + '\'' +
            ", ids=" + ids +
            '}';
    }
}
