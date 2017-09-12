package com.longfor.itserver.service;


import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;

public interface IProductEmployeeService extends IAdminService<ProductEmployee> {
    List<ProductEmployee> searchTypeList(Long productId, Integer employeeType, Long employeeTypeId);

    boolean delEmployee (ProductEmployee employee);
}
