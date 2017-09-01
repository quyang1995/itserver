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
import com.longfor.itserver.service.IBugFileService;
import com.longfor.itserver.service.base.AdminBaseService;
import net.mayee.commons.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangs on 2017/8/31.
 */
@Service("BugFileService")
public class BugFileServiceImpl extends AdminBaseService<BugFile> implements IBugFileService {

    @Autowired
    private BugFileMapper bugFileMapper;
    @Autowired
    private BugInfoMapper bugInfoMapper;


    @Override
    public boolean updateBugFile(Map paramsMap) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(paramsMap);
        BugInfo bugInfo = JSONObject.toJavaObject(jsonObject,BugInfo.class);
        BugFile bugFile = new BugFile();
        bugFile.setBugId(bugInfo.getId());
        bugFileMapper.delete(bugFile);
        List<BugFile> fileList = JSONArray.parseArray(jsonObject.getString("fileList"), BugFile.class);
        if(fileList!= null && fileList.size()>0) {
            for (BugFile file : fileList) {
                file.setBugId(bugInfo.getId());
                //			demandFile.setFileName("");
                //			demandFile.setFilePath("");
                //			demandFile.setFileSuffix("");
                //			demandFile.setFileSize("");
                file.setCreateTime(TimeUtils.getTodayByDateTime());
                bugFileMapper.insert(file);
            }
        }

        return false;
    }

    @Override
    public boolean addBugFile(Map paramsMap) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(paramsMap);
        BugInfo bugInfo = JSONObject.toJavaObject(jsonObject,BugInfo.class);
        List<BugInfo> bugInfoList = bugInfoMapper.select(bugInfo);
        bugInfo =  bugInfoList.get(bugInfoList.size()-1);
        if(bugInfo != null) {
            List<BugFile> fileList = JSONArray.parseArray(jsonObject.getString("fileList"), BugFile.class);
            if(fileList!= null && fileList.size()>0) {
                for (BugFile file : fileList) {
                    file.setBugId(bugInfo.getId());
                    //			demandFile.setFileName("");
                    //			demandFile.setFilePath("");
                    //			demandFile.setFileSuffix("");
                    //			demandFile.setFileSize("");
                    file.setCreateTime(TimeUtils.getTodayByDateTime());
                    bugFileMapper.insert(file);
                }
            }
        }

        return true;
    }



}
