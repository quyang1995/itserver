package com.longfor.itserver.service.impl;


import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.entity.DemandComment;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.mapper.DemandCommentMapper;
import com.longfor.itserver.service.IDemandCommentService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.longfor.itserver.entity.ProductEmployee;

import java.util.List;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("DemandCommentService")
public class DemandCommentServiceImpl extends AdminBaseService<DemandComment> implements IDemandCommentService {

    @Autowired
    DemandCommentMapper demandCommentMapper;

    @Autowired
    private ADSHelper adsHelper;



    @Override
    public List<DemandComment> getListById(DemandComment demandComment) {

        List<DemandComment> demandCommentList= demandCommentMapper.selectListById(demandComment);

        return demandCommentList;
    }

    @Override
    public boolean add(DemandComment demandComment){

        AccountLongfor accountLongfor =  adsHelper.getAccountLongforByLoginName(demandComment.getAccountId());
        demandComment.setEmployeeCode(Long.parseLong(accountLongfor.getPsEmployeeCode()));
        demandComment.setEmployeeName(accountLongfor.getName());
        demandComment.setFullDeptPath(accountLongfor.getPsDeptFullName());

        boolean flag = this.add(demandComment);
        return flag;
    };
}
