package com.longfor.itserver.service;


import com.longfor.itserver.entity.DemandComment;
import com.longfor.itserver.service.base.IAdminService;

import java.util.Map;

public interface IDemandCommentService extends IAdminService<DemandComment> {

    Map<String, Object> add (Map paramsMap);
}
