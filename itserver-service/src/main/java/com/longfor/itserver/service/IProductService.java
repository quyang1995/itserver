package com.longfor.itserver.service;


import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ps.PsProduct;
import com.longfor.itserver.entity.ps.PsProductCount;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IProductService extends IAdminService<Product> {
    List<PsProductCount> searchList(Map map);
    List<Product> searchLikeList(Map map);
    Map<String, Object> addProduct(Map map);
    List<Product> searchIdList(String likeProduct);
    boolean updateProduct(Map map);
    List<Product> getListSort();
    List<Product> productCountList(Map map);

    boolean updateStatus(Map paramsMap);
}
