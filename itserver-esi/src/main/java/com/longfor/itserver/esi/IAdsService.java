package com.longfor.itserver.esi;

import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.entity.AccountLongforLike;

import java.util.List;

/**
 *
 * @author chi.zhang
 * @create 2017/6/9 上午8:34
 *
 * @version v1.0
 **/
public interface IAdsService {

    List<AccountLongforLike> likeByName(String name);

    boolean isAvaAccount(String loginName);

    AccountLongfor getAccountLongfor(String loginName);
}
