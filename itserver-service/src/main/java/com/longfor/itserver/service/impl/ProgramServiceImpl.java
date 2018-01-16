package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.*;
import com.longfor.itserver.common.util.DateUtil;
import com.longfor.itserver.common.util.HttpUtil;
import com.longfor.itserver.common.util.StringUtil;
import com.longfor.itserver.common.vo.MoApprove.MoApproveListVo;
import com.longfor.itserver.common.vo.MoApprove.MoApproveVo;
import com.longfor.itserver.common.vo.programBpm.ApproveListVo;
import com.longfor.itserver.common.vo.programBpm.ApproveVo;
import com.longfor.itserver.common.vo.programBpm.common.ApplyCreateResultVo;
import com.longfor.itserver.common.vo.programBpm.common.ApplySubmitResultVo;
import com.longfor.itserver.common.vo.programBpm.common.FileVo;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.esi.bpm.ProgramBpmUtil;
import com.longfor.itserver.mapper.*;
import com.longfor.itserver.service.IProgramEmployeeService;
import com.longfor.itserver.service.IProgramService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
import net.mayee.commons.TimeUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Length;

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
    private ProgramEmployeeChangeLogMapper programEmployeeChangeLogMapper;

    @Autowired
    private ProgramApprovalSnapshotMapper programApprovalSnapshotMapper;
    @Autowired
    private ProgramFileMapper programFileMapper;
    @Override
    public List<Program> programList(Map map) {
        List<Program> programList = programMapper.programList(map);
        for (Program model:programList) {
            ProgramFollow follow = new ProgramFollow();
            follow.setPfAcc(map.get("accountId").toString());
            follow.setProgramId(model.getId());
            model.setIsFollow(programFollowMapper.selectCount(follow));
        }
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
        pMap.put("approvalStatus", ProgramApprovalStatusEnum.SHZ.getCode());
        List<ProgramApprovalSnapshot> s = programApprovalSnapshotMapper.grayLevelList(pMap);
        if (s==null || s.isEmpty()) {
            pMap.put("approvalStatus", ProgramApprovalStatusEnum.BGSHZ.getCode());
            s = programApprovalSnapshotMapper.grayLevelList(pMap);
        }
        if (s != null && !s.isEmpty()) {
            this.setProgramApprovalSnapshotInfo(s.get(0),shot);
        }
        return shot;
    }

    @Override
    @Transactional
    public boolean addProgram(Map map) {
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        Integer accountType = AccountUitl.getAccountType(map);
        Program program = JSONObject.toJavaObject(json, Program.class);
        Product product = productMapper.selectByPrimaryKey(program.getProductId());
        if(product == null){
            return false;
        }
        program.setProgramStatus(ProgramStatusNewEnum.WLX.getCode());
        program.setUid(UUID.randomUUID().toString());
        program.setProductName(product.getName());
        program.setProductCode(product.getCode());
        program.setCreateTime(TimeUtils.getTodayByDateTime());
        program.setModifiedTime(TimeUtils.getTodayByDateTime());
        program.setAccountType(accountType);
        program.setNewCode(this.generateProgramNewCode());
        programMapper.insert(program);

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
            program.setProductName(product.getName());
        }

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
        Integer accountType = AccountUitl.getAccountType(map);
        //先生成变动日志
        List<String> changeLogTextList = getChangeLogText(selectOneProgram, program);

        if (null == selectOneProgram) {
            return false;
        }
        selectOneProgram.setName(program.getName());
        selectOneProgram.setProductId(program.getProductId());
        selectOneProgram.setDescp(program.getDescp());
//        selectOneProgram.setCommitDate(program.getCommitDate());
//        selectOneProgram.setStartDate(program.getStartDate());
//        selectOneProgram.setUedDate(program.getUedDate());
//        selectOneProgram.setArchitectureDate(program.getArchitectureDate());
//        selectOneProgram.setGrayReleaseDate(program.getGrayReleaseDate());
//        selectOneProgram.setReleaseDate(program.getReleaseDate());
        selectOneProgram.setLikeProduct(program.getLikeProduct());
        selectOneProgram.setLikeProgram(program.getLikeProgram());
        selectOneProgram.setType(program.getType());
