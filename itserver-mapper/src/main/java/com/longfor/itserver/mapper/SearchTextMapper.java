package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.SearchText;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
import java.util.Map;

public interface SearchTextMapper extends BeeMapper<SearchText> {

    List<SearchText> getSearchText(Map<String,Object> map);
}