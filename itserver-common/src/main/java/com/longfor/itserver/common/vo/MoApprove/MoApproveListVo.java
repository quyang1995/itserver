package com.longfor.itserver.common.vo.MoApprove;

import java.util.List;


public class MoApproveListVo {
    private int total;
    private List<MoApproveVo> list;
    private int page;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MoApproveVo> getList() {
        return list;
    }

    public void setList(List<MoApproveVo> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}