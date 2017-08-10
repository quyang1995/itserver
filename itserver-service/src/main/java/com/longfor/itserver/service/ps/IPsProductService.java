package com.longfor.itserver.service.ps;



import com.longfor.itserver.entity.ps.PsProductAll;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IPsProductService extends IAdminService<PsProductAll> {
    PsProductAll getById(Integer id);
}
