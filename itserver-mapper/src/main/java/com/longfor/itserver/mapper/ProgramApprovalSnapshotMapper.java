package com.longfor.itserver.mapper;

import java.util.List;
import java.util.Map;

import com.longfor.itserver.entity.ProgramApprovalSnapshot;
import com.longfor.itserver.entity.ProgramApprovalSnapshotExample;
import com.longfor.itserver.mapper.base.BeeMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ProgramApprovalSnapshotMapper extends BeeMapper<ProgramApprovalSnapshot> {

    List<ProgramApprovalSnapshot> getListByProgramIdAndStatus(ProgramApprovalSnapshot programApprovalSnapshot);

    List<ProgramApprovalSnapshot> grayLevelList(Map<String, Object> map);
}