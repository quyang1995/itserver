package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.ProgramFile;
import com.longfor.itserver.entity.ProgramFileExample;
import com.longfor.itserver.entity.ProgramFileVo;
import com.longfor.itserver.mapper.base.BeeMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ProgramFileMapper extends BeeMapper<ProgramFile> {

    List<ProgramFile> getListByMap(Map<String,Object> map);

    int getFileListByMapTotal(Map<String,Object> map);

    List<ProgramFileVo> getFileListByMap(Map<String,Object> map);

}