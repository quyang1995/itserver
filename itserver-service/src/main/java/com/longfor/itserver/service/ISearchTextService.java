package com.longfor.itserver.service;

import com.longfor.itserver.entity.SearchText;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface ISearchTextService extends IAdminService<SearchText>{

    List<SearchText> getSearchText(Map<String,Object> map);
}
