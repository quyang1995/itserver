package com.longfor.itserver.esi.impl;

import com.longfor.ads.entity.AccountLongforLike;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.esi.IAdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author chi.zhang
 * @create 2017/6/9 上午8:33
 *
 * @version v1.0
 **/
@Service("adsServiceImpl")
public class AdsServiceImpl implements IAdsService {

    @Autowired
    private ADSHelper adsHelper;



    @Override
    public List<AccountLongforLike> likeByName(String name) {
        return adsHelper.likeByName(name);
    }


}
