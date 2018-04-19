package com.longfor.itserver.service.util;

import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.entity.BuddyAccount;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.eds.entity.PsEmployeeWy;
import com.longfor.eds.helper.EDSHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

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
        EDSHelper edsHelper = new EDSHelper();
        AccountLongfor accountLongfor = new AccountLongfor();
        if(accountType==0){
            accountLongfor = adsHelper.getAccountLongforByLoginName(loginName);
            if(accountLongfor == null){
                //查询地产人员为null时 查询物业人员
                PsEmployeeWy psEmployeeWy = edsHelper.getWyEmployeeByLoginName(loginName);
                if(psEmployeeWy == null){
                    return null;
                }else{
                    accountLongfor = new AccountLongfor();
                    accountLongfor.setId(psEmployeeWy.getId());
                    accountLongfor.setUcAccountId(psEmployeeWy.getGuid());
                    accountLongfor.setLoginName(psEmployeeWy.getHpsOaAcc());
                    accountLongfor.setName(psEmployeeWy.getName1());
                    accountLongfor.setPsEmployeeCode(psEmployeeWy.getEmplid());
                    accountLongfor.setPsDeptId(new Long(psEmployeeWy.getDeptid()));
                    accountLongfor.setPsDeptFullName(psEmployeeWy.getDeptDescr());
                }
            }
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
        EDSHelper edsHelper = new EDSHelper();
        AccountLongfor accountLongfor = adsHelper.getAccountLongforByLoginName(loginName);
        if(accountLongfor!=null){
            return accountLongfor;
        }
        PsEmployeeWy psEmployeeWy = edsHelper.getWyEmployeeByLoginName(loginName);
        if(psEmployeeWy!=null){
            accountLongfor = new AccountLongfor();
            accountLongfor.setId(psEmployeeWy.getId());
            accountLongfor.setUcAccountId(psEmployeeWy.getGuid());
            accountLongfor.setLoginName(psEmployeeWy.getHpsOaAcc());
            accountLongfor.setName(psEmployeeWy.getName1());
            accountLongfor.setPsEmployeeCode(psEmployeeWy.getEmplid());
            accountLongfor.setPsDeptId(new Long(psEmployeeWy.getDeptid()));
            accountLongfor.setPsDeptFullName(psEmployeeWy.getDeptDescr());
            return accountLongfor;
        }
        BuddyAccount buddyAccount = adsHelper.getByLoginNameBuddyAccount(loginName);
        if(buddyAccount==null)return null;
        accountLongfor = new AccountLongfor();
        accountLongfor.setName(buddyAccount.getName());
        accountLongfor.setLoginName(loginName);
        return accountLongfor;
    }

    public static Integer getAccountType(Map<String,String> paramsMap){
        String accountTypeStr = paramsMap.get("accountType");
        if(StringUtils.isBlank(accountTypeStr)){
            return 0;
        }
        return Integer.parseInt(accountTypeStr);
    }
}
