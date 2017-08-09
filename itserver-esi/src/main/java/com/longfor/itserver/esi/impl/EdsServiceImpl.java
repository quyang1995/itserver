package com.longfor.itserver.esi.impl;

import com.longfor.eds.helper.EDSHelper;
import com.longfor.itserver.esi.IEdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chi.zhang
 * @create 2017/6/9 上午8:33
 *
 * @version v1.0
 **/
@Service("edsServiceImpl")
public class EdsServiceImpl implements IEdsService {

    @Autowired
    private EDSHelper edsHelper;





}
