package com.longfor.itserver.service;


import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ps.PsProduct;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IProductService extends IAdminService<Product> {
    List<Product> searchList(Map map);
    List<Product> searchLikeList(Map map);
    boolean addProduct(Map map);
    List<Product> searchIdList(String likeProduct);
    boolean updateProduct(Map map);
    List<Product> getListSort();
}
