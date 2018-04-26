package com.longfor.itserver.service;

import com.longfor.itserver.entity.ProductEmployeeChangeLog;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IProductEmployeeChangeLogService extends IAdminService<ProductEmployeeChangeLog> {


        Map orderList(Map paramsMap);

        List<ProductEmployeeChangeLog> paraQuery(Map<String, String> paramsMap);
}
