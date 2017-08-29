package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.ProductEmployeeChangeLog;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;

public interface ProductEmployeeChangeLogMapper extends BeeMapper<ProductEmployeeChangeLog> {

        List<ProductEmployeeChangeLog> orderList(ProductEmployeeChangeLog productEmployeeChangeLog);

}