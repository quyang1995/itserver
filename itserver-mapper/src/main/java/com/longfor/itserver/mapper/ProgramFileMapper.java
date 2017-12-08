package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.ProgramFile;
import com.longfor.itserver.entity.ProgramFileExample;
import com.longfor.itserver.mapper.base.BeeMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProgramFileMapper extends BeeMapper<ProgramFile> {
    int countByExample(ProgramFileExample example);

    int deleteByExample(ProgramFileExample example);

    List<ProgramFile> selectByExampleWithBLOBs(ProgramFileExample example);

    List<ProgramFile> selectByExample(ProgramFileExample example);

    int updateByExampleSelective(@Param("record") ProgramFile record, @Param("example") ProgramFileExample example);

    int updateByExampleWithBLOBs(@Param("record") ProgramFile record, @Param("example") ProgramFileExample example);

    int updateByExample(@Param("record") ProgramFile record, @Param("example") ProgramFileExample example);
}