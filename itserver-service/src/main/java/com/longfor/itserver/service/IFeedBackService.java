package com.longfor.itserver.service;

import com.longfor.itserver.entity.FeedBack;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IFeedBackService extends IAdminService<FeedBack> {

    boolean addFeedBack(Map map);

    List<FeedBack> feedBackList(Map map);

    FeedBack getFeedBackId(long id);
}
