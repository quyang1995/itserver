package com.longfor.itserver.service.impl;

import com.longfor.itserver.entity.ProgramApprovalSnapshot;
import com.longfor.itserver.mapper.ProgramApprovalSnapshotMapper;
import com.longfor.itserver.service.IProgramApprovalSnapshotService;
import com.longfor.itserver.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/12/22.
 */
@Service("ProgramApprovalSnapshotService")
public class ProgramApprovalSnapshotServiceImpl extends AdminBaseService<ProgramApprovalSnapshot> implements IProgramApprovalSnapshotService {

    @Autowired
    private ProgramApprovalSnapshotMapper programApprovalSnapshotMapper;

    @Override
    public List<ProgramApprovalSnapshot> grayLevelList(Map<String,Object> map) {

        return  programApprovalSnapshotMapper.grayLevelList(map);
    }

    @Override
    public List<ProgramApprovalSnapshot> getByBpmCodes(List<String> list) {
        return  programApprovalSnapshotMapper.getByBpmCodes(list);
    }
}
