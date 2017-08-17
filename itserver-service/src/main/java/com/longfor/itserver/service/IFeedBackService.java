package com.longfor.itserver.service;

import com.longfor.itserver.entity.FeedBack;
import com.longfor.itserver.service.base.IAdminService;

import java.util.Map;

public interface IFeedBackService extends IAdminService<FeedBack> {

    boolean addFeedBack(Map map);

}
