package com.longfor.itserver.service;

import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.service.base.IAdminService;

import java.util.List;
import java.util.Map;

public interface IProgramEmployeeService extends IAdminService<ProgramEmployee> {

    List<ProgramEmployee> selectTypeList(Map map);

    boolean delEmployee(ProgramEmployee employee,String accountType);

    /**
     * 查询责任人，产品经理，以及测试或开发人员
     * @param map
     * @return
     */
    List<ProgramEmployee> selectPersonList(Map map);
}
