package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.itserver.entity.BugFile;
import com.longfor.itserver.entity.BugInfo;
import com.longfor.itserver.entity.Demand;
import com.longfor.itserver.entity.DemandFile;
import com.longfor.itserver.mapper.BugFileMapper;
import com.longfor.itserver.mapper.BugInfoMapper;
import com.longfor.itserver.mapper.DemandFileMapper;
import com.longfor.itserver.mapper.DemandMapper;
import com.longfor.itserver.service.IDemandFileService;
import com.longfor.itserver.service.base.AdminBaseService;
import net.mayee.commons.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangs on 2017/8/31.
 */
@Service("DemandFileService")
public class DemandFileServiceImpl extends AdminBaseService<DemandFile> implements IDemandFileService{

    @Autowired
    private DemandFileMapper demandFileMapper;
    @Autowired
    private DemandMapper demandMapper;

    @Override
    public boolean addDemandFile(Map paramsMap) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(paramsMap);
        Demand demand = JSONObject.toJavaObject(jsonObject,Demand.class);
        List<Demand> demandList = demandMapper.select(demand);
        demand = demandList.get(demandList.size()-1);
        if(demand != null) {
            List<DemandFile> fileList = JSONArray.parseArray(jsonObject.getString("fileList"), DemandFile.class);
            if(fileList!= null && fileList.size()>0) {
                for (DemandFile demandFile : fileList) {
                    //			DemandFile demandFile = new DemandFile();

                    demandFile.setDemandId(demand.getId());
                    //			demandFile.setFileName("");
                    //			demandFile.setFilePath("");
                    //			demandFile.setFileSuffix("");
                    //			demandFile.setFileSize("");
                    demandFile.setCreateTime(TimeUtils.getTodayByDateTime());
                    demandFileMapper.insert(demandFile);
                }
            }
        }

        return true;
    }
}
