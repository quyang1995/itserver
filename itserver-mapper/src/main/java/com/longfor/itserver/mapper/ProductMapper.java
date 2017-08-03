package com.longfor.itserver.mapper;

import com.longfor.itserver.mapper.base.BeeMapper;
import generator.warehouse.entity.Product;
import generator.warehouse.entity.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ProductMapper extends BeeMapper<Product> {
}