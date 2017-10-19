package com.longfor.itserver.service.util;

import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.entity.BuddyAccount;
import com.longfor.ads.helper.ADSHelper;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by sunyanhui on 2017/10/18.
 */
public class AccountUitl {

    /***
     * 0-内部账号，查询ads获取内部账户信息    1-供方账号，查询ads获取供方账号信息
     * @param accountType
     * @param loginName
     * @param adsHelper
     * @return
     */
    public static AccountLongfor getAccountByAccountType(Integer accountType, String loginName, ADSHelper adsHelper){
        AccountLongfor accountLongfor = new AccountLongfor();
        if(accountType==0){
            accountLongfor = adsHelper.getAccountLongforByLoginName(loginName);
            return accountLongfor;
        }
        if(accountType==1){
            BuddyAccount buddyAccount = adsHelper.getByLoginNameBuddyAccount(loginName);
            if(buddyAccount==null)return null;
            accountLongfor.setName(buddyAccount.getName());
            return accountLongfor;
        }
        return null;
    }

    /***
     * 先查询内部账号，如果有数据直接返回，否则在查询外部账号
     * @param loginName
     * @param adsHelper
     * @return
     */
    public static AccountLongfor getAccountByAccountTypes( String loginName, ADSHelper adsHelper){
        AccountLongfor accountLongfor = adsHelper.getAccountLongforByLoginName(loginName);
        if(accountLongfor!=null){
            return accountLongfor;
        }
        BuddyAccount buddyAccount = adsHelper.getByLoginNameBuddyAccount(loginName);
        if(buddyAccount==null)return null;
        accountLongfor = new AccountLongfor();
        accountLongfor.setName(buddyAccount.getName());
        accountLongfor.setLoginName(loginName);
        return accountLongfor;
    }
}
