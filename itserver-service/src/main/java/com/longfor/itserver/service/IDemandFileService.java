package com.longfor.itserver.service;

import com.longfor.itserver.entity.DemandFile;
import com.longfor.itserver.service.base.IAdminService;

import java.util.Map;

/**
 * Created by wangs on 2017/8/31.
 */
public interface IDemandFileService extends IAdminService<DemandFile>{
    boolean addDemandFile(Map paramsMap);
}
