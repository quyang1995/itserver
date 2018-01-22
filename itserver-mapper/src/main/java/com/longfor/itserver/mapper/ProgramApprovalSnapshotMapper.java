package com.longfor.itserver.mapper;

import java.util.List;
import java.util.Map;

import com.longfor.itserver.entity.ExceptionProgramVo;
import com.longfor.itserver.entity.ProgramApprovalSnapshot;
import com.longfor.itserver.mapper.base.BeeMapper;

public interface ProgramApprovalSnapshotMapper extends BeeMapper<ProgramApprovalSnapshot> {

    List<ProgramApprovalSnapshot> getListByProgramIdAndStatus(Map<String, Object> map);

    List<ProgramApprovalSnapshot> grayLevelList(Map<String, Object> map);

    List<ProgramApprovalSnapshot> getByBpmCodes(List<String> list);

    List<ExceptionProgramVo> getExceptionProgram(Map<String, Object> map);

    Integer getExceptionProgramTotal(Map<String, Object> map);

    List<ProgramApprovalSnapshot> latelychangeList(Map<String, Object> map);

    Integer latelychangeListTotal(Map<String, Object> map);

    ProgramApprovalSnapshot getOneByWhere(Map<String, Object> map);
}