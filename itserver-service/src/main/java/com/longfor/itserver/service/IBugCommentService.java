package com.longfor.itserver.service;


import com.longfor.itserver.entity.BugComment;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IBugCommentService extends IAdminService<BugComment> {


    Map<String,Object>  add(Map paramsMap);
}
