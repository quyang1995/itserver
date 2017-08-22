package com.longfor.itserver.service;


import com.longfor.itserver.entity.DemandComment;
import com.longfor.itserver.service.base.IAdminService;
import java.util.List;

public interface IDemandCommentService extends IAdminService<DemandComment> {

    List<DemandComment> getListById(DemandComment demandComment);
}
