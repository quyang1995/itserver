package com.longfor.itserver.mapper;

import java.util.List;

import com.longfor.itserver.entity.ProgramApprovalSnapshot;
import com.longfor.itserver.entity.ProgramApprovalSnapshotExample;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ProgramApprovalSnapshotMapper extends Mapper<ProgramApprovalSnapshot> {
    int countByExample(ProgramApprovalSnapshotExample example);

    int deleteByExample(ProgramApprovalSnapshotExample example);

    List<ProgramApprovalSnapshot> selectByExample(ProgramApprovalSnapshotExample example);

    int updateByExampleSelective(@Param("record") ProgramApprovalSnapshot record, @Param("example") ProgramApprovalSnapshotExample example);

    int updateByExample(@Param("record") ProgramApprovalSnapshot record, @Param("example") ProgramApprovalSnapshotExample example);
}