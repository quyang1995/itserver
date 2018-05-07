package com.longfor.itserver.service.impl;

import com.longfor.itserver.entity.SearchText;
import com.longfor.itserver.mapper.SearchTextMapper;
import com.longfor.itserver.service.ISearchTextService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("SearchTextService")
public class SearchTextServiceImpl extends AdminBaseService<SearchText> implements ISearchTextService {

    @Autowired
    private SearchTextMapper searchTextMapper;

    public List<SearchText> getSearchText(Map<String,Object> map){
        return searchTextMapper.getSearchText(map);
    }

}
