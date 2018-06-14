package com.longfor.itserver.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.entity.userTable;
import com.longfor.itserver.mapper.userTableMapper;
import com.longfor.itserver.service.IuserTableService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * quyang
 */
@Service("BugChangeLogService")
public class UserTableServiceImpl extends AdminBaseService<userTable> implements IuserTableService {

    @Autowired
    private userTableMapper userTableMappers;

    /**
     * quyang 查询
     * @return
     */
    public List<userTable> userList(){
        return userTableMappers.selectUserTable();
    }

    /**
     * quyang 新增
     * @param paramsMap
     * @return
     */
    public Map insetUser(Map paramsMap){
        Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
        userTable userTables = new userTable();
        userTables.setName(paramsMap.get("name").toString());
        userTables.setSex(paramsMap.get("sex").toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userTables.setCreateTime(df.format(new Date()));
        try{
            userTableMappers.insert(userTables);
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }

        return resultMap;
    }

}
