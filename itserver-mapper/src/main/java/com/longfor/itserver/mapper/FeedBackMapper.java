package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.FeedBack;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface FeedBackMapper extends BeeMapper<FeedBack> {

    List<FeedBack> feedBackList(Map map);

    FeedBack getFeedBackId(long id);
}