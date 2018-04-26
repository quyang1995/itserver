package com.longfor.itserver.service.impl;

import com.longfor.itserver.entity.*;
import com.longfor.itserver.mapper.*;
import com.longfor.itserver.service.IProgramFileService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wax Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("ProgramFileService")
public class ProgramFileServiceImpl extends AdminBaseService<ProgramFile> implements IProgramFileService {

    @Autowired
    private ProgramFileMapper programFileMapper;
    @Autowired
    private ProgramApprovalSnapshotMapper programApprovalSnapshotMapper;

//    @Override
//    public List<ProgramFileVo> getListByMap(Map<String,Object> paramsMap) throws Exception{
//        List<ProgramFile> fileList = programFileMapper.getListByMap(paramsMap);
//        if (fileList == null || fileList.isEmpty()) {
//            return null;
//        }
//        List<ProgramFileVo> fileVoList = new ArrayList<ProgramFileVo>();
//        for (ProgramFile file : fileList) {
//            ProgramFileVo fileVo = new ProgramFileVo();
//            BeanUtils.copyProperties(fileVo,file);
//            ProgramApprovalSnapshot shot = programApprovalSnapshotMapper.selectByPrimaryKey(file.getSnapshotId());
//            if (shot != null) {
//                fileVo.setProgramName(shot.getName());
//                fileVo.setProductId(shot.getProductId());
//                fileVo.setProductName(shot.getProductName());
//                fileVo.setModifiedAccountId(shot.getModifiedAccountId());
//                fileVo.setModifiedName(shot.getModifiedName());
//            }
//            fileVoList.add(fileVo);
//        }
//        return fileVoList;
//    }

    @Override
    public int getFileListByMapTotal(Map<String,Object> paramsMap){

        return programFileMapper.getFileListByMapTotal(paramsMap);
    }

    @Override
    public List<ProgramFileVo> getFileListByMap(Map<String,Object> paramsMap) {

        return programFileMapper.getFileListByMap(paramsMap);
    }
}

