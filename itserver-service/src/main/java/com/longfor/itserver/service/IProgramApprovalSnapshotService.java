package com.longfor.itserver.service;

import com.longfor.itserver.entity.ProgramApprovalSnapshot;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/12/22.
 */
public interface IProgramApprovalSnapshotService  extends IAdminService<ProgramApprovalSnapshot> {

    List<ProgramApprovalSnapshot> grayLevelList(Map<String, Object> map);

    List<ProgramApprovalSnapshot> getByBpmCodes(List<String> list);
}
