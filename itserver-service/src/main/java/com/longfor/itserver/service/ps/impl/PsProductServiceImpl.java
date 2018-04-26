package com.longfor.itserver.service.ps.impl;

import com.longfor.itserver.entity.ps.PsProductAll;
import com.longfor.itserver.mapper.ProductEmployeeMapper;
import com.longfor.itserver.mapper.ps.PsProductMapper;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.ps.IPsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wax
 *         Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("PsProductService")
public class PsProductServiceImpl extends AdminBaseService<PsProductAll> implements IPsProductService {
    @Autowired
    private PsProductMapper psProductMapper;
    @Autowired
    private ProductEmployeeMapper productEmployeeMapper;

    @Override
    public PsProductAll getById(Integer id) {
        return psProductMapper.getById(id);
    }


}
