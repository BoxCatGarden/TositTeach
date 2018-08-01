package com.tositteach.util;

import java.util.List;

public class PagingBody {
    //如果查询失败，total=-1，data=null
    private Integer total;
    private List<?> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
