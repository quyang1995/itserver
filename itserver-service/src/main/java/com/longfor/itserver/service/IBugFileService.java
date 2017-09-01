package com.longfor.itserver.service;

import com.longfor.itserver.entity.BugFile;
import com.longfor.itserver.service.base.IAdminService;

import java.util.Map;

/**
 * Created by wangs on 2017/8/31.
 */

public interface IBugFileService extends IAdminService<BugFile>{

    boolean addBugFile(Map paramsMap);
    boolean updateBugFile(Map paramsMap);
}
