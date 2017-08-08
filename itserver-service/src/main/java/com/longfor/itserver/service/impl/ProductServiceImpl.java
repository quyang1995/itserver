package com.longfor.itserver.service.impl;

import com.longfor.itserver.entity.Product;
import com.longfor.itserver.service.IProductService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.stereotype.Service;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("ProductService")
public class ProductServiceImpl extends AdminBaseService<Product> implements IProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public List<Product> searchList(Map map) {
        return productMapper.selectList(map);
    }

    @Override
    public List<Product> searchLikeList(Map map) {
        return productMapper.selectLikeList(map);
    }
}
