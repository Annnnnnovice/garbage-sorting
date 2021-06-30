package com.zjz.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zjz
 * @description 文章修改的数据传输类
 * @date 2021-06-07 16:07
 */
@ApiModel("文章修改的数据传输类")
public class ArticleUpdateDTO {

    @ApiModelProperty(value = "主键(UUID)", example = "d7fc8045-475e-4b1b-afc7-c2ac5e5f954d")
    private String id;

    @ApiModelProperty(value = "标题", example = "前端页面都不写")
    private String title;

    @ApiModelProperty(value = "图片url", example = "http://annnnnnovice.top:9999/api/images/u=2957037378,581731134&fm=26&gp=0.jpg")
    private String url;

    @ApiModelProperty(value = "类型名称", example = "垃圾分类知识")
    private String type;

    @ApiModelProperty(value = "专题", example = "生活垃圾,浙江")
    private String label;

    @ApiModelProperty(value = "内容", example = "该死的前端不知道写页面")
    private String content;

    public ArticleUpdateDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleUpdateDTO{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", url='" + url + '\'' +
            ", type='" + type + '\'' +
            ", label='" + label + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
