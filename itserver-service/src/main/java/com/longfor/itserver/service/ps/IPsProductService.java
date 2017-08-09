package com.longfor.itserver.service.ps;



import com.longfor.itserver.entity.ps.PsProduct;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IPsProductService extends IAdminService<PsProduct> {
    List<PsProduct> searchList(Map map);
    List<PsProduct> searchLikeList(Map map);
    PsProduct getById(Integer id);
}
