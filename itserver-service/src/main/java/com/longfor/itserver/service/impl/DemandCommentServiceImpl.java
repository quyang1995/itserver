package com.longfor.itserver.service.impl;


import com.longfor.itserver.entity.DemandComment;
import com.longfor.itserver.mapper.DemandCommentMapper;
import com.longfor.itserver.service.IDemandCommentService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    @Override
    public List<DemandComment> getListById(DemandComment demandId) {

        List<DemandComment> demandCommentList= demandCommentMapper.selectListById(demandId);

        return demandCommentList;
    }
}
