package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.Product;
import com.longfor.itserver.mapper.base.BeeMapper;

public interface ProductMapper extends BeeMapper<Product> {
    List<Product> selectList(Map map);
    List<Product> selectLikeList(Map map);
}