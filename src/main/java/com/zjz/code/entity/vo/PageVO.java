package com.zjz.code.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author zjz
 * @description 分页的显示层
 * @date 2021-06-06 15:51
 */
@ApiModel("分页的显示层")
public class PageVO<T> {

    public static final Integer PAGE_SIZE = 5;

    @ApiModelProperty(value = "当前页码")
    private Integer pageNow;

    @ApiModelProperty(value = "总页码")
    private Integer pageTotal;

    @ApiModelProperty(value = "当前页显示数量，默认显示5条")
    private Integer pageSize;

    @ApiModelProperty(value = "总记录数")
    private Integer pageTotalCount;

    @ApiModelProperty(value = "当前页数据")
    private List<T> items;

    public PageVO() {
        super();
    }



    public PageVO(Integer pageTotal, Integer pageSize, Integer pageTotalCount) {
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
    }

    public PageVO(Integer pageNow, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> items) {
        this.pageNow = pageNow;
        this.pageTotal = pageTotal;
        this.pageSize = PAGE_SIZE;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        //数据边界有效检查
        if (pageNow <= 0) {
            pageNow = 1;
        }
        /*if (pageNow > pageTotal && pageTotal != 0) {
            pageNow = pageTotal;
        }*/
        this.pageNow = pageNow;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {

        this.pageTotal = pageTotal;
    }


    @Override
    public String toString() {
        return "Page{" +
            "pageNow=" + pageNow +
            ", pageTotal=" + pageTotal +
            ", pageSize=" + pageSize +
            ", pageTotalCount=" + pageTotalCount +
            ", items=" + items +
            '}';
    }
}
