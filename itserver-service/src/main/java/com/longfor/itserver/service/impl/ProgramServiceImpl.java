package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.*;
import com.longfor.itserver.common.helper.DataPermissionHelper;
import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.common.util.DateUtil;
import com.longfor.itserver.common.util.StringUtil;
import com.longfor.itserver.common.vo.MoApprove.MoApproveListVo;
import com.longfor.itserver.common.vo.MoApprove.MoApproveVo;
import com.longfor.itserver.common.vo.programBpm.ApproveListVo;
import com.longfor.itserver.common.vo.programBpm.ApproveVo;
import com.longfor.itserver.common.vo.programBpm.common.ApplyCreateResultVo;
import com.longfor.itserver.common.vo.programBpm.common.ApplySubmitResultVo;
import com.longfor.itserver.common.vo.programBpm.common.FileVo;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.APIProgramTask;
import com.longfor.itserver.entity.ps.PsProgramDetail;
import com.longfor.itserver.esi.IEdsService;
import com.longfor.itserver.esi.bpm.ProgramBpmUtil;
import com.longfor.itserver.esi.bpm.ProgramBpmUtils;
import com.longfor.itserver.esi.impl.LongforServiceImpl;
import com.longfor.itserver.mapper.*;
import com.longfor.itserver.service.IProgramEmployeeService;
import com.longfor.itserver.service.IProgramService;
import com.longfor.itserver.service.IProgramWarningService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
import jodd.props.Props;
import net.mayee.commons.TimeUtils;
import net.mayee.commons.helper.APIHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author wax Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("ProgramService")
public class ProgramServiceImpl extends AdminBaseService<Program> implements IProgramService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProgramMapper programMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProgramFollowMapper programFollowMapper;

    @Autowired
    private ProgramEmployeeMapper programEmployeeMapper;

    @Autowired
    private IProgramEmployeeService programEmployeeService;

    @Autowired
    private ADSHelper adsHelper;

    @Autowired
    private IEdsService edsService;

    @Autowired
    private ProgramEmployeeChangeLogMapper programEmployeeChangeLogMapper;

    @Autowired
    private ProgramApprovalSnapshotMapper programApprovalSnapshotMapper;
    @Autowired
    private ProgramFileMapper programFileMapper;
    @Autowired
    private IProgramWarningService programWarningService;

    @Autowired
    private LongforServiceImpl longforServiceImpl;


    @Override
    public List<PsProgramDetail> programList(Map map) {
        List<PsProgramDetail> programList = programMapper.programList(map);
        for (PsProgramDetail model:programList) {
            ProgramFollow follow = new ProgramFollow();
            follow.setPfAcc(map.get("accountId").toString());
            follow.setProgramId(model.getId());
            model.setIsFollow(programFollowMapper.selectCount(follow));

            ProgramEmployee programEmployee  = new ProgramEmployee();
            programEmployee.setEmployeeType(AvaStatusEnum.LIABLEAVA.getCode());
            programEmployee.setProgramId(model.getId());
            List<ProgramEmployee> personLiableList = programEmployeeMapper.select(programEmployee);
            model.setPersonLiableList(personLiableList);
            //当前登录人员为管理员或项目责任人时，就可以看到“评估人天”和“总预算”
            String accountId = map.get("accountId").toString();
            String isAdmin = DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0";
            if ("0".equals(isAdmin)){
                Boolean f = true ;
                for(ProgramEmployee emp:personLiableList){
                    if(emp.getAccountId().equals(accountId)){
                        f = false;
                    }
                }
                if (f) {
                    model.setDevWorkload(-1);
                    model.setOverallCost(new BigDecimal(-1));
                }
            }
        }
        return programList;
    }

    @Override
    public List<PsProgramDetail> programList1(Map map) {
        List<PsProgramDetail> programList = programMapper.programList1(map);
//        for (PsProgramDetail model:programList) {
//            ProgramFollow follow = new ProgramFollow();
//            follow.setPfAcc(map.get("accountId").toString());
//            follow.setProgramId(model.getId());
//            model.setIsFollow(programFollowMapper.selectCount(follow));
//
//            ProgramEmployee programEmployee  = new ProgramEmployee();
//            programEmployee.setEmployeeType(AvaStatusEnum.LIABLEAVA.getCode());
//            programEmployee.setProgramId(model.getId());
//            List<ProgramEmployee> personLiableList = programEmployeeMapper.select(programEmployee);
//            model.setPersonLiableList(personLiableList);
//            //当前登录人员为管理员或项目责任人时，就可以看到“评估人天”和“总预算”
//            String accountId = map.get("accountId").toString();
//            String isAdmin = DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0";
//            if ("0".equals(isAdmin)){
//                Boolean f = true ;
//                for(ProgramEmployee emp:personLiableList){
//                    if(emp.getAccountId().equals(accountId)){
//                        f = false;
//                    }
//                }
//                if (f) {
//                    model.setDevWorkload(-1);
//                    model.setOverallCost(new BigDecimal(-1));
//                }
//            }
//        }
        return programList;
    }

    @Override
    public List<Program> productIdList(Map map) {
        return programMapper.productIdList(map);
    }

    @Override
    public List<Program> programLimitList(Map map) {
        return programMapper.programLimitList(map);
    }

    @Override
    public List<ProgramApprovalSnapshot> lookNodes(Map<String,Object> paramMap) {
        List<ProgramApprovalSnapshot> allList =programApprovalSnapshotMapper.grayLevelList(paramMap);
        List<ProgramApprovalSnapshot> resultList = new ArrayList<ProgramApprovalSnapshot>();
        if (allList == null || allList.isEmpty()) {
            return resultList;
        }
        resultList.add(allList.get(0));

        for (int i = 1;i<allList.size();i++) {
            if (paramMap.get("programStatus").equals(ProgramStatusNewEnum.CPPS.getCode())) {
                if (allList.get(i).getApprovalStatus()== ProgramApprovalStatusEnum.SHTG.getCode()
                        || allList.get(i).getApprovalStatus()== ProgramApprovalStatusEnum.SHBH.getCode()
                        || allList.get(i).getApprovalStatus()== ProgramApprovalStatusEnum.BGSHBH.getCode()) {
                    resultList.add(allList.get(i));
                }
            } else {
                if (allList.get(i).getApprovalStatus()== ProgramApprovalStatusEnum.SHTG.getCode()
                        || allList.get(i).getApprovalStatus()== ProgramApprovalStatusEnum.SHBH.getCode()) {
                    resultList.add(allList.get(i));
                }
            }
        }

        for (ProgramApprovalSnapshot model:resultList) {
            Map pMap = new HashMap();
            pMap.put("bpmCode", model.getBpmCode());
            pMap.put("approvalStatus", ProgramApprovalStatusEnum.SHZ.getCode());
            List<ProgramApprovalSnapshot> s = programApprovalSnapshotMapper.grayLevelList(pMap);
            if (s == null || s.isEmpty()) {
                pMap.put("approvalStatus", ProgramApprovalStatusEnum.BGSHZ.getCode());
                s = programApprovalSnapshotMapper.grayLevelList(pMap);
            }
            if (s != null && !s.isEmpty()) {
                this.setProgramApprovalSnapshotInfo(s.get(0),model);
            }
        }
        return resultList;
    }

    @Override
    public List<ProgramApprovalSnapshot> milepost(Map<String,Object> paramMap) {
        List<ProgramApprovalSnapshot> resultList = new ArrayList<ProgramApprovalSnapshot>();
        paramMap.put("approvalStatus",ProgramApprovalStatusEnum.SHTG.getCode());
		/*立项*/
        paramMap.put("programStatus",ProgramStatusNewEnum.LX.getCode());
        List<ProgramApprovalSnapshot> snapshot =programApprovalSnapshotMapper.getListByProgramIdAndStatus(paramMap);
        if (snapshot != null && !snapshot.isEmpty()) {
            resultList.add(snapshot.get(0));
        }
		/*Demo评审*/
        paramMap.put("programStatus",ProgramStatusNewEnum.DPS.getCode());
        snapshot =programApprovalSnapshotMapper.getListByProgramIdAndStatus(paramMap);
        if (snapshot != null && !snapshot.isEmpty()) {
            resultList.add(snapshot.get(0));
        }
		/*招投标申请*/
        paramMap.put("programStatus",ProgramStatusNewEnum.ZTBSQ.getCode());
        snapshot =programApprovalSnapshotMapper.getListByProgramIdAndStatus(paramMap);
        if (snapshot != null && !snapshot.isEmpty()) {
            resultList.add(snapshot.get(0));
        }
		/*中标申请*/
        paramMap.put("programStatus",ProgramStatusNewEnum.ZBSQ.getCode());
        snapshot =programApprovalSnapshotMapper.getListByProgramIdAndStatus(paramMap);
        if (snapshot != null && !snapshot.isEmpty()) {
            resultList.add(snapshot.get(0));
        }
		/*产品评审*/

        paramMap.put("programStatus",ProgramStatusNewEnum.CPPS.getCode());
        snapshot =programApprovalSnapshotMapper.getListByProgramIdAndStatus(paramMap);
        if (snapshot != null && !snapshot.isEmpty()) {
            resultList.add(snapshot.get(0));
        }
		/*开发评审*/
        paramMap.put("programStatus",ProgramStatusNewEnum.KFPS.getCode());
        snapshot =programApprovalSnapshotMapper.getListByProgramIdAndStatus(paramMap);
        if (snapshot != null && !snapshot.isEmpty()) {
            resultList.add(snapshot.get(0));
        }
		/*测试评审*/
        paramMap.put("programStatus",ProgramStatusNewEnum.CSPS.getCode());
        snapshot =programApprovalSnapshotMapper.getListByProgramIdAndStatus(paramMap);
        if (snapshot != null && !snapshot.isEmpty()) {
            resultList.add(snapshot.get(0));
        }
		/*上线计划*/
        paramMap.put("programStatus",ProgramStatusNewEnum.SXPS.getCode());
        snapshot =programApprovalSnapshotMapper.getListByProgramIdAndStatus(paramMap);
        if (snapshot != null && !snapshot.isEmpty()) {
            resultList.add(snapshot.get(0));
        }
		/*灰度发布*/
        paramMap.put("programStatus",ProgramStatusNewEnum.HDFB.getCode());
        snapshot =programApprovalSnapshotMapper.getListByProgramIdAndStatus(paramMap);
        if (snapshot != null && !snapshot.isEmpty()) {
            resultList.add(snapshot.get(0));
        }
        /*全面推广*/
        paramMap.put("programStatus",ProgramStatusNewEnum.QMTG.getCode());
        snapshot =programApprovalSnapshotMapper.getListByProgramIdAndStatus(paramMap);
        if (snapshot != null && !snapshot.isEmpty()) {
            resultList.add(snapshot.get(0));
        }
		/*项目复盘*/
        paramMap.put("programStatus",ProgramStatusNewEnum.XMFP.getCode());
        snapshot =programApprovalSnapshotMapper.getListByProgramIdAndStatus(paramMap);
        if (snapshot != null && !snapshot.isEmpty()) {
            resultList.add(snapshot.get(0));
        }
        return resultList;
    }

    @Override
    public ProgramApprovalSnapshot  getProgramByBpmCode(Map<String,Object> paramMap) {
        List<ProgramApprovalSnapshot> allList =programApprovalSnapshotMapper.grayLevelList(paramMap);
        if (allList==null || allList.isEmpty()) {
            return null;
        }
        ProgramApprovalSnapshot shot = allList.get(0);
        Map pMap = new HashMap();
        pMap.put("bpmCode", shot.getBpmCode());
        if(shot.getProgramStatus()==ProgramStatusNewEnum.YQSX.getCode()
                || shot.getProgramStatus()==ProgramStatusNewEnum.XQBG.getCode()){
            BigDecimal ten = new BigDecimal(100000);
            if (shot.getOverallCost().compareTo(ten) != -1 || shot.getProgramStatus()==ProgramStatusNewEnum.YQSX.getCode()) {//大于10万,取审批变更数据
                //需求变更或延期时，取变更审核中的历史数据
                pMap.put("approvalStatus", ProgramApprovalStatusEnum.BGSHZ.getCode());
            } else {
                //需求变更走通知时，取审核通过的历史数据（通知没有审核中的数据）
                pMap.put("approvalStatus", ProgramApprovalStatusEnum.SHTG.getCode());
            }
        } else {
            //九步法，取审核中的历史数据
            pMap.put("approvalStatus", ProgramApprovalStatusEnum.SHZ.getCode());
        }
        List<ProgramApprovalSnapshot> s = programApprovalSnapshotMapper.grayLevelList(pMap);
        //取s.get(0)对象，是去找shot对应的文件信息
        if (s != null && !s.isEmpty()) {
            this.setProgramApprovalSnapshotInfo(s.get(0),shot);
        }
        return shot;
    }

    @Override
    @Transactional
    public boolean addProgram(Map map) throws Exception{
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        Integer accountType = AccountUitl.getAccountType(map);
        Program program = JSONObject.toJavaObject(json, Program.class);
        Product product = productMapper.selectByPrimaryKey(program.getProductId());
        if(product == null){
            return false;
        }
        program.setProgramStatus(ProgramStatusNewEnum.WLX.getCode());
        program.setProductName(product.getName());
        program.setProductCode(product.getCode());
        program.setCreateTime(TimeUtils.getTodayByDateTime());
        program.setModifiedTime(TimeUtils.getTodayByDateTime());
        program.setAccountType(accountType);
        program.setApprovalStatus(0);
        program.setNewCode(this.generateProgramNewCode());
        programMapper.insert(program);
        //记录新增项目时的信息
        ProgramApprovalSnapshot pas = new ProgramApprovalSnapshot();
        this.copyProperties(pas,program);
        programApprovalSnapshotMapper.insert(pas);

        // 项目责任人
        String jsonArrPl = json.get("personLiableList").toString();
        if (!"".equals(jsonArrPl)) {
            getAccountLongfor(program, jsonArrPl, "0");
        }
        // 产品经理
        String jsonArrPm = json.get("productManagerList").toString();
        if (!"".equals(jsonArrPm)) {
            getAccountLongfor(program, jsonArrPm, "1");

        }
        // 项目经理
        String jsonArrPMl = json.get("programManagerList").toString();
        if (!"".equals(jsonArrPMl)) {
            getAccountLongfor(program, jsonArrPMl, "2");
        }
        // 开发人员
        String jsonArrDe = json.get("developerList").toString();
        if (!"".equals(jsonArrDe)) {
            getAccountLongfor(program, jsonArrDe, "3");
        }
        // UED人员
        String jsonArrUed = json.get("uedList").toString();
        if (!"".equals(jsonArrUed)) {
            getAccountLongfor(program, jsonArrUed, "4");
        }
        // 测试人员
        String jsonArrTest = json.get("testingList").toString();
        if (!"".equals(jsonArrTest)) {
            getAccountLongfor(program, jsonArrTest, "5");
        }
        // 业务人员
        String jsonArrBusiness = json.get("businessList").toString();
        if (!"".equals(jsonArrBusiness)) {
            getAccountLongfor(program, jsonArrBusiness, "6");
        }

        // 运维人员
        String jsonArrOperation = json.get("operationList").toString();
        if (!"".equals(jsonArrOperation)) {
            getAccountLongfor(program, jsonArrOperation, "7");
        }

        // 运营人员
        String jsonArrOperate = json.get("operateList").toString();
        if (!"".equals(jsonArrOperate)) {
            getAccountLongfor(program, jsonArrOperate, "8");
        }

        //先生成变动日志
        List<String> changeLogTextList = getChangeLogText(null, program);
        /*添加日志*/
        for(String text : changeLogTextList){
            ProgramEmployeeChangeLog employeeChangeLog = new ProgramEmployeeChangeLog();
            employeeChangeLog.setProgramId(program.getId());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setActionChangeInfo(text);
            employeeChangeLog.setModifiedAccountId(program.getModifiedAccountId());
            employeeChangeLog.setModifiedName(program.getModifiedName());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setAccountType(accountType);
            programEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
        }
        return true;
    }

    public boolean getAccountLongfor(Program program, String str, String id) {
        String[] strArr = str.split(",");
        for (int i = 0; i < strArr.length; i++) {
            String loginName = strArr[i].toString();
            AccountLongfor accountLongfor =
                    AccountUitl.getAccountByAccountTypes(loginName,adsHelper);
            if (accountLongfor != null) {
                ProgramEmployee pe = new ProgramEmployee();
                pe.setProgramId(program.getId());
                pe.setAccountId(accountLongfor.getLoginName());
                pe.setEmployeeCode(StringUtil.getLongValue(accountLongfor.getPsEmployeeCode()));
                pe.setEmployeeName(accountLongfor.getName());
                pe.setFullDeptPath(accountLongfor.getPsDeptFullName());
                if ("0".equals(id)) {
                    pe.setEmployeeType(AvaStatusEnum.LIABLEAVA.getCode());
                } else if ("1".equals(id)) {
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.PRODAVA.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.PRODAVA.getText());
                } else if ("2".equals(id)) {
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.PROGAVA.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.PROGAVA.getText());
                } else if ("3".equals(id)) {
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.DEVEAVA.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.DEVEAVA.getText());
                } else if ("4".equals(id)) {
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.UEDAVA.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.UEDAVA.getText());
                } else if ("5".equals(id)) {
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.TESTINGAVA.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.TESTINGAVA.getText());
                } else if ("6".equals(id)) {
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.BUSINESSAVA.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.BUSINESSAVA.getText());
                }else if ("7".equals(id)) {
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.OPERATION.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.OPERATION.getText());
                }else if ("8".equals(id)) {
                    pe.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                    pe.setEmployeeTypeId(new Long(AvaStatusEnum.OPERATE.getCode()));
                    pe.setEmployeeTypeText(AvaStatusEnum.OPERATE.getText());
                }
                pe.setStatus(AvaStatusEnum.AVA.getCode());
                pe.setCreateTime(TimeUtils.getTodayByDateTime());
                pe.setModifiedTime(TimeUtils.getTodayByDateTime());
                programEmployeeMapper.insert(pe);
            }
        }
        return true;
    }

    @Override
    public List<Program> inProgramId(String likeProgram) {
        return programMapper.inProgramId(likeProgram);
    }

    @Override
    public Program getProgramId(long id) {
        Program program = programMapper.getProgramId(id);
        if (null != program) {
            Product product = productMapper.selectByPrimaryKey(program.getProductId());
            if(product!=null){
                program.setProductName(product.getName());
                program.setLabel(product.getLabel());
                program.setLabelName(product.getLabelName());
            }
        }
        return program;
    }

    @Override
    public Program getProgram(long id) {
        Map map = new HashMap();
        map.put("id", id);
        Program program = programMapper.getProgram(map);
        if (null != program) {
            Product product = productMapper.selectByPrimaryKey(program.getProductId());
            if(product!=null){
                program.setProductName(product.getName());
                program.setLabel(product.getLabel());
                program.setLabelName(product.getLabelName());
            }
        }
//        Program program = programMapper.selectByPrimaryKey(id);
        //获取节点的预警值
//        Map map = new HashMap();
//        map.put("id", program.getId());
//        if(program.getApprovalStatus()== ProgramApprovalStatusEnum.SHTG.getCode()) {
//            if (program.getProgramStatus() == ProgramStatusNewEnum.WLX.getCode()) {
//                map.put("programStatus", ProgramStatusNewEnum.LX.getCode());
//            }
//            if (program.getProgramStatus() == ProgramStatusNewEnum.LX.getCode()) {
//                map.put("programStatus", ProgramStatusNewEnum.DPS.getCode());
//            }
//            if (program.getProgramStatus() == ProgramStatusNewEnum.DPS.getCode()) {
//                map.put("programStatus", ProgramStatusNewEnum.CPPS.getCode());
//            }
//            if (program.getProgramStatus() == ProgramStatusNewEnum.CPPS.getCode()) {
//                map.put("programStatus", ProgramStatusNewEnum.KFPS.getCode());
//            }
//            if (program.getProgramStatus() == ProgramStatusNewEnum.KFPS.getCode()) {
//                map.put("programStatus", ProgramStatusNewEnum.CSPS.getCode());
//            }
//            if (program.getProgramStatus() == ProgramStatusNewEnum.CSPS.getCode()) {
//                map.put("programStatus", ProgramStatusNewEnum.SXPS.getCode());
//            }
//            if (program.getProgramStatus() == ProgramStatusNewEnum.SXPS.getCode()) {
//                map.put("programStatus", ProgramStatusNewEnum.HDFB.getCode());
//            }
//            if (program.getProgramStatus() == ProgramStatusNewEnum.HDFB.getCode()) {
//                map.put("programStatus", ProgramStatusNewEnum.QMTG.getCode());
//            }
//            if (program.getProgramStatus() == ProgramStatusNewEnum.QMTG.getCode()) {
//                map.put("programStatus", ProgramStatusNewEnum.XMFP.getCode());
//            }
//            if (program.getProgramStatus() == ProgramStatusNewEnum.XMFP.getCode()) {
//                map.put("programStatus", ProgramStatusNewEnum.WC.getCode());
//            }
//        }
        return program;
    }

    /**
     * 删除操作
     *
     * @param type
     * @param program
     * @param program
     */
    private void deleteByParam(int type, long typeId, Program program) {
        ProgramEmployee programEmployee = new ProgramEmployee();
        programEmployee.setEmployeeType(type);
        if (0 != typeId) {
            programEmployee.setEmployeeTypeId(typeId);
        }
        programEmployee.setProgramId(program.getId());
        programEmployeeMapper.delete(programEmployee);
    }

    @Override
    @Transactional
    public boolean updateProgram(Map map) {
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        Program program = JSONObject.toJavaObject(json, Program.class);
        Program selectOneProgram = programMapper.selectByPrimaryKey(program.getId());
        Product product = productMapper.selectByPrimaryKey(program.getProductId());
        Integer accountType = AccountUitl.getAccountType(map);
        //先生成变动日志
        List<String> changeLogTextList = getChangeLogText(selectOneProgram, program);

        if (null == selectOneProgram) {
            return false;
        }
        selectOneProgram.setName(program.getName());
        selectOneProgram.setProductId(product.getId());
        selectOneProgram.setProductName(product.getName());
        selectOneProgram.setProductCode(product.getCode());
        selectOneProgram.setDescp(program.getDescp());
        selectOneProgram.setLikeProduct(program.getLikeProduct());
        selectOneProgram.setLikeProgram(program.getLikeProgram());
        selectOneProgram.setType(program.getType());
        selectOneProgram.setAccountType(accountType);
        selectOneProgram.setModifiedTime(TimeUtils.getTodayByDateTime());
        //节点日期
        if(program.getCommitDate()!=null)selectOneProgram.setCommitDate(program.getCommitDate());
        if(program.getDemoApprovalDate()!=null)selectOneProgram.setDemoApprovalDate(program.getDemoApprovalDate());
        if(program.getBiddingDate()!=null)selectOneProgram.setBiddingDate(program.getBiddingDate());
        if(program.getWinningBidDate()!=null)selectOneProgram.setWinningBidDate(program.getWinningBidDate());
        if(program.getProdApprovalDate()!=null)selectOneProgram.setProdApprovalDate(program.getProdApprovalDate());
        if(program.getDevApprovalDate()!=null)selectOneProgram.setDevApprovalDate(program.getDevApprovalDate());
        if(program.getTestApprovalDate()!=null)selectOneProgram.setTestApprovalDate(program.getTestApprovalDate());
        if(program.getOnlinePlanDate()!=null)selectOneProgram.setOnlinePlanDate(program.getOnlinePlanDate());
        if(program.getGrayReleaseDate()!=null)selectOneProgram.setGrayReleaseDate(program.getGrayReleaseDate());
        if(program.getReplayDate()!=null)selectOneProgram.setReplayDate(program.getReplayDate());
        if(program.getAllExtensionDate()!=null)selectOneProgram.setAllExtensionDate(program.getAllExtensionDate());
        //节点日期
        programMapper.updateByPrimaryKey(selectOneProgram);

        // 项目责任人
        String jsonArrPl = json.get("personLiableList").toString();
        if (!"".equals(jsonArrPl)) {
            deleteByParam(1, 0, program);
            getAccountLongfor(program, jsonArrPl, "0");
        }
        // 产品经理
        String jsonArrPm = json.get("productManagerList").toString();
        if (!"".equals(jsonArrPm)) {
            deleteByParam(2, 1, program);
            getAccountLongfor(program, jsonArrPm, "1");
        }
        // 项目经理
        String jsonArrPMl = json.get("programManagerList").toString();
        if (!"".equals(jsonArrPMl)) {
            deleteByParam(2, 2, program);
            getAccountLongfor(program, jsonArrPMl, "2");
        }
        // 开发人员
        String jsonArrDe = json.get("developerList").toString();
        if (!"".equals(jsonArrDe)) {
            deleteByParam(2, 3, program);
            getAccountLongfor(program, jsonArrDe, "3");
        }
        // UED人员
        String jsonArrUed = json.get("uedList").toString();
        if (!"".equals(jsonArrUed)) {
            deleteByParam(2, 4, program);
            getAccountLongfor(program, jsonArrUed, "4");
        }
        // 测试人员
        String jsonArrTest = json.get("testingList").toString();
        if (!"".equals(jsonArrTest)) {
            deleteByParam(2, 5, program);
            getAccountLongfor(program, jsonArrTest, "5");
        }
        // 业务人员
        String jsonArrBusiness = json.get("businessList").toString();
        if (!"".equals(jsonArrBusiness)) {
            deleteByParam(2, 6, program);
            getAccountLongfor(program, jsonArrBusiness, "6");
        }

        // 运维人员
        String jsonArrOperation = json.get("operationList").toString();
        if (!"".equals(jsonArrOperation)) {
            deleteByParam(2, 7, program);
            getAccountLongfor(program, jsonArrOperation, "7");
        }

        // 运营人员
        String jsonArrOperate = json.get("operateList").toString();
        if (!"".equals(jsonArrOperate)) {
            deleteByParam(2, 8, program);
            getAccountLongfor(program, jsonArrOperate, "8");
        }

		/*添加日志*/
        for(String text : changeLogTextList){
            ProgramEmployeeChangeLog employeeChangeLog = new ProgramEmployeeChangeLog();
            employeeChangeLog.setProgramId(program.getId());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setActionChangeInfo(text);
            employeeChangeLog.setModifiedAccountId(program.getModifiedAccountId());
            employeeChangeLog.setModifiedName(program.getModifiedName());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setAccountType(accountType);
            programEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
        }

        return true;
    }

    private List<String> getChangeLogText(Program oldProgram, Program newProgram){
        List<String> textList = new ArrayList<>();

        if(oldProgram == null && newProgram != null){
            StringBuilder sb = new StringBuilder();
            sb.append(newProgram.getModifiedName())
                    .append(" 创建项目");
            textList.add(sb.toString());
            return textList;
        }

        if(newProgram == null){
            return textList;
        }

        if(!Objects.equals(oldProgram.getProgramStatus(), newProgram.getProgramStatus())
                || !Objects.equals(oldProgram.getType(), newProgram.getType())){
            StringBuilder sb = new StringBuilder();
            //判断code是否有效
//            if(Objects.nonNull(ProgramStatusEnum.getByCode(newProgram.getProgramStatus())) && !Objects.equals(oldProgram.getProgramStatus(), newProgram.getProgramStatus())){
//                sb.append(newProgram.getModifiedName())
//                        .append(" 将 项目状态 从 [")
//                        .append(ProgramStatusEnum.getByCode(oldProgram.getProgramStatus()).getText())
//                        .append("] 更新为 [")
//                        .append(ProgramStatusEnum.getByCode(newProgram.getProgramStatus()).getText())
//                        .append("] ");
//            }
            if(!Objects.equals(oldProgram.getType(), newProgram.getType())){
                if(StringUtils.isNotBlank(sb.toString())){
                    sb.append(",");
                } else {
                    sb.append(newProgram.getModifiedName());
                }
                sb.append(" 将 公开性 从 [")
                        .append(PublicTypeEnum.getByCode(oldProgram.getType()).getText())
                        .append("] 更新为 [")
                        .append(PublicTypeEnum.getByCode(newProgram.getType()).getText())
                        .append("]");
            }
            if (!sb.toString().isEmpty()) {
                textList.add(sb.toString());
            }
        }

        //立项时间
//        if(!TimeUtils.sameDate(oldProgram.getCommitDate(), newProgram.getCommitDate())){
//            StringBuilder sb = new StringBuilder();
//            sb.append(newProgram.getModifiedName());
//            sb.append(" 将 立项时间 从 [")
//                    .append(TimeUtils.getTime(oldProgram.getCommitDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                    .append("] 更新为 [")
//                    .append(TimeUtils.getTime(newProgram.getCommitDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                    .append("]");
//            textList.add(sb.toString());
//        }
        //启动时间
//        if(!TimeUtils.sameDate(oldProgram.getStartDate(), newProgram.getStartDate())){
//            StringBuilder sb = new StringBuilder();
//            sb.append(newProgram.getModifiedName());
//            sb.append(" 将 启动时间 从 [")
//                    .append(TimeUtils.getTime(oldProgram.getStartDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                    .append("] 更新为 [")
//                    .append(TimeUtils.getTime(newProgram.getStartDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                    .append("]");
//            textList.add(sb.toString());
//        }
        //UED评审时间
//        if(!"".equals(oldProgram.getUedDate()) && oldProgram.getUedDate() != null){
//            if(!TimeUtils.sameDate(oldProgram.getUedDate(), newProgram.getUedDate())){
//                StringBuilder sb = new StringBuilder();
//                sb.append(newProgram.getModifiedName());
//                sb.append(" 将 UED评审时间 从 [")
//                        .append(TimeUtils.getTime(oldProgram.getUedDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                        .append("] 更新为 [")
//                        .append(TimeUtils.getTime(newProgram.getUedDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                        .append("]");
//                textList.add(sb.toString());
//            }
//        }

        //架构评审时间
//        if(!"".equals(oldProgram.getArchitectureDate()) && oldProgram.getArchitectureDate() != null) {
//            if (!TimeUtils.sameDate(oldProgram.getArchitectureDate(), newProgram.getArchitectureDate())) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(newProgram.getModifiedName());
//                sb.append(" 将 架构评审时间 从 [")
//                        .append(TimeUtils.getTime(oldProgram.getArchitectureDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                        .append("] 更新为 [")
//                        .append(TimeUtils.getTime(newProgram.getArchitectureDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                        .append("]");
//                textList.add(sb.toString());
//            }
//        }
        //灰度时间
//        if(!TimeUtils.sameDate(oldProgram.getGrayReleaseDate(), newProgram.getGrayReleaseDate())){
//            StringBuilder sb = new StringBuilder();
//            sb.append(newProgram.getModifiedName());
//            sb.append(" 将 灰度时间 从 [")
//                    .append(TimeUtils.getTime(oldProgram.getGrayReleaseDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                    .append("] 更新为 [")
//                    .append(TimeUtils.getTime(newProgram.getGrayReleaseDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                    .append("]");
//            textList.add(sb.toString());
//        }
        //发布时间
//        if(!TimeUtils.sameDate(oldProgram.getReleaseDate(), newProgram.getReleaseDate())){
//            StringBuilder sb = new StringBuilder();
//            sb.append(newProgram.getModifiedName());
//            sb.append(" 将 发布时间 从 [")
//                    .append(TimeUtils.getTime(oldProgram.getReleaseDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                    .append("] 更新为 [")
//                    .append(TimeUtils.getTime(newProgram.getReleaseDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
//                    .append("]");
//            textList.add(sb.toString());
//        }

        if(!Objects.equals(oldProgram.getName(), newProgram.getName())
                || !Objects.equals(oldProgram.getLikeProduct(), newProgram.getLikeProduct())
                || !Objects.equals(oldProgram.getDescp(), newProgram.getDescp())){
            StringBuilder sb = new StringBuilder();
            sb.append(newProgram.getModifiedName())
                    .append(" 修改了项目基本信息");
            textList.add(sb.toString());
        }

        return textList;
    }

    private String statusLog(Program oldProgram,Program newProgram){
        StringBuilder sb = new StringBuilder();
        if( ProgramStatusEnum.getByCode(oldProgram.getProgramStatus()) != null && !Objects.equals(oldProgram.getProgramStatus(), newProgram.getProgramStatus())){
            sb.append(newProgram.getModifiedName())
                    .append(" 将 项目状态 从 [")
                    .append(ProgramStatusEnum.getByCode(oldProgram.getProgramStatus()).getText())
                    .append("] 更新为 [")
                    .append(ProgramStatusEnum.getByCode(newProgram.getProgramStatus()).getText())
                    .append("] ");
        }
        return sb.toString();
    }

    @Override
    public List<Program> productIdAllList(Map parsmsMap) {
        List<Program> programList =	programMapper.getListByMap(parsmsMap);
        Product product = productMapper.selectByPrimaryKey(Long.valueOf((String) parsmsMap.get("productId")));
        if(null != product) {
            for (Program p : programList) {
                p.setProductName(product.getName());
            }
        }
        return programList;
    }

    @Override
    @Transactional
    public boolean updateStatus(Map paramsMap) {
        JSONObject jsonObject = (JSONObject)JSONObject.toJSON(paramsMap);
        Long programId=Long.valueOf((String) paramsMap.get("programId"));
        Program	oldProgram = programMapper.selectByPrimaryKey(programId);
        Program newProgram = new Program();
        newProgram.setId(programId);
        newProgram.setProgramStatus(Integer.valueOf((String) jsonObject.get("status")));
        newProgram.setModifiedName((String) paramsMap.get("modifiedName"));
        newProgram.setModifiedAccountId((String) paramsMap.get("modifiedAccountId"));

        String text = statusLog(oldProgram,newProgram);
        if(StringUtils.isNotBlank(text)) {
            ProgramEmployeeChangeLog log = new ProgramEmployeeChangeLog();
            log.setProgramId(programId);
            log.setActionChangeInfo(text);
            log.setModifiedAccountId((String) paramsMap.get("modifiedAccountId"));
            log.setModifiedName((String) paramsMap.get("modifiedName"));
            log.setCreateTime(TimeUtils.getTodayByDateTime());
            log.setModifiedTime(TimeUtils.getTodayByDateTime());
            log.setAccountType(AccountUitl.getAccountType(paramsMap));
            programEmployeeChangeLogMapper.insertUseGeneratedKeys(log);
        }

        oldProgram.setProgramStatus(newProgram.getProgramStatus());
        oldProgram.setModifiedTime(TimeUtils.getTodayByDateTime());
        oldProgram.setAccountType(AccountUitl.getAccountType(paramsMap));
        programMapper.updateByPrimaryKey(oldProgram);
        return true;
    }


    /***
     * 审核通过
     */
    @Override
    @Transactional(value="transactionManager")
    public void approvalPass(Map<String, String> paramsMap,Program program) {
        try{
            //提交流程
            ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtil.applySumbmitWorkItem(
                    paramsMap.get("modifiedAccountId"),paramsMap.get("workItemId"),paramsMap.get("suggestion"));
            if(pplySubmitResultVo.getIsSuccess().equals("false")){
                LOG.error("提交流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("提交流程失败");
            }
            if(!"ALLEND".equals(pplySubmitResultVo.getInstanceState())){
                return;
            }

            Date now = new Date();
            String bpmCode = paramsMap.get("instanceId");

            //获取快照表最新一条数据
            Map<String,Object> paraMap = new HashMap<>();
            paraMap.put("bpmCode",bpmCode);
            List<ProgramApprovalSnapshot> tmpList = programApprovalSnapshotMapper.grayLevelList(paraMap);
            ProgramApprovalSnapshot programApprovalSnapshot = tmpList.get(0);
            //取最新的一条项目状态
            int oldProgramStatus = programApprovalSnapshot.getProgramStatus();
            int currProgramStatus = program.getProgramStatus();
            int currApprovalStatus = program.getApprovalStatus();

            String newCode = programApprovalSnapshot.getNewCode();
            String applyAccount = programApprovalSnapshot.getApplyAccount();

            this.copyPropertiesToProgram(program,programApprovalSnapshot);

            //更新项目表
            //延期和需求变更审核通过时，项目状态不变
            if (oldProgramStatus == ProgramStatusNewEnum.XQBG.getCode()
                    || oldProgramStatus == ProgramStatusNewEnum.YQSX.getCode()) {
                program.setApprovalStatus(currApprovalStatus);
            } else {
                program.setApprovalStatus(ProgramApprovalStatusEnum.SHTG.getCode());
            }
            program.setProgramStatus(currProgramStatus);
            program.setModifiedTime(now);
            getApprovelAfterProgramStatus(programApprovalSnapshot.getBpmCode(),program,programApprovalSnapshot.getProgramStatus());
            //实际立项时间
            if(program.getProgramStatus()==ProgramStatusNewEnum.LX.getCode()){
                program.setActualCommitDate(now);
            }
            //实际Demo评审时间
            if(program.getProgramStatus()==ProgramStatusNewEnum.DPS.getCode()){
                program.setActualDemoApprovalDate(now);
            }
            //实际招投标时间
            if(program.getProgramStatus()==ProgramStatusNewEnum.ZTBSQ.getCode()){
                program.setActualBiddingDate(now);
            }
            //实际中标申请时间
            if(program.getProgramStatus()==ProgramStatusNewEnum.ZBSQ.getCode()){
                program.setActualWinningBidDate(now);
            }
            //实际产品评审时间
            if(program.getProgramStatus()==ProgramStatusNewEnum.CPPS.getCode()){
                program.setActualProdApprovalDate(now);
            }
            //实际开发评审时间
            if(program.getProgramStatus()==ProgramStatusNewEnum.KFPS.getCode()){
                program.setActualDevApprovalDate(now);
            }
            //实际测试评审时间
            if(program.getProgramStatus()==ProgramStatusNewEnum.CSPS.getCode()){
                program.setActualTestApprovalDate(now);
            }
            //实际上线计划时间
            if(program.getProgramStatus()==ProgramStatusNewEnum.SXPS.getCode()){
                program.setActualOnlinePlanDate(now);
            }
            //实际灰度发布时间
            if(program.getProgramStatus()==ProgramStatusNewEnum.HDFB.getCode()){
                program.setActualGrayReleaseDate(now);
            }
            //实际全面推广时间
            if(program.getProgramStatus()==ProgramStatusNewEnum.QMTG.getCode()){
                program.setActualAllExtensionDate(now);
            }
            //实际项目复盘时间
            if(program.getProgramStatus()==ProgramStatusNewEnum.XMFP.getCode()){
                program.setActualReplayDate(now);
            }
            programMapper.updateByPrimaryKey(program);

            //program快照表
            this.copyProperties(programApprovalSnapshot,program);
            if (oldProgramStatus == ProgramStatusNewEnum.XQBG.getCode()
                    || oldProgramStatus == ProgramStatusNewEnum.YQSX.getCode()) {
                programApprovalSnapshot.setProgramStatus(oldProgramStatus);
            }
            programApprovalSnapshot.setNewCode(newCode);
            programApprovalSnapshot.setApplyAccount(applyAccount);
            programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.SHTG.getCode());
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setSuggestion(paramsMap.get("suggestion"));
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);
            //如果是项目复盘的话，数据库里添加一条项目复盘审核通过的信息
            if(oldProgramStatus==ProgramStatusNewEnum.XMFP.getCode()){
                programApprovalSnapshot.setId(null);
                programApprovalSnapshot.setProgramStatus(ProgramStatusNewEnum.XMFP.getCode());
                programApprovalSnapshotMapper.insert(programApprovalSnapshot);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发生异常");
        }
    }

    /***
     * 审核驳回
     */
    @Override
    @Transactional(value="transactionManager")
    public void approvalRebut(Map<String, String> paramsMap,Program program) {
        try{
            //提交流程
            ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtil.returnWorkflowToStart(
                    paramsMap.get("modifiedAccountId"),paramsMap.get("workItemId"),paramsMap.get("suggestion"));
            if(pplySubmitResultVo.getIsSuccess().equals("false")){
                LOG.error("提交流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("提交流程失败");
            }

            //获取快照表最新一条数据
            Map<String,Object> paraMap = new HashMap<>();
            paraMap.put("bpmCode",paramsMap.get("instanceId"));
            List<ProgramApprovalSnapshot> tmpList = programApprovalSnapshotMapper.grayLevelList(paraMap);
            ProgramApprovalSnapshot programApprovalSnapshot = tmpList.get(0);
            int oldProgramStatus = programApprovalSnapshot.getProgramStatus();

            String newCode = programApprovalSnapshot.getNewCode();
            String applyAccount = programApprovalSnapshot.getApplyAccount();
            Date now = new Date();

            //更新项目表
            // 延期和需求变更审核不通过时，项目状态不变
            if (oldProgramStatus != ProgramStatusNewEnum.XQBG.getCode()
                    && oldProgramStatus != ProgramStatusNewEnum.YQSX.getCode()
                    && oldProgramStatus != ProgramStatusNewEnum.ZZ.getCode()) {
                program.setApprovalStatus(ProgramApprovalStatusEnum.SHBH.getCode());
            }
            program.setModifiedTime(now);
            programMapper.updateByPrimaryKey(program);

            programApprovalSnapshot.setId(null);
//            programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.SHBH.getCode());
            if (oldProgramStatus == ProgramStatusNewEnum.XQBG.getCode()
                    || oldProgramStatus == ProgramStatusNewEnum.YQSX.getCode()) {
                programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.BGSHBH.getCode());
            } else {
                programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.SHBH.getCode());
            }
            programApprovalSnapshot.setNewCode(newCode);
            programApprovalSnapshot.setApplyAccount(applyAccount);
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setBpmCode(paramsMap.get("instanceId"));
            programApprovalSnapshot.setSuggestion(paramsMap.get("suggestion"));
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发生异常");
        }
    }

    /***
     * 终止流程
     */
    @Override
    @Transactional(value="transactionManager")
    public void cancelInstance(Map<String, String> paramsMap,Program program) {
        try{
            ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtil.returnWorkflowToStart(
                    paramsMap.get("modifiedAccountId"),paramsMap.get("workItemId"),paramsMap.get("suggestion"));
            if(pplySubmitResultVo.getIsSuccess().equals("false")){
                LOG.error("提交流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("提交流程失败");
            }

            //提交流程
            boolean f = ProgramBpmUtils.cancelInstance(
                    "admin",paramsMap.get("instanceId"),null,"1");
            if(!f){
                LOG.error("提交流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("提交流程失败");
            }

            //获取快照表最新一条数据
            Map<String,Object> paraMap = new HashMap<>();
            paraMap.put("bpmCode",paramsMap.get("instanceId"));
            List<ProgramApprovalSnapshot> tmpList = programApprovalSnapshotMapper.grayLevelList(paraMap);
            ProgramApprovalSnapshot programApprovalSnapshot = tmpList.get(0);
            int oldProgramStatus = programApprovalSnapshot.getProgramStatus();

            String newCode = programApprovalSnapshot.getNewCode();
            String applyAccount = programApprovalSnapshot.getApplyAccount();
            Date now = new Date();

            //更新项目表
            // 延期和需求变更审核不通过时，项目状态不变
            if (oldProgramStatus != ProgramStatusNewEnum.XQBG.getCode()
                    && oldProgramStatus != ProgramStatusNewEnum.YQSX.getCode()
                    && oldProgramStatus != ProgramStatusNewEnum.ZZ.getCode()) {
                program.setApprovalStatus(ProgramApprovalStatusEnum.SHBH.getCode());
            }
            program.setModifiedTime(now);
            programMapper.updateByPrimaryKey(program);

            programApprovalSnapshot.setId(null);
//            programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.SHBH.getCode());
            if (oldProgramStatus == ProgramStatusNewEnum.XQBG.getCode()
                    || oldProgramStatus == ProgramStatusNewEnum.YQSX.getCode()) {
                programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.BGSHBH.getCode());
            } else {
                programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.SHBH.getCode());
            }
            programApprovalSnapshot.setNewCode(newCode);
            programApprovalSnapshot.setApplyAccount(applyAccount);
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setBpmCode(paramsMap.get("instanceId"));
            programApprovalSnapshot.setSuggestion(paramsMap.get("suggestion"));
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发生异常");
        }
    }

    /***
     * 延期上线
     */
    @Override
    @Transactional(value="transactionManager")
    public void delay(Map<String, String> paramsMap,Program program) {
        try{
            Date now = new Date();
            //延期原因，1：需求变更，2：其他原因,需走审批
//            String causeDelay = paramsMap.get("causeDelay");
            //人员转换
            paramsMap.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
            paramsMap.put("businessList",edsService.getEmpGuidByPfAcc(paramsMap.get("businessList")));//业务人员转成GUID
            paramsMap.put("developerList",edsService.getEmpGuidByPfAcc(paramsMap.get("developerList")));//开发人员转成GUID
            if("1".equals(paramsMap.get("reportPoor"))){
                paramsMap.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
            }
            paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.YQSX.getText());//流程名称
            //创建流程
            ApplyCreateResultVo applyCreateResultVo = new ApplyCreateResultVo();
            applyCreateResultVo = ProgramBpmUtils.submitDelayOnline(paramsMap);
            if (!applyCreateResultVo.isSuccess()) {
                LOG.error("创建流程失败:" + JSON.toJSONString(paramsMap) + "-----------------------");
                throw new RuntimeException("创建流程失败");
            }

            //program表
//            program.setApprovalStatus(ProgramApprovalStatusEnum.BGSHZ.getCode());
            program.setAccountType(Integer.parseInt(paramsMap.get("accountType")));
            program.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
            program.setModifiedName(paramsMap.get("modifiedName"));
            programMapper.updateByPrimaryKey(program);

            if(StringUtils.isNotBlank(paramsMap.get("grayReleaseDate")))program.setGrayReleaseDate(DateUtil.string2Date(paramsMap.get("grayReleaseDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNotBlank(paramsMap.get("demandDate")))program.setProdApprovalDate(DateUtil.string2Date(paramsMap.get("demandDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNotBlank(paramsMap.get("developmentDate")))program.setDevApprovalDate(DateUtil.string2Date(paramsMap.get("developmentDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNotBlank(paramsMap.get("testReviewDate")))program.setTestApprovalDate(DateUtil.string2Date(paramsMap.get("testReviewDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNotBlank(paramsMap.get("onlineDate")))program.setOnlinePlanDate(DateUtil.string2Date(paramsMap.get("onlineDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNotBlank(paramsMap.get("replayDate")))program.setReplayDate(DateUtil.string2Date(paramsMap.get("replayDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNotBlank(paramsMap.get("allExtensionDate")))program.setAllExtensionDate(DateUtil.string2Date(paramsMap.get("allExtensionDate"),DateUtil.PATTERN_DATE));

//            program.setAccountType(Integer.parseInt(paramsMap.get("accountType")));
//            program.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
//            program.setModifiedName(paramsMap.get("modifiedName"));
//            programMapper.updateByPrimaryKey(program);

            //program快照表
            ProgramApprovalSnapshot programApprovalSnapshot = new ProgramApprovalSnapshot();
            this.copyProperties(programApprovalSnapshot,program);
            programApprovalSnapshot.setBpmCode(applyCreateResultVo.getInstanceID());
            programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.BGSHZ.getCode());
            programApprovalSnapshot.setProgramStatus(ProgramStatusNewEnum.YQSX.getCode());
            programApprovalSnapshot.setRemark(paramsMap.get("remark"));
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setApplyAccount(paramsMap.get("modifiedAccountId"));
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);

            //附件表
            this.dealFileList(paramsMap.get("fileList"),program.getId(),ProgramStatusNewEnum.YQSX.getCode(),programApprovalSnapshot.getId());


            //激活流程
            ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtils.approvePass(
                    paramsMap.get("modifiedAccountId"),applyCreateResultVo.getWorkItemID(),null);
            if(pplySubmitResultVo.getIsSuccess().equals("false")){
                LOG.error("激活流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("激活流程失败");
            }
            //日志记录审批记录
            String text = paramsMap.get("modifiedName") + "提交了"+ ProgramStatusNewEnum.YQSX.getText();
            ProgramEmployeeChangeLog employeeChangeLog = new ProgramEmployeeChangeLog();
            employeeChangeLog.setProgramId(program.getId());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setActionChangeInfo(text);
            employeeChangeLog.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
            employeeChangeLog.setModifiedName(paramsMap.get("modifiedName"));
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setAccountType(0);
            programEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发生异常");
        }
    }

    /***
     * 需求变更
     */
    @Override
    @Transactional(value="transactionManager")
    public void demandChange(Map<String, String> paramsMap,Program program) {
        try{
            Date now = new Date();
            BigDecimal  overallCost = new BigDecimal(paramsMap.get("overallCost"));
            BigDecimal ten = new BigDecimal(100000);
            //人员转换
            paramsMap.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
            paramsMap.put("businessList",edsService.getEmpGuidByPfAcc(paramsMap.get("businessList")));//业务人员转成GUID
            paramsMap.put("developerList",edsService.getEmpGuidByPfAcc(paramsMap.get("developerList")));//开发人员转成GUID
            if("1".equals(paramsMap.get("reportPoor"))){
                paramsMap.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
            }
            if("1".equals(paramsMap.get("tApproval"))){
                paramsMap.put("lorf",edsService.getEmpGuidByPfAcc("lichuan"));//lorf:李川    string 1-李，2-傅
            }else if("2".equals(paramsMap.get("tApproval"))){
                paramsMap.put("lorf",edsService.getEmpGuidByPfAcc("fuzhihua"));//lorf:傅志华    string 1-李，2-傅
            }
            paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.XQBG.getText());//流程名称
            ApplyCreateResultVo applyCreateResultVo = new ApplyCreateResultVo();
            if (overallCost.compareTo(ten) != -1) {//大于10万走审批
                //创建流程
                applyCreateResultVo = ProgramBpmUtils.submitDemandChangeApprove(paramsMap);
                if (!applyCreateResultVo.isSuccess()) {
                    LOG.error("创建流程失败:" + JSON.toJSONString(paramsMap) + "-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
            } else {//小于10万走通知
                //创建流程
                applyCreateResultVo = ProgramBpmUtils.submitDemandChangeAdvise(paramsMap);
                if (!applyCreateResultVo.isSuccess()) {
                    LOG.error("创建流程失败:" + JSON.toJSONString(paramsMap) + "-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
            }

            //program表
            if (overallCost.compareTo(ten) != -1) {//大于10万
                program.setAccountType(Integer.parseInt(paramsMap.get("accountType")));
                program.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
                program.setModifiedName(paramsMap.get("modifiedName"));
                programMapper.updateByPrimaryKey(program);
            }

            program.setDevWorkload(program.getDevWorkload()+Integer.parseInt(paramsMap.get("devWorkloadChange")));
            program.setOverallCost(program.getOverallCost().add(overallCost));
            if(StringUtils.isNoneBlank(paramsMap.get("grayReleaseDate")))program.setGrayReleaseDate(DateUtil.string2Date(paramsMap.get("grayReleaseDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNoneBlank(paramsMap.get("demandDate")))program.setProdApprovalDate(DateUtil.string2Date(paramsMap.get("demandDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNoneBlank(paramsMap.get("developmentDate")))program.setDevApprovalDate(DateUtil.string2Date(paramsMap.get("developmentDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNoneBlank(paramsMap.get("testReviewDate")))program.setTestApprovalDate(DateUtil.string2Date(paramsMap.get("testReviewDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNoneBlank(paramsMap.get("onlineDate")))program.setOnlinePlanDate(DateUtil.string2Date(paramsMap.get("onlineDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNoneBlank(paramsMap.get("replayDate")))program.setReplayDate(DateUtil.string2Date(paramsMap.get("replayDate"),DateUtil.PATTERN_DATE));
            if(StringUtils.isNoneBlank(paramsMap.get("allExtensionDate")))program.setAllExtensionDate(DateUtil.string2Date(paramsMap.get("allExtensionDate"),DateUtil.PATTERN_DATE));

            //小于10万直接审核通过
            if (overallCost.compareTo(ten) == -1) {
                //研发方式：招投标时，状态回到，中标申请，审核通过
                if (program.getDevType() == 1) {
//                    program.setProgramStatus(ProgramStatusNewEnum.DPS.getCode());
                }
                //研发方式：自研时，状态回到，Demo评审，审核通过
                if (program.getDevType() == 2) {
                    program.setProgramStatus(ProgramStatusNewEnum.DPS.getCode());
                }
                program.setApprovalStatus(ProgramApprovalStatusEnum.SHTG.getCode());
                program.setAccountType(Integer.parseInt(paramsMap.get("accountType")));
                program.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
                program.setModifiedName(paramsMap.get("modifiedName"));
                programMapper.updateByPrimaryKey(program);
            }

            //program快照表
            ProgramApprovalSnapshot programApprovalSnapshot = new ProgramApprovalSnapshot();
            this.copyProperties(programApprovalSnapshot,program);
            programApprovalSnapshot.setProgramStatus(ProgramStatusNewEnum.XQBG.getCode());
            if (overallCost.compareTo(ten) != -1) {
                programApprovalSnapshot.setBpmCode(applyCreateResultVo.getInstanceID());
                programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.BGSHZ.getCode());
            } else {
                programApprovalSnapshot.setBpmCode(applyCreateResultVo.getInstanceID());
                programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.SHTG.getCode());
            }

            programApprovalSnapshot.setDevWorkload(Integer.parseInt(paramsMap.get("devWorkloadChange")));
            programApprovalSnapshot.setOverallCost(overallCost);
            programApprovalSnapshot.setRemark(paramsMap.get("remark"));
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setApplyAccount(paramsMap.get("modifiedAccountId"));
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshot.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
            programApprovalSnapshot.setModifiedName(paramsMap.get("modifiedName"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);

            //附件表
            this.dealFileList(paramsMap.get("fileList"),program.getId(),ProgramStatusNewEnum.XQBG.getCode(),programApprovalSnapshot.getId());


            if (overallCost.compareTo(ten) != -1) {
                //激活流程
                ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtils.approvePass(
                        paramsMap.get("modifiedAccountId"),applyCreateResultVo.getWorkItemID(),null);
                if(pplySubmitResultVo.getIsSuccess().equals("false")){
                    LOG.error("激活流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("激活流程失败");
                }
            } else {//小于10万走通知
                //激活流程
                ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtils.approvePass(
                        paramsMap.get("modifiedAccountId"),applyCreateResultVo.getWorkItemID(),null);
                if(pplySubmitResultVo.getIsSuccess().equals("false")){
                    LOG.error("激活流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("激活流程失败");
                }
            }
            //日志记录审批记录
            String text = paramsMap.get("modifiedName") + "提交了"+ ProgramStatusNewEnum.XQBG.getText();
            ProgramEmployeeChangeLog employeeChangeLog = new ProgramEmployeeChangeLog();
            employeeChangeLog.setProgramId(program.getId());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setActionChangeInfo(text);
            employeeChangeLog.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
            employeeChangeLog.setModifiedName(paramsMap.get("modifiedName"));
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setAccountType(0);
            programEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发生异常");
        }
    }

    /***
     * 终止项目
     */
    @Override
    @Transactional(value="transactionManager")
    public void stopProgram(Map<String, String> paramsMap,Program program) {
        try{
            Date now = new Date();

            paramsMap.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
            paramsMap.put("businessList",edsService.getEmpGuidByPfAcc(paramsMap.get("businessList")));//业务人员转成GUID
            paramsMap.put("developerList",edsService.getEmpGuidByPfAcc(paramsMap.get("developerList")));//开发人员转成GUID
            if("1".equals(paramsMap.get("reportPoor"))){
                paramsMap.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
            }
            paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.ZZ.getText());//流程名称
            //创建流程
            ApplyCreateResultVo applyCreateResultVo = ProgramBpmUtils.submitFinishProgram(paramsMap);
            if(!applyCreateResultVo.isSuccess()){
                LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("创建流程失败");
            }

            //program表
//            program.setApprovalStatus(ProgramApprovalStatusEnum.SHZ.getCode());
            program.setAccountType(Integer.parseInt(paramsMap.get("accountType")));
            program.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
            program.setModifiedName(paramsMap.get("modifiedName"));
            programMapper.updateByPrimaryKey(program);

            //program快照表
            ProgramApprovalSnapshot programApprovalSnapshot = new ProgramApprovalSnapshot();
            this.copyProperties(programApprovalSnapshot,program);
            programApprovalSnapshot.setProgramStatus(ProgramStatusNewEnum.ZZ.getCode());
            programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.SHZ.getCode());
            programApprovalSnapshot.setBpmCode(applyCreateResultVo.getInstanceID());
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setRemark(paramsMap.get("remark"));
            programApprovalSnapshot.setApplyAccount(paramsMap.get("modifiedAccountId"));
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);

            //激活流程
            ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtils.approvePass(
                    paramsMap.get("modifiedAccountId"),applyCreateResultVo.getWorkItemID(),null);
            if(pplySubmitResultVo.getIsSuccess().equals("false")){
                LOG.error("激活流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("激活流程失败");
            }
            //日志记录审批记录
            String text = paramsMap.get("modifiedName") +ProgramStatusNewEnum.ZZ.getText()+ "了项目。";
            ProgramEmployeeChangeLog employeeChangeLog = new ProgramEmployeeChangeLog();
            employeeChangeLog.setProgramId(program.getId());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setActionChangeInfo(text);
            employeeChangeLog.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
            employeeChangeLog.setModifiedName(paramsMap.get("modifiedName"));
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setAccountType(0);
            programEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发生异常");
        }
    }

    private void dealFileList(String fileStr,Long programId , int programStatus, Long snapshotId) {
        if (org.apache.commons.lang.StringUtils.isNotBlank(fileStr)) {
            List<FileVo> fileList = JSON.parseArray(fileStr, FileVo.class);
            if (fileList != null && !fileList.isEmpty()) {
                for (FileVo fileVo : fileList) {
                    ProgramFile programFile = new ProgramFile();
                    programFile.setProgramId(programId);
                    programFile.setFileName(StringUtils.trim(fileVo.getFileName()));
                    programFile.setFileSuffix(fileVo.getFileSuffix());
                    programFile.setFileSize(fileVo.getFileSize());
                    programFile.setType(programStatus);
                    programFile.setCreateTime(new Date());
                    programFile.setFilePath(fileVo.getFilePath());
                    programFile.setSnapshotId(snapshotId);
                    programFileMapper.insert(programFile);
                }
            }
        }
    }

    /***
     * 审批流审批通过之后，如果审批为复盘、延期上线、需求变更、终止项目时，  则项目表项目状态需要变更
     * @param bpmCode
     * @return
     */
    private void getApprovelAfterProgramStatus(String bpmCode,Program program,int programStatus){
        if(programStatus==ProgramStatusNewEnum.XMFP.getCode()){//如果提交审批的为复盘，则项目状态直接到完成
            program.setProgramStatus(ProgramStatusNewEnum.WC.getCode());
        }else if(programStatus==ProgramStatusNewEnum.XQBG.getCode()){//如果提交审批的为需求变更，则项目状态根据研发方式回到对应节点
            //研发方式：招投标时，状态回到，中标申请，审核通过
            if (program.getDevType() == 1) {
//                program.setProgramStatus(ProgramStatusNewEnum.ZBSQ.getCode());
            }
            //研发方式：自研时，状态回到，Demo评审，审核通过
            if (program.getDevType() == 2) {
                program.setProgramStatus(ProgramStatusNewEnum.DPS.getCode());
            }
        }else if(programStatus==ProgramStatusNewEnum.ZZ.getCode()){//如果提交审批的为终止项目，则项目状态直接到终止
            program.setProgramStatus(ProgramStatusNewEnum.ZZ.getCode());
        }
    }

    /**
     * 参数处理_提交立项BRD
     * @param paramsMap
     * @return
     */
    private Map submitBrdMap(Map paramsMap){
        Map map = new HashMap();
        map.put("workflowInstanceTitle",paramsMap.get("workflowInstanceTitle"));//l流程名称
        map.put("modifiedAccountId",paramsMap.get("modifiedAccountId"));//提交人oa账号
        map.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
        map.put("businessAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("businessList").toString()));//业务人guid
        map.put("businessFunctionsList",paramsMap.get("businessFunctionsList"));//业务职能人guid
//        map.put("itCenterLeaderList",paramsMap.get("itCenterLeaderList"));//IT中心负责人guid
        map.put("businessCenterList",paramsMap.get("businessCenterList"));//业务中心负责人
        map.put("developAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("developerList").toString()));//项目技术负责人/开发人员guid
        if("1".equals(paramsMap.get("reportPoor"))){
            map.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
        }
//        map.put("counterSigners",paramsMap.get("counterSigners"));//会签人  string 逗号分隔
        map.put("cOrZ",paramsMap.get("tApproval"));//string 1-李，2-傅   IT部门副总经理
        map.put("businessPresidentList",paramsMap.get("businessPresidentList"));//业务副总裁
        if(paramsMap.get("productId")!=null){
            Program program = new Program();
            program.setProductId(Long.parseLong(paramsMap.get("productId").toString()));
            int i = programMapper.selectCount(program);
            map.put("isFirst",String.valueOf(i));//是否产品下第一个项目
        }
        //地产、冠寓、基础架构、技术管理相关加刘富强会签人，添加节点：立项、开发评审
        if(paramsMap.get("analyzingConditions")!=null){
            if("1".equals(paramsMap.get("analyzingConditions"))
                    || "4".equals(paramsMap.get("analyzingConditions"))
                    || "7".equals(paramsMap.get("analyzingConditions"))){
                map.put("isLfq","d815be69-9d5d-45d1-a7d4-2823d0a33631");//刘富强审批
            }
        }
        return map;
    }

    /**
     * 参数处理_Demo评审
     * @param paramsMap
     * @return
     */
    private Map submitDemo(Map paramsMap){
        Map map = new HashMap();
        map.put("workflowInstanceTitle",paramsMap.get("workflowInstanceTitle"));//l流程名称
        map.put("modifiedAccountId",paramsMap.get("modifiedAccountId"));//提交人oa账号
        map.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
        map.put("businessAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("businessList").toString()));//业务人guid
//        map.put("itCenterLeaderList",paramsMap.get("itCenterLeaderList"));//IT中心负责人guid
        map.put("businessCenterList",paramsMap.get("businessCenterList"));//业务中心负责人
        map.put("developAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("developerList").toString()));//项目技术负责人/开发人员guid
        if("1".equals(paramsMap.get("reportPoor"))){
            map.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
        }
        map.put("cOrZ",paramsMap.get("tApproval"));//string 1-李，2-傅   IT部门副总经理
        return map;
    }
    /**
     * 参数处理_产品评审
     * @param paramsMap
     * @return
     */
    private Map submitProductReview(Map paramsMap){
        Map map = new HashMap();
        map.put("workflowInstanceTitle",paramsMap.get("workflowInstanceTitle"));//l流程名称
        map.put("modifiedAccountId",paramsMap.get("modifiedAccountId"));//提交人oa账号
        map.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
        map.put("businessAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("businessList").toString()));//业务人guid
//        map.put("itCenterLeaderList",paramsMap.get("itCenterLeaderList"));//IT中心负责人guid
        map.put("businessCenterList",paramsMap.get("businessCenterList"));//业务中心负责人
        map.put("developAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("developerList").toString()));//项目技术负责人/开发人员guid
        if("1".equals(paramsMap.get("reportPoor"))){
            map.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
        }
        return map;
    }

    /**
     * 参数处理_开发评审
     * @param paramsMap
     * @return
     */
    private Map submitDevelopReview(Map paramsMap){
        Map map = new HashMap();
        map.put("workflowInstanceTitle",paramsMap.get("workflowInstanceTitle"));//l流程名称
        map.put("modifiedAccountId",paramsMap.get("modifiedAccountId"));//提交人oa账号
        map.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
        map.put("productManagerList",edsService.getEmpGuidByPfAcc(paramsMap.get("productManagerList").toString()));//产品经理
        map.put("testingList",edsService.getEmpGuidByPfAcc(paramsMap.get("testingList").toString()));//项目测试负责人
        map.put("counterSigners",paramsMap.get("counterSigners"));//会签人  string 逗号分隔
        map.put("developAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("developerList").toString()));//项目技术负责人/开发人员guid
        if("1".equals(paramsMap.get("reportPoor"))){
            map.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
        }
        //地产、冠寓、基础架构、技术管理相关加刘富强会签人，添加节点：立项、开发评审
        if(paramsMap.get("programId")!=null){
            Program program = new Program();
            program.setId(Long.valueOf(paramsMap.get("programId").toString()));
            program = programMapper.selectOne(program);
            if("1".equals(program.getAnalyzingConditions())
                    || "4".equals(program.getAnalyzingConditions())
                    || "7".equals(program.getAnalyzingConditions())){
                map.put("isLfq","d815be69-9d5d-45d1-a7d4-2823d0a33631");//刘富强审批
            }
        }
        return map;
    }

    /**
     * 参数处理_测试评审
     * @param paramsMap
     * @return
     */
    private Map submitTestReview(Map paramsMap){
        Map map = new HashMap();
        map.put("workflowInstanceTitle",paramsMap.get("workflowInstanceTitle"));//l流程名称
        map.put("modifiedAccountId",paramsMap.get("modifiedAccountId"));//提交人oa账号
        map.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
        map.put("productManagerList",edsService.getEmpGuidByPfAcc(paramsMap.get("productManagerList").toString()));//产品经理
        map.put("testingList",edsService.getEmpGuidByPfAcc(paramsMap.get("testingList").toString()));//项目测试负责人
        map.put("developAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("developerList").toString()));//项目技术负责人/开发人员guid
        if("1".equals(paramsMap.get("reportPoor"))){
            map.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
        }
        return map;
    }

    /**
     * 参数处理_提交上线计划
     * @param paramsMap
     * @return
     */
    private Map submitOnlinePlan(Map paramsMap){
        Map map = new HashMap();
        map.put("workflowInstanceTitle",paramsMap.get("workflowInstanceTitle"));//l流程名称
        map.put("modifiedAccountId",paramsMap.get("modifiedAccountId"));//提交人oa账号
        map.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
        map.put("businessAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("businessList").toString()));//业务人guid
        map.put("businessFunctionsList",paramsMap.get("businessFunctionsList"));//业务职能人guid
//        map.put("itCenterLeaderList",paramsMap.get("itCenterLeaderList"));//IT中心负责人guid
        map.put("businessCenterList",paramsMap.get("businessCenterList"));//业务中心负责人
        map.put("developAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("developerList").toString()));//项目技术负责人/开发人员guid
        if("1".equals(paramsMap.get("reportPoor"))){
            map.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
        }
        map.put("cOrZ",paramsMap.get("tApproval"));//string 1-李，2-傅   IT部门副总经理
        map.put("businessPresidentList",paramsMap.get("businessPresidentList"));//业务副总裁
        return map;
    }

    /**
     * 参数处理_提交灰度发布
     * @param paramsMap
     * @return
     */
    private Map submitPartIntroduce(Map paramsMap){
        Map map = new HashMap();
        map.put("workflowInstanceTitle",paramsMap.get("workflowInstanceTitle"));//l流程名称
        map.put("modifiedAccountId",paramsMap.get("modifiedAccountId"));//提交人oa账号
        map.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
        map.put("businessAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("businessList").toString()));//业务人guid
//        map.put("itCenterLeaderList",paramsMap.get("itCenterLeaderList"));//IT中心负责人guid
        map.put("businessCenterList",paramsMap.get("businessCenterList"));//业务中心负责人
        map.put("developAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("developerList").toString()));//项目技术负责人/开发人员guid
        if("1".equals(paramsMap.get("reportPoor"))){
            map.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
        }
        return map;
    }

    /**
     * 参数处理_提交全面推广
     * @param paramsMap
     * @return
     */
    private Map submitAllSpread(Map paramsMap){
        Map map = new HashMap();
        map.put("workflowInstanceTitle",paramsMap.get("workflowInstanceTitle"));//l流程名称
        map.put("modifiedAccountId",paramsMap.get("modifiedAccountId"));//提交人oa账号
        map.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
        map.put("businessAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("businessList").toString()));//业务人guid
//        map.put("itCenterLeaderList",paramsMap.get("itCenterLeaderList"));//IT中心负责人guid
        map.put("businessCenterList",paramsMap.get("businessCenterList"));//业务中心负责人
        map.put("developAccount",edsService.getEmpGuidByPfAcc(paramsMap.get("developerList").toString()));//项目技术负责人/开发人员guid
        if("1".equals(paramsMap.get("reportPoor"))){
            map.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
        }
        return map;
    }

    /**
     * 参数处理_提交项目复盘
     * @param paramsMap
     * @return
     */
    private Map submitProgramReplay(Map paramsMap){
        Map map = new HashMap();
        map.put("workflowInstanceTitle",paramsMap.get("workflowInstanceTitle"));//l流程名称
        map.put("modifiedAccountId",paramsMap.get("modifiedAccountId"));//提交人oa账号
        map.put("modifiedAccountGuid",edsService.getEmpGuidByPfAcc(paramsMap.get("modifiedAccountId").toString()));//提交人guid
//        map.put("itCenterLeaderList",paramsMap.get("itCenterLeaderList"));//IT中心负责人guid
        if("1".equals(paramsMap.get("reportPoor"))){
            map.put("ifZqs",edsService.getEmpGuidByPfAcc("zhouqiongshuo"));//ifZqs:是否周琼硕审批    string 2-否，1-是
        }
        return map;
    }

    @Override
    @Transactional(value="transactionManager")
    public void submit(Map<String, String> paramsMap,Program program,int programStatus) {
        try{
            Date now = new Date();
            ApplyCreateResultVo applyCreateResultVo = new ApplyCreateResultVo();
            String textAction = "";
            if(programStatus==ProgramStatusNewEnum.LX.getCode()) {
                //立项创建流程
                paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.LX.getText());
                applyCreateResultVo = ProgramBpmUtils.submitBrd(submitBrdMap(paramsMap));
                if(!applyCreateResultVo.isSuccess()){
                    LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
                textAction = ProgramStatusNewEnum.LX.getText();
            }
            if(programStatus==ProgramStatusNewEnum.DPS.getCode()) {
                //Demo评审创建流程
                paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.DPS.getText());
                applyCreateResultVo = ProgramBpmUtils.submitDemo(submitDemo(paramsMap));
                if(!applyCreateResultVo.isSuccess()){
                    LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
                textAction = ProgramStatusNewEnum.DPS.getText();
            }
            if(programStatus==ProgramStatusNewEnum.CPPS.getCode()) {
                //产品评审创建流程
                paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.CPPS.getText());
                applyCreateResultVo = ProgramBpmUtils.submitProductReview(submitProductReview(paramsMap));
                if(!applyCreateResultVo.isSuccess()){
                    LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
                textAction = ProgramStatusNewEnum.CPPS.getText();
            }

            if(programStatus==ProgramStatusNewEnum.KFPS.getCode()) {
                //提交开发评审创建流程
                paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.KFPS.getText());
                applyCreateResultVo = ProgramBpmUtils.submitDevelopReview(submitDevelopReview(paramsMap));
                if(!applyCreateResultVo.isSuccess()){
                    LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
                textAction = ProgramStatusNewEnum.KFPS.getText();
            }

            if(programStatus==ProgramStatusNewEnum.CSPS.getCode()) {
                //提交测试评审创建流程
                paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.CSPS.getText());
                applyCreateResultVo = ProgramBpmUtils.submitTestReview(submitTestReview(paramsMap));
                if(!applyCreateResultVo.isSuccess()){
                    LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
                textAction = ProgramStatusNewEnum.CSPS.getText();
            }
            if(programStatus==ProgramStatusNewEnum.SXPS.getCode()) {
                //提交上线计划创建流程
                paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.SXPS.getText());
                applyCreateResultVo = ProgramBpmUtils.submitOnlinePlan(submitOnlinePlan(paramsMap));
                if(!applyCreateResultVo.isSuccess()){
                    LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
                textAction = ProgramStatusNewEnum.SXPS.getText();
            }

            if(programStatus==ProgramStatusNewEnum.HDFB.getCode()) {
                //提交灰度发布创建流程
                paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.HDFB.getText());
                applyCreateResultVo = ProgramBpmUtils.submitPartIntroduce(submitPartIntroduce(paramsMap));
                if(!applyCreateResultVo.isSuccess()){
                    LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
                textAction = ProgramStatusNewEnum.HDFB.getText();
            }

            if(programStatus==ProgramStatusNewEnum.QMTG.getCode()) {
                //提交全面推广创建流程
                paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.QMTG.getText());
                applyCreateResultVo = ProgramBpmUtils.submitAllSpread(submitAllSpread(paramsMap));
                if(!applyCreateResultVo.isSuccess()){
                    LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
                textAction = ProgramStatusNewEnum.QMTG.getText();
            }

            if(programStatus==ProgramStatusNewEnum.XMFP.getCode()) {
                //提交项目复盘创建流程
                paramsMap.put("workflowInstanceTitle",program.getName()+ProgramStatusNewEnum.XMFP.getText());
                applyCreateResultVo = ProgramBpmUtils.submitProgramReplay(submitProgramReplay(paramsMap));
                if(!applyCreateResultVo.isSuccess()){
                    LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
                textAction = ProgramStatusNewEnum.XMFP.getText();
            }


            int approvalStatus = ProgramApprovalStatusEnum.SHZ.getCode();

            String devType = paramsMap.get("devType");
            String analyzingConditions = paramsMap.get("analyzingConditions");
            String devWorkload = paramsMap.get("devWorkload");
            String overallCost = paramsMap.get("overallCost");
            String commitDate = paramsMap.get("commitDate");
            String demoApprovalDate = paramsMap.get("demoApprovalDate");
            String prodApprovalDate = paramsMap.get("prodApprovalDate");
            String grayReleaseDate = paramsMap.get("grayReleaseDate");
            String biddingDate = paramsMap.get("biddingDate");
            String winningBidDate = paramsMap.get("winningBidDate");
            String bidOverallCost = paramsMap.get("bidOverallCost");
            String bidDevWorkload = paramsMap.get("bidDevWorkload");
            String bidOversingleCost = paramsMap.get("bidOversingleCost");
            String researchDate = paramsMap.get("researchDate");
            String testDate = paramsMap.get("testDate");
            String onlineDate = paramsMap.get("onlineDate");
            String productId = paramsMap.get("productId");
            String likeProduct = paramsMap.get("likeProduct");
            String type = paramsMap.get("type");
            String replayDate = paramsMap.get("replayDate");
            String allExtensionDate = paramsMap.get("allExtensionDate");

            //基本信息实时修改
            if(StringUtils.isNotBlank(devType))program.setDevType(Integer.parseInt(devType));//研发方式
            if(StringUtils.isNotBlank(analyzingConditions))program.setAnalyzingConditions(analyzingConditions);//判断条件
            if(StringUtils.isNotBlank(devWorkload))program.setDevWorkload(Integer.parseInt(devWorkload));//研发工作量预估
            if(StringUtils.isNotBlank(overallCost))program.setOverallCost(new BigDecimal(overallCost));//整体费用预估
            if(StringUtils.isNotBlank(bidOverallCost))program.setBidOverallCost(new BigDecimal(bidOverallCost));//整体费用
            if(StringUtils.isNotBlank(bidDevWorkload))program.setBidDevWorkload(Integer.parseInt(bidDevWorkload));//人天（框架合同填写）
            if(StringUtils.isNotBlank(bidOversingleCost))program.setBidOversingleCost(new BigDecimal(bidOversingleCost));//人天单价（框架合同填写）
            if(StringUtils.isNotBlank(likeProduct))program.setLikeProduct(likeProduct);
            if(StringUtils.isNotBlank(type))program.setType(Integer.parseInt(type));
            if(StringUtils.isNotBlank(productId)){
                Product product = productMapper.selectByPrimaryKey(Long.valueOf(productId));
                if (product!=null) {
                    program.setProductId(product.getId());
                    program.setProductName(product.getName());
                    program.setProductCode(product.getCode());
                }
            }
            program.setApprovalStatus(approvalStatus);
            program.setProgramStatus(programStatus);
            program.setModifiedTime(now);
            programMapper.updateByPrimaryKey(program);

            //program表
            //相关日期审核通过之后，修改项目信息
            if(StringUtils.isNotBlank(commitDate))program.setCommitDate(DateUtil.string2Date(commitDate,DateUtil.PATTERN_DATE));//立项时间
            if(StringUtils.isNotBlank(demoApprovalDate))program.setDemoApprovalDate(DateUtil.string2Date(demoApprovalDate,DateUtil.PATTERN_DATE));//demo评审时间
            if(StringUtils.isNotBlank(grayReleaseDate))program.setGrayReleaseDate(DateUtil.string2Date(grayReleaseDate,DateUtil.PATTERN_DATE));//灰度发布时间
            if(StringUtils.isNotBlank(biddingDate))program.setBiddingDate(DateUtil.string2Date(biddingDate,DateUtil.PATTERN_DATE));//招标时间
            if(StringUtils.isNotBlank(winningBidDate))program.setWinningBidDate(DateUtil.string2Date(winningBidDate,DateUtil.PATTERN_DATE));//中标时间
            if(StringUtils.isNotBlank(prodApprovalDate))program.setProdApprovalDate(DateUtil.string2Date(prodApprovalDate,DateUtil.PATTERN_DATE));//产品评审时间
            if(StringUtils.isNotBlank(researchDate))program.setDevApprovalDate(DateUtil.string2Date(researchDate,DateUtil.PATTERN_DATE));//研发评审时间
            if(StringUtils.isNotBlank(testDate))program.setTestApprovalDate(DateUtil.string2Date(testDate,DateUtil.PATTERN_DATE));//测试评审时间
            if(StringUtils.isNotBlank(onlineDate))program.setOnlinePlanDate(DateUtil.string2Date(onlineDate,DateUtil.PATTERN_DATE));//上线计划时间
            if(StringUtils.isNotBlank(replayDate))program.setReplayDate(DateUtil.string2Date(replayDate,DateUtil.PATTERN_DATE));//项目复盘时间
            if(StringUtils.isNotBlank(allExtensionDate))program.setAllExtensionDate(DateUtil.string2Date(allExtensionDate,DateUtil.PATTERN_DATE));//全面推广时间

//            program.setApprovalStatus(approvalStatus);
//            program.setProgramStatus(programStatus);
//            program.setModifiedTime(now);
//            programMapper.updateByPrimaryKey(program);
            if(programStatus==ProgramStatusNewEnum.LX.getCode()) {
                this.dealProgramEmployee(paramsMap, program);
            }

            //program快照表
            ProgramApprovalSnapshot programApprovalSnapshot = new ProgramApprovalSnapshot();
            this.copyProperties(programApprovalSnapshot,program);
            programApprovalSnapshot.setBpmCode(applyCreateResultVo.getInstanceID());
            programApprovalSnapshot.setRemark(paramsMap.get("remark"));
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setApplyAccount(paramsMap.get("modifiedAccountId"));
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshot.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
            programApprovalSnapshot.setModifiedName(paramsMap.get("modifiedName"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);

            //附件表
            this.dealFileList(paramsMap.get("fileList"),program.getId(),programStatus,programApprovalSnapshot.getId());

            //激活流程
            ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtils.approvePass(
                    paramsMap.get("modifiedAccountId"),applyCreateResultVo.getWorkItemID(),null);
            if(pplySubmitResultVo.getIsSuccess().equals("false")){
                LOG.error("激活流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("激活流程失败");
            }
            //日志记录审批记录
            String text = paramsMap.get("modifiedName") + "提交了"+ textAction;
            ProgramEmployeeChangeLog employeeChangeLog = new ProgramEmployeeChangeLog();
            employeeChangeLog.setProgramId(program.getId());
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setActionChangeInfo(text);
            employeeChangeLog.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
            employeeChangeLog.setModifiedName(paramsMap.get("modifiedName"));
            employeeChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
            employeeChangeLog.setAccountType(0);
            programEmployeeChangeLogMapper.insertUseGeneratedKeys(employeeChangeLog);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发生异常");
        }
    }

    /**
     * 拷贝program信息到programApprovalSnapshot
     * @param programApprovalSnapshot
     * @param program
     * @throws Exception
     */
    private void copyProperties(ProgramApprovalSnapshot programApprovalSnapshot,Program program) throws Exception{
        BeanUtils.copyProperties(programApprovalSnapshot,program);
        programApprovalSnapshot.setId(null);
        programApprovalSnapshot.setProgramId(program.getId());
    }
    /**
     * 拷贝programApprovalSnapshot信息到program
     * @param programApprovalSnapshot
     * @param program
     * @throws Exception
     */
    private void copyPropertiesToProgram(Program program,ProgramApprovalSnapshot programApprovalSnapshot) throws Exception{
        Long programId = program.getId();
        BeanUtils.copyProperties(program,programApprovalSnapshot);
        program.setId(programId);
    }

    /***
     * 根据需求ID 获取快照信息
     */
    @Override
    public ProgramApprovalSnapshot getSnapshot(Long id)  throws Exception{
        ProgramApprovalSnapshot shot = programApprovalSnapshotMapper.selectByPrimaryKey(id);
        Map pMap = new HashMap();
        pMap.put("bpmCode", shot.getBpmCode());
        if(shot.getProgramStatus()==ProgramStatusNewEnum.YQSX.getCode()
                || shot.getProgramStatus()==ProgramStatusNewEnum.XQBG.getCode()){
            BigDecimal ten = new BigDecimal(100000);
            if (shot.getOverallCost().compareTo(ten) != -1 || shot.getProgramStatus()==ProgramStatusNewEnum.YQSX.getCode()) {//大于10万,取审批变更数据
                //需求变更或延期时，取变更审核中的历史数据
                pMap.put("approvalStatus", ProgramApprovalStatusEnum.BGSHZ.getCode());
            } else {
                //需求变更走通知时，取审核通过的历史数据（通知没有审核中的数据）
                pMap.put("approvalStatus", ProgramApprovalStatusEnum.SHTG.getCode());
            }
        } else {
            //九步法，取审核中的历史数据
            pMap.put("approvalStatus", ProgramApprovalStatusEnum.SHZ.getCode());
        }
        List<ProgramApprovalSnapshot> s = programApprovalSnapshotMapper.grayLevelList(pMap);
        //取s.get(0)对象，是去找shot对应的文件信息
        if (s != null && !s.isEmpty()) {
            this.setProgramApprovalSnapshotInfo(s.get(0),shot);
        }
        return shot;
    }

    /*设置快照文件信息和项目经理信息*/
    private void setProgramApprovalSnapshotInfo (ProgramApprovalSnapshot model,ProgramApprovalSnapshot resultModel){
        if (model != null ) {
            Map map = new HashMap();
            map.put("programId",model.getProgramId());
            map.put("type",model.getProgramStatus());
            map.put("snapshotId",model.getId());
            List<ProgramFile> fileList = programFileMapper.getListByMap(map);
            resultModel.setFileList(fileList);//w文件信息

             /* 责任人 */
            map.put("employeeType", AvaStatusEnum.LIABLEAVA.getCode());
            List<ProgramEmployee> personLiableList = programEmployeeService.selectTypeList(map);
            resultModel.setPersonLiableList(personLiableList);
            /*项目经理信息*/
            map.put("employeeType", AvaStatusEnum.MEMBERAVA.getCode());
            map.put("employeeTypeId", new Long(AvaStatusEnum.PROGAVA.getCode()));
            List<ProgramEmployee> empList  = programEmployeeMapper.selectTypeList(map);
            resultModel.setEmpList(empList);
			/* 产品经理 */
            map.put("employeeType", AvaStatusEnum.MEMBERAVA.getCode());
            map.put("employeeTypeId", new Long(AvaStatusEnum.PRODAVA.getCode()));
            List<ProgramEmployee> programManagerList = programEmployeeService.selectTypeList(map);
            resultModel.setProductManagerList(programManagerList);
			/* 项目经理 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.PROGAVA.getCode()));
            List<ProgramEmployee> productManagerList = programEmployeeService.selectTypeList(map);
            resultModel.setProgramManagerList(productManagerList);
			/* 开发人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.DEVEAVA.getCode()));
            List<ProgramEmployee> developerList = programEmployeeService.selectTypeList(map);
            resultModel.setDeveloperList(developerList);
			/* UED人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.UEDAVA.getCode()));
            List<ProgramEmployee> uedList = programEmployeeService.selectTypeList(map);
            resultModel.setUedList(uedList);
			/* 测试人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.TESTINGAVA.getCode()));
            List<ProgramEmployee> testingList = programEmployeeService.selectTypeList(map);
            resultModel.setTestingList(testingList);
			/* 业务人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.BUSINESSAVA.getCode()));
            List<ProgramEmployee> businessList = programEmployeeService.selectTypeList(map);
            resultModel.setBusinessList(businessList);
			/* 运维人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.OPERATION.getCode()));
            List<ProgramEmployee> operationList = programEmployeeService.selectTypeList(map);
            resultModel.setOperationList(operationList);
            /* 运营人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.OPERATE.getCode()));
            List<ProgramEmployee> operateList = programEmployeeService.selectTypeList(map);
            resultModel.setOperateList(operateList);

        }
    }

    @Override
    public ApproveListVo getApprovelapprovList(MoApproveListVo moApproveListVo,String isMyLaunch) throws Exception{
        ApproveListVo approveListVo = new ApproveListVo();
        approveListVo.setTotal(moApproveListVo.getTotal());
        approveListVo.setPageNo(moApproveListVo.getPage());

        //循环获取bpmcode list，然后查询快照表获取 每个bpmcode获取最后一条对应快照
        List<String> bmpCodeList = new ArrayList<>();
        List<MoApproveVo> moApproveVoList = moApproveListVo.getList();
        for(MoApproveVo moApproveVo:moApproveVoList){
            bmpCodeList.add(moApproveVo.getFlowNo());
        }
        List<ProgramApprovalSnapshot> snapshotList = programApprovalSnapshotMapper.getByBpmCodes(bmpCodeList);

        //封装bmpcode为key  快照为value的map
        Map<String,ProgramApprovalSnapshot> map = new HashMap<>();
        for(ProgramApprovalSnapshot programApprovalSnapshot:snapshotList){
            map.put(programApprovalSnapshot.getBpmCode(),programApprovalSnapshot);
        }

        //循环生成返回页面对象视图
        List<ApproveVo> resultList = new ArrayList<>();
        for(MoApproveVo moApproveVo:moApproveVoList){
            ApproveVo resultVo = new ApproveVo();
            String bpmCode = moApproveVo.getFlowNo();
            ProgramApprovalSnapshot tmpSna = map.get(bpmCode);
            if(tmpSna==null)continue;
            resultVo.setInstanceId(bpmCode);
            resultVo.setToDoId(moApproveVo.getTodoId());
            resultVo.setTitle(moApproveVo.getTitle());
            resultVo.setProgramId(String.valueOf(tmpSna.getProgramId()));
            resultVo.setProgramName(tmpSna.getName());
            resultVo.setProgramStatus(tmpSna.getProgramStatus());
            resultVo.setProgramStatusCh(ProgramStatusNewEnum.getByCode(tmpSna.getProgramStatus()).getText());
            resultVo.setApplyName(moApproveVo.getPubTrueName());
            //0=获取移动审批列表,1=获取我的发起列表
            if ("0".equals(isMyLaunch)) {
                resultVo.setTodoStatus(moApproveVo.getTodoStatus());
            }
            if ("1".equals(isMyLaunch)) {
                resultVo.setTodoStatus(moApproveVo.getFlowStatus());
            }
            resultVo.setApplyTime(DateUtil.date2String(tmpSna.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            resultList.add(resultVo);
        }
        approveListVo.setList(resultList);
        return approveListVo;
    }

    private void dealProgramEmployee (Map map,Program program) {
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        // 项目责任人
        if (json.get("personLiableList") != null) {
            String jsonArrPl = json.get("personLiableList").toString();
            if (!"".equals(jsonArrPl)) {
                deleteByParam(1, 0, program);
                getAccountLongfor(program, jsonArrPl, "0");
            }
        }

        // 产品经理
        if (json.get("productManagerList") != null) {
            String jsonArrPm = json.get("productManagerList").toString();
            if (!"".equals(jsonArrPm)) {
                deleteByParam(2, 1, program);
                getAccountLongfor(program, jsonArrPm, "1");
            }
        }

        // 项目经理
        if (json.get("programManagerList") != null) {
            String jsonArrPMl = json.get("programManagerList").toString();
            if (!"".equals(jsonArrPMl)) {
                deleteByParam(2, 2, program);
                getAccountLongfor(program, jsonArrPMl, "2");
            }
        }

        // 开发人员
        if (json.get("developerList") != null) {
            String jsonArrDe = json.get("developerList").toString();
            if (!"".equals(jsonArrDe)) {
                deleteByParam(2, 3, program);
                getAccountLongfor(program, jsonArrDe, "3");
            }
        }

        // UED人员
        if (json.get("uedList") != null) {
            String jsonArrUed = json.get("uedList").toString();
            if (!"".equals(jsonArrUed)) {
                deleteByParam(2, 4, program);
                getAccountLongfor(program, jsonArrUed, "4");
            }
        }

        // 测试人员
        if (json.get("testingList") != null) {
            String jsonArrTest = json.get("testingList").toString();
            if (!"".equals(jsonArrTest)) {
                deleteByParam(2, 5, program);
                getAccountLongfor(program, jsonArrTest, "5");
            }
        }

        // 业务人员
        if (json.get("businessList") != null) {
            String jsonArrBusiness = json.get("businessList").toString();
            if (!"".equals(jsonArrBusiness)) {
                deleteByParam(2, 6, program);
                getAccountLongfor(program, jsonArrBusiness, "6");
            }
        }

        // 运维人员
        if (json.get("operationList") != null) {
            String jsonArrOperation = json.get("operationList").toString();
            if (!"".equals(jsonArrOperation)) {
                deleteByParam(2, 7, program);
                getAccountLongfor(program, jsonArrOperation, "7");
            }
        }

        // 运营人员
        if (json.get("operateList") != null) {
            String jsonArrOperate = json.get("operateList").toString();
            if (!"".equals(jsonArrOperate)) {
                deleteByParam(2, 8, program);
                getAccountLongfor(program, jsonArrOperate, "8");
            }
        }

    }

    /**
     * 根据规则生成新的项目code
     * 例：IT_XM000001
     * @return
     */
    private String generateProgramNewCode(){
        String newCode = programMapper.getNewCode();
        if (StringUtils.isBlank(newCode)) {
            newCode = "IT_XM000001";
            return newCode;
        }
        Integer newNum = Integer.parseInt(newCode.substring(5,newCode.length()))+1;
        String newNumStr = newNum.toString();
        Integer j  = newNumStr.length();
        for(int i = 0; i< 6 - j; i++) {
            newNumStr = "0" + newNumStr;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("IT_XM" + newNumStr);
        return sb.toString();
    }

    /***
     * 添加关注
     */
    @Override
    public void addProgramFollow(Map<String,String> paramMap){
        ProgramFollow follow = new ProgramFollow();
        follow.setProgramId(Long.parseLong(paramMap.get("programId")));
        follow.setPfAcc(paramMap.get("pfAcc"));
        if (programFollowMapper.selectOne(follow) == null) {
            follow.setCreateTime(new Date());
            programFollowMapper.insert(follow);
        }
    }

    /***
     * 取消关注
     */
    @Override
    public void cancelFollow(Map<String,String> paramMap){
        ProgramFollow follow = new ProgramFollow();
        follow.setPfAcc(paramMap.get("pfAcc"));
        follow.setProgramId(Long.parseLong(paramMap.get("programId")));
        programFollowMapper.delete(follow);
    }

    /***
     * 产品总数list
     */
    @Override
    public List<Product> getListByLikeAnalyzingConditions(Map<String,Object> paramMap,int type){
        paramMap.put("type", type);
        return productMapper.getListByLikeAnalyzingConditions(paramMap);
    }

    /***
     * 項目数
     */
    @Override
    public int getProgramSum(Map<String, Object> paramsMap,int type){
        paramsMap.put("type",type);
        return  programMapper.getCountByProductId(paramsMap);
    }

    /***
     * 异常项目
     */
    @Override
    public Map getExceptionProgramList(Map<String,Object> paramMap,Map resultMap){
        List<ExceptionProgramVo> exceptionList = programApprovalSnapshotMapper.getExceptionProgram(paramMap);
        //总数
        resultMap.put(APIHelper.TOTAL, programApprovalSnapshotMapper.getExceptionProgramTotal(paramMap));
        for (ExceptionProgramVo model:exceptionList) {
            /* 产品经理 */
            Map productManager = new HashMap();
            productManager.put("programId",model.getProgramId());
            productManager.put("employeeType", AvaStatusEnum.MEMBERAVA.getCode());
            productManager.put("employeeTypeId", new Long(AvaStatusEnum.PRODAVA.getCode()));
            List<ProgramEmployee> programManagerList = programEmployeeService.selectTypeList(productManager);
            model.setProductManagerList(programManagerList);
        }
        //列表
        resultMap.put("data",exceptionList);
        return resultMap;
    }

    /***
     * 最新的需求变更
     */
    @Override
    public Map latelyChangeList(Map<String,Object> paramMap,Map resultMap){
        List<ProgramApprovalSnapshot> resultList = programApprovalSnapshotMapper.latelyChangeList(paramMap);
        //总数
        resultMap.put(APIHelper.TOTAL, programApprovalSnapshotMapper.latelyChangeListTotal(paramMap));
        for (ProgramApprovalSnapshot model:resultList) {
            /* 产品经理 */
            productManagerList(model);
        }
        //列表
        resultMap.put("data",resultList);
        return resultMap;
    }

    /* 产品经理 */
    private void productManagerList(ProgramApprovalSnapshot model){
        Map productManager = new HashMap();
        productManager.put("programId",model.getProgramId());
        productManager.put("employeeType", AvaStatusEnum.MEMBERAVA.getCode());
        productManager.put("employeeTypeId", new Long(AvaStatusEnum.PRODAVA.getCode()));
        List<ProgramEmployee> programManagerList = programEmployeeService.selectTypeList(productManager);
        model.setProductManagerList(programManagerList);
    }

    /***
     * 我關注的項目
     */
    @Override
    public Map myFollowProgram(Map<String,Object> paramMap,Map rMap) throws Exception{
        List<Program> list = programMapper.myFollowProgram(paramMap);
        //总数
        rMap.put(APIHelper.TOTAL,  programMapper.myFollowProgramTotal(paramMap));
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Program model:list) {
            Map shopMap = new HashMap();
            //当前项目
            shopMap.put("programId",model.getId());
            //立项时的状态
            shopMap.put("programStatus", ProgramStatusNewEnum.LX.getCode());
            shopMap.put("approvalStatus",ProgramApprovalStatusEnum.SHTG.getCode());
            ProgramApprovalSnapshot lixiangShot = programApprovalSnapshotMapper.getOneByWhere(shopMap);

            Map resultMap = new HashMap();
            resultMap.put("programId",model.getId()); //项目id
            resultMap.put("programName",model.getName());//项目名称
            resultMap.put("currProgramStatus",model.getProgramStatus());//当前项目节点
            if(model!=null && model.getCreateTime()!=null) {
                resultMap.put("currProgramStatusTime", DateUtil.date2String(model.getCreateTime(), DateUtil.PATTERN_DATE));//当前项目节点时间
                //判断项目到那个节点，取那个节点的计划时间
                if(model.getProgramStatus()==ProgramStatusNewEnum.LX.getCode() && model.getCommitDate()!=null){
                    resultMap.put("planProgramStatusTime", DateUtil.date2String(model.getCommitDate(), DateUtil.PATTERN_DATE));//当前项目节点计划时间
                }
                if(model.getProgramStatus()==ProgramStatusNewEnum.DPS.getCode() && model.getDemoApprovalDate()!=null){
                    resultMap.put("planProgramStatusTime", DateUtil.date2String(model.getDemoApprovalDate(), DateUtil.PATTERN_DATE));//当前项目节点计划时间
                }
                if(model.getProgramStatus()==ProgramStatusNewEnum.ZTBSQ.getCode() && model.getBiddingDate()!=null){
                    resultMap.put("planProgramStatusTime", DateUtil.date2String(model.getBiddingDate(), DateUtil.PATTERN_DATE));//当前项目节点计划时间
                }
                if(model.getProgramStatus()==ProgramStatusNewEnum.ZBSQ.getCode() && model.getWinningBidDate()!=null){
                    resultMap.put("planProgramStatusTime", DateUtil.date2String(model.getWinningBidDate(), DateUtil.PATTERN_DATE));//当前项目节点计划时间
                }
                if(model.getProgramStatus()==ProgramStatusNewEnum.CPPS.getCode() && model.getProdApprovalDate()!=null){
                    resultMap.put("planProgramStatusTime", DateUtil.date2String(model.getProdApprovalDate(), DateUtil.PATTERN_DATE));//当前项目节点计划时间
                }
                if(model.getProgramStatus()==ProgramStatusNewEnum.KFPS.getCode() && model.getDevApprovalDate()!=null){
                    resultMap.put("planProgramStatusTime", DateUtil.date2String(model.getDevApprovalDate(), DateUtil.PATTERN_DATE));//当前项目节点计划时间
                }
                if(model.getProgramStatus()==ProgramStatusNewEnum.CSPS.getCode() && model.getTestApprovalDate()!=null){
                    resultMap.put("planProgramStatusTime", DateUtil.date2String(model.getTestApprovalDate(), DateUtil.PATTERN_DATE));//当前项目节点计划时间
                }
                if(model.getProgramStatus()==ProgramStatusNewEnum.SXPS.getCode() && model.getOnlinePlanDate()!=null){
                    resultMap.put("planProgramStatusTime", DateUtil.date2String(model.getOnlinePlanDate(), DateUtil.PATTERN_DATE));//当前项目节点计划时间
                }
                if(model.getProgramStatus()==ProgramStatusNewEnum.HDFB.getCode() && model.getGrayReleaseDate()!=null){
                    resultMap.put("planProgramStatusTime", DateUtil.date2String(model.getGrayReleaseDate(), DateUtil.PATTERN_DATE));//当前项目节点计划时间
                }
                if(model.getProgramStatus()==ProgramStatusNewEnum.XMFP.getCode() && model.getReplayDate()!=null){
                    resultMap.put("planProgramStatusTime", DateUtil.date2String(model.getReplayDate(), DateUtil.PATTERN_DATE));//当前项目节点计划时间
                }
                if(model.getProgramStatus()==ProgramStatusNewEnum.XMFP.getCode() && model.getReplayDate()!=null){
                    resultMap.put("planProgramStatusTime", DateUtil.date2String(model.getReplayDate(), DateUtil.PATTERN_DATE));//当前项目节点计划时间
                }
            }


            resultMap.put("lxProgramStatus",ProgramStatusNewEnum.LX.getCode());//立项状态
            if(lixiangShot!=null && lixiangShot.getCreateTime()!=null){
                resultMap.put("lxTime",DateUtil.date2String(lixiangShot.getCreateTime(),DateUtil.PATTERN_DATE));//立项时间
            }
            resultMap.put("hdProgramStatus",ProgramStatusNewEnum.HDFB.getCode());//灰度发布状态
            if(model!=null && model.getGrayReleaseDate()!=null){
                resultMap.put("hdfbTime",DateUtil.date2String(model.getGrayReleaseDate(),DateUtil.PATTERN_DATE));//灰度发布时间
            }
            resultMap.put("devWorkload",model.getDevWorkload());//评估人天
            resultMap.put("overallCost",model.getOverallCost());//总预算
            mapList.add(resultMap);
        }
        //列表
        rMap.put("data",mapList);
        return rMap;
    }

    /***
     * 需求变更--近三个月变更次数TOP5
     */
    @Override
    public List<Map<String,Object>> changeTopFive(List<Product> productList,Map<String,Object> paramMap){
        return programMapper.changeTopFive(paramMap);
    }

    /***
     * 本年度费用使用情况
     */
    @Override
    public List<Map<String,Object>> yearCost(String year){
        return programMapper.yearCost(year);
    }

    /***
     * 根据一定条件查询文件列表
     */
    @Override
    public List<ProgramFile> getFileListByWhere(Map<String,Object> map){
        return programFileMapper.getFileListByWhere(map);
    }

    /***
     * 导出项目列表
     */
    @Override
    public List<Map<String,Object>> exportProgramList(Map<String,Object> map){
        return programMapper.exportProgramList(map);
    }

    /***
     * 移动端龙信小秘书提醒
     */
    @Override
    public void programTask() throws Exception{
        List<APIProgramTask> apiProgramTaskList = this.getProgramTask();
        for(int i = 0; i < apiProgramTaskList.size(); i++){
//            if(!apiProgramTaskList.get(i).getProgramId().toString().equals("300")){
//                continue;
//            }
            APIProgramTask apiProgramTask = apiProgramTaskList.get(i);
            Map paramMap = longforServiceImpl.param();
            Props props = JoddHelper.getInstance().getJoddProps();
            String openUrl = props.getValue("openUrl.programListPath")+apiProgramTask.getProgramId();
            paramMap.put("ruser",apiProgramTask.getAccountId());
            JSONObject paramMapCont = (JSONObject) paramMap.get("content");
            paramMapCont.put("topTitle",apiProgramTask.getTitle());
            paramMapCont.put("centerWords",apiProgramTask.getContent());
            paramMapCont.put("openUrl",openUrl);
            longforServiceImpl.msgcenter(paramMap);
        }
    }

    private List<APIProgramTask> getProgramTask () throws Exception {
        List<APIProgramTask> apiProgramTasks = new ArrayList<>();
        List<Program> programList = programMapper.selectAll();
        for(Program program:programList){
            if(program.getProgramStatus()==ProgramStatusNewEnum.ZZ.getCode() ||
                    program.getProgramStatus()==ProgramStatusNewEnum.WC.getCode() ){
                continue;
            }
            //提示立项
            if (program.getProgramStatus()==ProgramStatusNewEnum.WLX.getCode()
                    && program.getApprovalStatus()==0
                    && program.getCommitDate()!=null){
                this.getTaskList(apiProgramTasks,program,program.getCommitDate());
            }
            //提示Demo评审
            if (program.getProgramStatus()==ProgramStatusNewEnum.LX.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getDemoApprovalDate()!=null ){
                this.getTaskList(apiProgramTasks,program,program.getDemoApprovalDate());
            }
            //提示产品评审
            if (program.getProgramStatus()==ProgramStatusNewEnum.DPS.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getProdApprovalDate()!=null ){
                this.getTaskList(apiProgramTasks,program,program.getProdApprovalDate());
            }
            //提示开发评审
            if (program.getProgramStatus()==ProgramStatusNewEnum.CPPS.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getDevApprovalDate()!=null ){
                this.getTaskList(apiProgramTasks,program,program.getDevApprovalDate());
            }
            //提示测试评审
            if (program.getProgramStatus()==ProgramStatusNewEnum.KFPS.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getTestApprovalDate()!=null){
                this.getTaskList(apiProgramTasks,program,program.getTestApprovalDate());
            }
            //提示上线计划
            if (program.getProgramStatus()==ProgramStatusNewEnum.CSPS.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getOnlinePlanDate()!=null ){
                this.getTaskList(apiProgramTasks,program,program.getOnlinePlanDate());
            }
            //提示灰度发布
            if (program.getProgramStatus()==ProgramStatusNewEnum.SXPS.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getGrayReleaseDate()!=null ){
                this.getTaskList(apiProgramTasks,program,program.getGrayReleaseDate());
            }
            //提示全面推广
//            if (program.getProgramStatus()==ProgramStatusNewEnum.HDFB.getCode()
//                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
//                    && program.getAllExtensionDate()!=null){
//                this.getTaskList(apiProgramTasks,program,program.getAllExtensionDate());
//            }
            //提示项目复盘
//            if (program.getProgramStatus()==ProgramStatusNewEnum.QMTG.getCode()
//                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
//                    && program.getReplayDate()!=null ){
//                this.getTaskList(apiProgramTasks,program,program.getReplayDate());
//            }
        }
        return apiProgramTasks;
    }

    private void getTaskList(List<APIProgramTask> apiProgramTasks,Program program,Date planDate){
        Date now = new Date();
        int differentDays = DateUtil.differentDays(now,planDate);
        if (differentDays<=3) {
            //获取发送小秘书的人员
            List<ProgramEmployee> empList = this.taskProgramEmployee(program);
            for(ProgramEmployee emp:empList){
                APIProgramTask apiProgramTask = new APIProgramTask();
                apiProgramTask.setProgramId(program.getId());
                apiProgramTask.setTitle("项目进度提醒");
                apiProgramTask.setContent(this.taskContent(differentDays,program,1));
                apiProgramTask.setAccountId(emp.getAccountId());
                apiProgramTasks.add(apiProgramTask);
            }
            //逾期提示管理层：蒋正浩、左恩泽、李骏岩、傅志华
            if(differentDays<0){
                //蒋正浩
                APIProgramTask apiProgramTask = new APIProgramTask();
                apiProgramTask.setProgramId(program.getId());
                apiProgramTask.setTitle("项目进度提醒");
                apiProgramTask.setContent(this.taskContent(differentDays,program,0));
                apiProgramTask.setAccountId("jiangzhenghao");
                apiProgramTasks.add(apiProgramTask);
                //左恩泽
                APIProgramTask apiProgramTask1 = new APIProgramTask();
                apiProgramTask1.setProgramId(program.getId());
                apiProgramTask1.setTitle("项目进度提醒");
                apiProgramTask1.setContent(this.taskContent(differentDays,program,0));
                apiProgramTask1.setAccountId("zuoenze");
                apiProgramTasks.add(apiProgramTask1);
                //李骏岩
                APIProgramTask apiProgramTask2 = new APIProgramTask();
                apiProgramTask2.setProgramId(program.getId());
                apiProgramTask2.setTitle("项目进度提醒");
                apiProgramTask2.setContent(this.taskContent(differentDays,program,0));
                apiProgramTask2.setAccountId("lijyan");
                apiProgramTasks.add(apiProgramTask2);
                //傅志华
                APIProgramTask apiProgramTask3 = new APIProgramTask();
                apiProgramTask3.setProgramId(program.getId());
                apiProgramTask3.setTitle("项目进度提醒");
                apiProgramTask3.setContent(this.taskContent(differentDays,program,0));
                apiProgramTask3.setAccountId("fuzhihua");
                apiProgramTasks.add(apiProgramTask3);
            }
        }
    }

    /**
     * 龙信小秘书提示信息人员
     * @param program
     * @return
     */
    private List<ProgramEmployee> taskProgramEmployee(Program program){
        List<ProgramEmployee> taskEmployee = new ArrayList<ProgramEmployee>();
        Map map = new HashMap();
        map.put("programId",program.getId());
        /*产品评审通过，提示开发人员*/
        if (program.getProgramStatus()==ProgramStatusNewEnum.CPPS.getCode()){
            /* 开发人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.DEVEAVA.getCode()));
        }
        /*开发评审通过，提示测试人员*/
        if (program.getProgramStatus()==ProgramStatusNewEnum.KFPS.getCode()){
            /* 测试人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.TESTINGAVA.getCode()));
        }
        taskEmployee.addAll(programEmployeeService.selectPersonList(map));
        return taskEmployee;
    }
    /**
     * 龙信小秘书提示信息内容
     * @param program
     * @return
     */
    private String taskContent(int differentDays, Program program,int role){
        String text = "";
        String noteName = "";
        String noteAction = "";
        if (program.getProgramStatus()==ProgramStatusNewEnum.WLX.getCode()){
            noteName = ProgramStatusNewEnum.LX.getText();
            noteAction = ProgramStatusNewEnum.LX.getText();
        }
        if (program.getProgramStatus()==ProgramStatusNewEnum.LX.getCode()){
            noteName = ProgramStatusNewEnum.DPS.getText();
            noteAction = ProgramStatusNewEnum.DPS.getText();
        }
        if (program.getProgramStatus()==ProgramStatusNewEnum.DPS.getCode()){
            noteName = ProgramStatusNewEnum.CPPS.getText();
            noteAction = ProgramStatusNewEnum.CPPS.getText();
        }
        if (program.getProgramStatus()==ProgramStatusNewEnum.CPPS.getCode()){
            noteName = ProgramStatusNewEnum.KFPS.getText();
            noteAction = ProgramStatusNewEnum.KFPS.getText();
        }
        if (program.getProgramStatus()==ProgramStatusNewEnum.KFPS.getCode()){
            noteName = ProgramStatusNewEnum.CSPS.getText();
            noteAction = ProgramStatusNewEnum.CSPS.getText();
        }
        if (program.getProgramStatus()==ProgramStatusNewEnum.CSPS.getCode()){
            noteName = ProgramStatusNewEnum.SXPS.getText();
            noteAction = ProgramStatusNewEnum.SXPS.getText();
        }
        if (program.getProgramStatus()==ProgramStatusNewEnum.SXPS.getCode()){
            noteName = ProgramStatusNewEnum.HDFB.getText();
            noteAction = ProgramStatusNewEnum.HDFB.getText();
        }
        if (program.getProgramStatus()==ProgramStatusNewEnum.HDFB.getCode()){
            noteName = ProgramStatusNewEnum.QMTG.getText();
            noteAction = ProgramStatusNewEnum.QMTG.getText();
        }
        if (program.getProgramStatus()==ProgramStatusNewEnum.QMTG.getCode()){
            noteName = ProgramStatusNewEnum.XMFP.getText();
            noteAction = ProgramStatusNewEnum.XMFP.getText();
        }
        //管理层蒋正浩、左恩泽、李骏岩、傅志华,提示信息
        if (role == 0) {
            text = "【"+program.getName()+"】已逾期，请及时关注。";
            return text;
        }
        //项目负责人提示信息
        if(differentDays>0){
            text = "【"+program.getName()+"】将于"+differentDays+"天后到达"+noteName+"节点，请及时完成"+noteAction;
        }
        if(differentDays==0){
            text = "【"+program.getName()+"】于今日到达"+noteName+"节点，请及时完成"+noteAction;
        }
        if(differentDays<0){
            text = "【"+program.getName()+"】已在"+noteName+"节点逾期"+Math.abs(differentDays)+"天，请尽快到IT+PC端完成";
        }
        return text;
    }

    @Override
    public void warningDays() throws Exception{
        programMapper.updateWarningDays();//修改相差天数值
        List<Program> programList = programMapper.selectAll();
        for(Program program:programList){
            if(program.getProgramStatus()==ProgramStatusNewEnum.ZZ.getCode() ||
                    program.getProgramStatus()==ProgramStatusNewEnum.WC.getCode() ){
                continue;
            }
            //提示立项
            if (program.getProgramStatus()==ProgramStatusNewEnum.WLX.getCode()
                    && program.getApprovalStatus()==0
                    && program.getCommitDate()!=null){
                this.updateWarningDays(program,program.getCommitDate());
            }
            //提示Demo评审
            if (program.getProgramStatus()==ProgramStatusNewEnum.LX.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getDemoApprovalDate()!=null ){
                this.updateWarningDays(program,program.getDemoApprovalDate());
            }
            //提示产品评审
            if (program.getProgramStatus()==ProgramStatusNewEnum.DPS.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getProdApprovalDate()!=null ){
                this.updateWarningDays(program,program.getProdApprovalDate());
            }
            //提示开发评审
            if (program.getProgramStatus()==ProgramStatusNewEnum.CPPS.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getDevApprovalDate()!=null ){
                this.updateWarningDays(program,program.getDevApprovalDate());
            }
            //提示测试评审
            if (program.getProgramStatus()==ProgramStatusNewEnum.KFPS.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getTestApprovalDate()!=null){
                this.updateWarningDays(program,program.getTestApprovalDate());
            }
            //提示上线计划
            if (program.getProgramStatus()==ProgramStatusNewEnum.CSPS.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getOnlinePlanDate()!=null ){
                this.updateWarningDays(program,program.getOnlinePlanDate());
            }
            //提示灰度发布
            if (program.getProgramStatus()==ProgramStatusNewEnum.SXPS.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getGrayReleaseDate()!=null ){
                this.updateWarningDays(program,program.getGrayReleaseDate());
            }
            //提示全面推广
            if (program.getProgramStatus()==ProgramStatusNewEnum.HDFB.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getAllExtensionDate()!=null){
                this.updateWarningDays(program,program.getAllExtensionDate());
            }
            //提示项目复盘
            if (program.getProgramStatus()==ProgramStatusNewEnum.QMTG.getCode()
                    && program.getApprovalStatus()==ProgramApprovalStatusEnum.SHTG.getCode()
                    && program.getReplayDate()!=null ){
                this.updateWarningDays(program,program.getReplayDate());
            }
        }
    }

    /**
     *  differentDays>3天的，按照绿色的正常显示，
     *  0<=differentDays<=3天的，按照黄色的显示，
     *  differentDays<0天的，按照红色的显示，
     * @param program
     * @param planDate
     */
    private void updateWarningDays(Program program,Date planDate){
        Integer differentDays = null;
        Date now = new Date();
        //首先查找是否有手动预警的数据，手动预警的优先级最高
        Map<String,Object> map = new HashMap<>();
        map.put("programId", program.getId());
//        if(program.getApprovalStatus()== ProgramApprovalStatusEnum.SHTG.getCode()){
//            if(program.getProgramStatus() == ProgramStatusNewEnum.WLX.getCode()){
//                map.put("programStatus", ProgramStatusNewEnum.LX.getCode());
//            }
//            if(program.getProgramStatus() == ProgramStatusNewEnum.LX.getCode()){
//                map.put("programStatus", ProgramStatusNewEnum.DPS.getCode());
//            }
//            if(program.getProgramStatus() == ProgramStatusNewEnum.DPS.getCode()){
//                map.put("programStatus", ProgramStatusNewEnum.CPPS.getCode());
//            }
//            if(program.getProgramStatus() == ProgramStatusNewEnum.CPPS.getCode()){
//                map.put("programStatus", ProgramStatusNewEnum.KFPS.getCode());
//            }
//            if(program.getProgramStatus() == ProgramStatusNewEnum.KFPS.getCode()){
//                map.put("programStatus", ProgramStatusNewEnum.CSPS.getCode());
//            }
//            if(program.getProgramStatus() == ProgramStatusNewEnum.CSPS.getCode()){
//                map.put("programStatus", ProgramStatusNewEnum.SXPS.getCode());
//            }
//            if(program.getProgramStatus() == ProgramStatusNewEnum.SXPS.getCode()){
//                map.put("programStatus", ProgramStatusNewEnum.HDFB.getCode());
//            }
//            if(program.getProgramStatus() == ProgramStatusNewEnum.HDFB.getCode()){
//                map.put("programStatus", ProgramStatusNewEnum.QMTG.getCode());
//            }
//            if(program.getProgramStatus() == ProgramStatusNewEnum.QMTG.getCode()){
//                map.put("programStatus", ProgramStatusNewEnum.XMFP.getCode());
//            }
//            if(program.getProgramStatus() == ProgramStatusNewEnum.XMFP.getCode()){
//                map.put("programStatus", ProgramStatusNewEnum.WC.getCode());
//            }
//        } else {
//            map.put("programStatus", program.getProgramStatus());
//        }
        ProgramWarning programWarning = programWarningService.getOneByWhere(map);
        if (programWarning != null){
            //预警值：0=绿色正常，1=黄色中风险-存在潜在问题，进度可控，2=红色高风险-可能导致延期，进度不可控
            //0=绿色正常
            if (programWarning.getWarning()==0){
                differentDays = DateUtil.differentDays(now,planDate);
            }
            //黄色中风险的按照黄色的预警显示，预期时间给0天，以便排序
            if (programWarning.getWarning()==1){
                differentDays = 0;
            }
            //逾期的按照红色的预警显示，预期时间给-100天，以便排序
            if (programWarning.getWarning()==2){
                differentDays = -100;
            }
        } else {
            //无手动预警的数据，按照正常的显示日期差
            differentDays = DateUtil.differentDays(now,planDate);
        }
        Program newProgram = new Program();
        newProgram.setId(program.getId());
        newProgram.setWarningDays(differentDays);
        programMapper.updateByPrimaryKeySelective(newProgram);
    }

}