package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.mapper.base.BeeMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductEmployeeMapper extends BeeMapper<ProductEmployee> {
    List<ProductEmployee> selectTypeList(@Param(value = "productId")Long productId,@Param(value = "employeeType")Integer employeeType,@Param(value = "employeeTypeId") Long employeeTypeId);
}