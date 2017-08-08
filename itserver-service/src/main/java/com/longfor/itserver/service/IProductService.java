package com.longfor.itserver.service;


import com.longfor.itserver.entity.Product;
import com.longfor.itserver.service.base.IAdminService;

public interface IProductService extends IAdminService<Product> {
    List<Product> searchList(Map map);
    List<Product> searchLikeList(Map map);
}
