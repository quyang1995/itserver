package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ps.PsProduct;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface ProductMapper extends BeeMapper<Product> {
    List<Product> selectList(Map map);
    List<Product> selectLikeList(Map map);
    List<Product>  selectIdList(String likeProduct);
}