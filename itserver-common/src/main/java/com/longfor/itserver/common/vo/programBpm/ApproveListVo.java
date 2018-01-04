package com.longfor.itserver.common.vo.programBpm;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.longfor.itserver.common.vo.programBpm.common.FileVo;
import net.mayee.commons.CustomDateSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class ApproveListVo {
    private List<ApproveVo> list;
    private int total;
    private int pageNo;

    public List<ApproveVo> getList() {
        return list;
    }

    public void setList(List<ApproveVo> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}