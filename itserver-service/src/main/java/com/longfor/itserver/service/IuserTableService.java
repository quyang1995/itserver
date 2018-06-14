package com.longfor.itserver.service;

import com.longfor.itserver.entity.userTable;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

/**
 * quyang
 */
public interface IuserTableService extends IAdminService<userTable> {

    //查询基本信息(demo)
    List<userTable> userList();
    //新增
    Map insetUser(Map paramsMap);

}
