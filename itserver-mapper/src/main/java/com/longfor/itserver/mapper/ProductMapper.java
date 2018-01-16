package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ps.PsProduct;
import com.longfor.itserver.entity.ps.PsProductCount;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface ProductMapper extends BeeMapper<Product> {
    List<PsProductCount> selectList(Map map);
    List<Product> selectLikeList(Map map);
    List<Product>  selectIdList(String likeProduct);
    List<Product> getListSort();
    List<Product> productCountList(Map map);

    String getNewCode();

    List<Product> getListByLikeAnalyzingConditions(Map map);
}