//        selectOneProgram.setProgramStatus(program.getProgramStatus());
        selectOneProgram.setAccountType(accountType);
        selectOneProgram.setModifiedTime(TimeUtils.getTodayByDateTime());
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
                    .append(" 修改了项目基础信息");
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
        Long productId = Long.valueOf((String) parsmsMap.get("productId"));
        Program program = new Program();
        program.setProductId(productId);
        List<Program> programList =	programMapper.select(program);
        Product product = productMapper.selectByPrimaryKey(productId);
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

            //更新项目表
            program.setApprovalStatus(ProgramApprovalStatusEnum.SHTG.getCode());
            program.setModifiedTime(now);
            getApprovelAfterProgramStatus(programApprovalSnapshot.getBpmCode(),program,programApprovalSnapshot.getProgramStatus());
            programMapper.updateByPrimaryKey(program);

            //program快照表
            this.copyProperties(programApprovalSnapshot,program);
            if (programApprovalSnapshot.getProgramStatus() == ProgramStatusNewEnum.XQBG.getCode()
                    || programApprovalSnapshot.getProgramStatus() == ProgramStatusNewEnum.YQSX.getCode()) {
                programApprovalSnapshot.setProgramStatus(programApprovalSnapshot.getProgramStatus());
            }
//            programApprovalSnapshot.setId(null);
//            programApprovalSnapshot.setProgramStatus(program.getProgramStatus());
//            programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.SHTG.getCode());
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setSuggestion(paramsMap.get("suggestion"));
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);

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
            Date now = new Date();

            //更新项目表
            program.setApprovalStatus(ProgramApprovalStatusEnum.SHBH.getCode());
            program.setModifiedTime(now);
            programMapper.updateByPrimaryKey(program);

            programApprovalSnapshot.setId(null);
            programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.SHBH.getCode());
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
            String causeDelay = paramsMap.get("causeDelay");

            //创建流程
            ApplyCreateResultVo applyCreateResultVo = new ApplyCreateResultVo();
            if ("2".equals(causeDelay)) {
                applyCreateResultVo = ProgramBpmUtil.createApplyWorkFlow(paramsMap);
                if (!applyCreateResultVo.isSuccess()) {
                    LOG.error("创建流程失败:" + JSON.toJSONString(paramsMap) + "-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
            }

            //program表
            if ("2".equals(causeDelay)) {
                program.setApprovalStatus(ProgramApprovalStatusEnum.BGSHZ.getCode());
            }
            if ("1".equals(causeDelay)) {
                program.setDevWorkload(Integer.parseInt(paramsMap.get("devWorkloadChange")));
                //变更预期费用
                String overallCost = paramsMap.get("overallCost");
                BigDecimal overallCostBig = new BigDecimal(overallCost);
                program.setOverallCost(overallCostBig);
            }
            program.setGrayReleaseDate(DateUtil.string2Date(paramsMap.get("grayReleaseDate"),DateUtil.PATTERN_DATE));
            program.setProdApprovalDate(DateUtil.string2Date(paramsMap.get("demandDate"),DateUtil.PATTERN_DATE));
            program.setDevApprovalDate(DateUtil.string2Date(paramsMap.get("developmentDate"),DateUtil.PATTERN_DATE));
            program.setTestApprovalDate(DateUtil.string2Date(paramsMap.get("testReviewDate"),DateUtil.PATTERN_DATE));
            program.setOnlinePlanDate(DateUtil.string2Date(paramsMap.get("onlineDate"),DateUtil.PATTERN_DATE));

            program.setAccountType(Integer.parseInt(paramsMap.get("accountType")));
            program.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
            program.setModifiedName(paramsMap.get("modifiedName"));
            programMapper.updateByPrimaryKey(program);

            //program快照表
            ProgramApprovalSnapshot programApprovalSnapshot = new ProgramApprovalSnapshot();
            this.copyProperties(programApprovalSnapshot,program);
            if ("2".equals(causeDelay)) {
                programApprovalSnapshot.setBpmCode(applyCreateResultVo.getInstanceID());
                programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.BGSHZ.getCode());
            } else if ("1".equals(causeDelay)) {
//                programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.SHTG.getCode());
            }
            programApprovalSnapshot.setCauseDelay(causeDelay);
            programApprovalSnapshot.setProgramStatus(ProgramStatusNewEnum.YQSX.getCode());
            programApprovalSnapshot.setRemark(paramsMap.get("remark"));
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);

            //附件表
            this.dealFileList(paramsMap.get("fileList"),program.getId(),ProgramStatusNewEnum.YQSX.getCode(),programApprovalSnapshot.getId());


            if ("2".equals(causeDelay)) {
                //激活流程
                ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtil.applySumbmitWorkItem(
                        paramsMap.get("modifiedAccountId"),applyCreateResultVo.getWorkItemID());
                if(pplySubmitResultVo.getIsSuccess().equals("false")){
                    LOG.error("激活流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("激活流程失败");
                }
            }

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

            ApplyCreateResultVo applyCreateResultVo = new ApplyCreateResultVo();
            if (overallCost.compareTo(ten) != -1) {//大于10万走审批
                //创建流程
                applyCreateResultVo = ProgramBpmUtil.createApplyWorkFlow(paramsMap);
                if (!applyCreateResultVo.isSuccess()) {
                    LOG.error("创建流程失败:" + JSON.toJSONString(paramsMap) + "-----------------------");
                    throw new RuntimeException("创建流程失败");
                }
            } else {//小于10万走通知

            }

            //program表
//			program.setProgramStatus(ProgramStatusNewEnum.XQBG.getCode());

            program.setDevWorkload(Integer.parseInt(paramsMap.get("devWorkloadChange")));
            program.setOverallCost(overallCost);
            program.setGrayReleaseDate(DateUtil.string2Date(paramsMap.get("grayReleaseDate"),DateUtil.PATTERN_DATE));
            program.setProdApprovalDate(DateUtil.string2Date(paramsMap.get("demandDate"),DateUtil.PATTERN_DATE));
            program.setDevApprovalDate(DateUtil.string2Date(paramsMap.get("developmentDate"),DateUtil.PATTERN_DATE));
            program.setTestApprovalDate(DateUtil.string2Date(paramsMap.get("testReviewDate"),DateUtil.PATTERN_DATE));
            program.setOnlinePlanDate(DateUtil.string2Date(paramsMap.get("onlineDate"),DateUtil.PATTERN_DATE));
            if (overallCost.compareTo(ten) != -1) {
                program.setApprovalStatus(ProgramApprovalStatusEnum.BGSHZ.getCode());
            }


            program.setAccountType(Integer.parseInt(paramsMap.get("accountType")));
            program.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
            program.setModifiedName(paramsMap.get("modifiedName"));
            programMapper.updateByPrimaryKey(program);

            //program快照表
            ProgramApprovalSnapshot programApprovalSnapshot = new ProgramApprovalSnapshot();
            this.copyProperties(programApprovalSnapshot,program);
            programApprovalSnapshot.setProgramStatus(ProgramStatusNewEnum.XQBG.getCode());
            if (overallCost.compareTo(ten) != -1) {
                programApprovalSnapshot.setBpmCode(applyCreateResultVo.getInstanceID());
                programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.BGSHZ.getCode());
            } else {
                programApprovalSnapshot.setApprovalStatus(ProgramApprovalStatusEnum.SHTG.getCode());
            }

            programApprovalSnapshot.setRemark(paramsMap.get("remark"));
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);

            //附件表
            this.dealFileList(paramsMap.get("fileList"),program.getId(),ProgramStatusNewEnum.XQBG.getCode(),programApprovalSnapshot.getId());


            if (overallCost.compareTo(ten) != -1) {
                //激活流程
                ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtil.applySumbmitWorkItem(
                        paramsMap.get("modifiedAccountId"),applyCreateResultVo.getWorkItemID());
                if(pplySubmitResultVo.getIsSuccess().equals("false")){
                    LOG.error("激活流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                    throw new RuntimeException("激活流程失败");
                }
            } else {//小于10万走通知

            }
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

            //创建流程
            ApplyCreateResultVo applyCreateResultVo = ProgramBpmUtil.createApplyWorkFlow(paramsMap);
            if(!applyCreateResultVo.isSuccess()){
                LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("创建流程失败");
            }

            //program表
//            program.setProgramStatus(ProgramStatusNewEnum.ZZ.getCode());
            program.setApprovalStatus(ProgramApprovalStatusEnum.SHZ.getCode());
            program.setAccountType(Integer.parseInt(paramsMap.get("accountType")));
            program.setModifiedAccountId(paramsMap.get("modifiedAccountId"));
            program.setModifiedName(paramsMap.get("modifiedName"));
            programMapper.updateByPrimaryKey(program);

            //program快照表
            ProgramApprovalSnapshot programApprovalSnapshot = new ProgramApprovalSnapshot();
            this.copyProperties(programApprovalSnapshot,program);
            programApprovalSnapshot.setProgramStatus(ProgramStatusNewEnum.ZZ.getCode());
            programApprovalSnapshot.setBpmCode(applyCreateResultVo.getInstanceID());
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);

            //激活流程
            ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtil.applySumbmitWorkItem(
                    paramsMap.get("modifiedAccountId"),applyCreateResultVo.getWorkItemID());
            if(pplySubmitResultVo.getIsSuccess().equals("false")){
                LOG.error("激活流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("激活流程失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发生异常");
        }
    }

    private void dealFileList(String fileStr,Long programId , int programStatus, Long snapshotId) {
        if (org.apache.commons.lang.StringUtils.isNotBlank(fileStr)) {
            List<FileVo> fileList = JSON.parseArray(fileStr, FileVo.class);
            if (fileList != null && !fileList.isEmpty()) {
                String a = fileList.get(0).getFileName();
                for (FileVo fileVo : fileList) {
                    ProgramFile programFile = new ProgramFile();
                    programFile.setProgramId(programId);
                    programFile.setFileName(fileVo.getFileName());
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

    @Override
    @Transactional(value="transactionManager")
    public void submit(Map<String, String> paramsMap,Program program,int programStatus) {
        try{
            Date now = new Date();
            //创建流程
            ApplyCreateResultVo applyCreateResultVo = ProgramBpmUtil.createApplyWorkFlow(paramsMap);
            if(!applyCreateResultVo.isSuccess()){
                LOG.error("创建流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("创建流程失败");
            }

            int approvalStatus = ProgramApprovalStatusEnum.SHZ.getCode();

            String devType = paramsMap.get("devType");
            String analyzingConditions = paramsMap.get("analyzingConditions");
            String devWorkload = paramsMap.get("devWorkload");
            String overallCost = paramsMap.get("overallCost");
            String commitDate = paramsMap.get("commitDate");
            String demoApprovalDate = paramsMap.get("demoApprovalDate");
            String grayReleaseDate = paramsMap.get("grayReleaseDate");
            String biddingDate = paramsMap.get("biddingDate");
            String winningBidDate = paramsMap.get("winningBidDate");
            String bidOverallCost = paramsMap.get("bidOverallCost");
            String bidDevWorkload = paramsMap.get("bidDevWorkload");
            String bidOversingleCost = paramsMap.get("bidOversingleCost");
            String productReviewDate = paramsMap.get("productReviewDate");
            String researchDate = paramsMap.get("researchDate");
            String testDate = paramsMap.get("testDate");
            String onlineDate = paramsMap.get("onlineDate");
            String productId = paramsMap.get("productId");
            String productName = paramsMap.get("productName");
            String likeProduct = paramsMap.get("likeProduct");
            String type = paramsMap.get("type");

            //program表
            if(StringUtils.isNotBlank(devType))program.setDevType(Integer.parseInt(devType));//研发方式
            if(StringUtils.isNotBlank(analyzingConditions))program.setAnalyzingConditions(Integer.parseInt(analyzingConditions));//判断条件
            if(StringUtils.isNotBlank(devWorkload))program.setDevWorkload(Integer.parseInt(devWorkload));//研发工作量预估
            if(StringUtils.isNotBlank(overallCost))program.setOverallCost(new BigDecimal(overallCost));//整体费用预估
            if(StringUtils.isNotBlank(commitDate))program.setCommitDate(DateUtil.string2Date(commitDate,DateUtil.PATTERN_DATE));//立项时间
            if(StringUtils.isNotBlank(demoApprovalDate))program.setDemoApprovalDate(DateUtil.string2Date(demoApprovalDate,DateUtil.PATTERN_DATE));//demo评审时间
            if(StringUtils.isNotBlank(grayReleaseDate))program.setGrayReleaseDate(DateUtil.string2Date(grayReleaseDate,DateUtil.PATTERN_DATE));//灰度发布时间
            if(StringUtils.isNotBlank(biddingDate))program.setBiddingDate(DateUtil.string2Date(biddingDate,DateUtil.PATTERN_DATE));//招标时间
            if(StringUtils.isNotBlank(winningBidDate))program.setWinningBidDate(DateUtil.string2Date(winningBidDate,DateUtil.PATTERN_DATE));//中标时间
            if(StringUtils.isNotBlank(bidOverallCost))program.setBidOverallCost(new BigDecimal(bidOverallCost));//整体费用
            if(StringUtils.isNotBlank(bidDevWorkload))program.setBidDevWorkload(Integer.parseInt(bidDevWorkload));//人天（框架合同填写）
            if(StringUtils.isNotBlank(bidOversingleCost))program.setBidOversingleCost(new BigDecimal(bidOversingleCost));//人天单价（框架合同填写）
            if(StringUtils.isNotBlank(productReviewDate))program.setProdApprovalDate(DateUtil.string2Date(productReviewDate,DateUtil.PATTERN_DATE));//产品评审时间
            if(StringUtils.isNotBlank(researchDate))program.setDevApprovalDate(DateUtil.string2Date(researchDate,DateUtil.PATTERN_DATE));//研发评审时间
            if(StringUtils.isNotBlank(testDate))program.setTestApprovalDate(DateUtil.string2Date(testDate,DateUtil.PATTERN_DATE));//测试评审时间
            if(StringUtils.isNotBlank(onlineDate))program.setOnlinePlanDate(DateUtil.string2Date(onlineDate,DateUtil.PATTERN_DATE));//上线计划时间
            if(StringUtils.isNotBlank(productId))program.setProductId(Long.parseLong(productId));
            if(StringUtils.isNotBlank(productName))program.setProductName(productName);
            if(StringUtils.isNotBlank(likeProduct))program.setLikeProduct(likeProduct);
            if(StringUtils.isNotBlank(type))program.setType(Integer.parseInt(type));

            program.setApprovalStatus(approvalStatus);
            program.setProgramStatus(programStatus);
            program.setModifiedTime(now);
            programMapper.updateByPrimaryKey(program);

            this.dealProgramEmployee(paramsMap, program);

            //program快照表
            ProgramApprovalSnapshot programApprovalSnapshot = new ProgramApprovalSnapshot();
            this.copyProperties(programApprovalSnapshot,program);
            programApprovalSnapshot.setBpmCode(applyCreateResultVo.getInstanceID());
            programApprovalSnapshot.setRemark(paramsMap.get("remark"));
            programApprovalSnapshot.setCreateTime(now);
            programApprovalSnapshot.setModifiedTime(now);
            programApprovalSnapshot.setApplyAccount(paramsMap.get("modifiedAccountId"));
            programApprovalSnapshot.setReportPoor(paramsMap.get("reportPoor"));
            programApprovalSnapshotMapper.insert(programApprovalSnapshot);

            //附件表
            this.dealFileList(paramsMap.get("fileList"),program.getId(),programStatus,programApprovalSnapshot.getId());

            //激活流程
            ApplySubmitResultVo pplySubmitResultVo = ProgramBpmUtil.applySumbmitWorkItem(
                    paramsMap.get("modifiedAccountId"),applyCreateResultVo.getWorkItemID());
            if(pplySubmitResultVo.getIsSuccess().equals("false")){
                LOG.error("激活流程失败:"+ JSON.toJSONString(paramsMap)+"-----------------------");
                throw new RuntimeException("激活流程失败");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发生异常");
        }
    }

    private void copyProperties(ProgramApprovalSnapshot programApprovalSnapshot,Program program) throws Exception{
        BeanUtils.copyProperties(programApprovalSnapshot,program);
        programApprovalSnapshot.setId(null);
        programApprovalSnapshot.setProgramId(program.getId());
    }

    /***
     * 根据需求ID 获取快照信息
     */
    @Override
    public ProgramApprovalSnapshot getSnapshot(Long id)  throws Exception{
        ProgramApprovalSnapshot shot = programApprovalSnapshotMapper.selectByPrimaryKey(id);
        this.setProgramApprovalSnapshotInfo(shot,shot);
        return shot;
    }

    /**
     * 根据员工oa账号获取员工guid
     * 多个员工，以‘，’分割，返回guid也以‘，’分割
     * @param url
     * @param token
     * @param param
     * @return
     */
    private String getEmpGuidByPfAcc(String url,String token,String param){
        JSONObject json = HttpUtil.post(url,token,param);
        List<Map<String,Object>> jsonList = JSON.parseObject(JSON.toJSONString(json.get("list")),List.class);
        if (jsonList == null || jsonList.isEmpty()) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (Map<String,Object> model:jsonList) {
            sb.append(model.get("psGuid"));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
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
    public ApproveListVo getApprovelapprovList(MoApproveListVo moApproveListVo) throws Exception{
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
        ApproveVo resultVo = new ApproveVo();
        for(MoApproveVo moApproveVo:moApproveVoList){
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
            resultVo.setApplyName(tmpSna.getApplyAccount());
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
    public int getProgramSum(List<Product> productList,int type){
        if (productList == null || productList.isEmpty()) {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        for (Product model:productList) {
            sb.append(model.getId());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        Map<String,Object> map = new HashMap<>();
        map.put("productIdList",sb.toString().split(","));
        map.put("type",type);
        return  programMapper.getCountByProductId(map);
    }

    /***
     * 异常项目
     */
    @Override
    public List<ExceptionProgramVo> getExceptionProgramList(Map<String,Object> paramMap){
        List<Product> productList = getListByLikeAnalyzingConditions(paramMap,0);
        if (productList==null || productList.isEmpty()) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (Product model:productList) {
            sb.append(model.getId());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        Map<String,Object> map = new HashMap<>();
        map.put("programIdList",sb.toString().split(","));
        map.put("startRow", paramMap.get("startRow") );
        map.put("endRow", paramMap.get("endRow") );
        List<ExceptionProgramVo> exceptionList = programApprovalSnapshotMapper.getExceptionProgram(map);
        for (ExceptionProgramVo model:exceptionList) {
            /* 产品经理 */
            Map productManager = new HashMap();
            productManager.put("programId",model.getProgramId());
            productManager.put("employeeType", AvaStatusEnum.MEMBERAVA.getCode());
            productManager.put("employeeTypeId", new Long(AvaStatusEnum.PRODAVA.getCode()));
            List<ProgramEmployee> programManagerList = programEmployeeService.selectTypeList(productManager);
            model.setProductManagerList(programManagerList);
        }
        return exceptionList;
    }

    /***
     * 最新的需求变更
     */
    @Override
    public List<ProgramApprovalSnapshot> latelyChangeList(Map<String,Object> paramMap){
        List<Product> productList = getListByLikeAnalyzingConditions(paramMap,0);
        if (productList==null || productList.isEmpty()) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (Product model:productList) {
            sb.append(model.getId());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        Map<String,Object> map = new HashMap<>();
        map.put("programIdList",sb.toString().split(","));
        map.put("startRow", paramMap.get("startRow") );
        map.put("endRow", paramMap.get("endRow") );
        List<ProgramApprovalSnapshot> resultList = programApprovalSnapshotMapper.latelychangeList(map);
        for (ProgramApprovalSnapshot model:resultList) {
            /* 产品经理 */
            Map productManager = new HashMap();
            productManager.put("programId",model.getProgramId());
            productManager.put("employeeType", AvaStatusEnum.MEMBERAVA.getCode());
            productManager.put("employeeTypeId", new Long(AvaStatusEnum.PRODAVA.getCode()));
            List<ProgramEmployee> programManagerList = programEmployeeService.selectTypeList(productManager);
            model.setProductManagerList(programManagerList);
        }
        return resultList;
    }

    /***
     * 我關注的項目
     */
    @Override
    public List<Program> myFollowProgram(Map<String,Object> paramMap){
        List<Product> productList = getListByLikeAnalyzingConditions(paramMap,0);
        if (productList==null || productList.isEmpty()) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (Product model:productList) {
            sb.append(model.getId());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        Map<String,Object> map = new HashMap<>();
        map.put("productIdList",sb.toString().split(","));
        map.put("startRow", paramMap.get("startRow") );
        map.put("endRow", paramMap.get("endRow") );
        map.put("pfAcc", paramMap.get("pfAcc") );
        return programMapper.myFollowProgram(map);
    }

    /***
     * 需求变更--近三个月变更次数TOP5
     */
    @Override
    public List<Map<String,Object>> changeTopFive(List<Product> productList,Map<String,Object> paramMap){
        if (productList==null || productList.isEmpty()) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (Product model:productList) {
            sb.append(model.getId());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        Map<String,Object> map = new HashMap<>();
        map.put("productIdList",sb.toString().split(","));
        map.put("startRow", paramMap.get("startRow") );
        map.put("endRow", paramMap.get("endRow") );
        map.put("pfAcc", paramMap.get("pfAcc") );
        return programMapper.changeTopFive(map);
    }

    /***
     * 本年度费用使用情况
     */
    @Override
    public List<Map<String,Object>> yearCost(){
        return programMapper.yearCost();
    }

}