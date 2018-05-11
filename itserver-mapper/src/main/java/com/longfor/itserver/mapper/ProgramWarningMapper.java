package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.ProgramWarning;
import com.longfor.itserver.mapper.base.BeeMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProgramWarningMapper extends BeeMapper<ProgramWarning> {
    List<ProgramWarning> getListByWhere (Map<String,Object> map);

    /**
     * 取当前项目节点的最新的一条手动预警数据
     * @param map
     * @return
     */
    ProgramWarning getOneByWhere (Map<String,Object> map);
    /**
     * 进度预警包括红色跟黄色的
     * @param accountId
     * @return
     */
    int warningSum(@Param(value = "accountId") String accountId);

    /**
     * 风险备注数：一个项目添加两个算两个，关闭的不算
     * @param accountId
     * @return
     */
    int warningRemarksSum(@Param(value = "accountId") String accountId);

    /**
     * 风险备注列表
     * @param map
     * @return
     */
    List<ProgramWarning> warningList (Map<String,Object> map);

    /**
     * 风险备注列表(数量)
     * @param map
     * @return
     */
    int warningListTotal (Map<String,Object> map);
}