package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.eds.helper.EDSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.ProgramStatusNewEnum;
import com.longfor.itserver.common.util.StringUtil;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.PsProgramDraftDetail;
import com.longfor.itserver.mapper.ProductMapper;
import com.longfor.itserver.mapper.ProgramDraftMapper;
import com.longfor.itserver.mapper.ProgramEmployeeDraftMapper;
import com.longfor.itserver.service.IProductService;
import com.longfor.itserver.service.IProgramDraftService;
import com.longfor.itserver.service.IProgramEmployeeDraftService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
import net.mayee.commons.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ProgramDraftService")
public class ProgramDraftServiceImpl extends AdminBaseService<ProgramDraft> implements IProgramDraftService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProgramDraftMapper programDraftMapper;
    @Autowired
    private IProductService productService;
    @Autowired
    private ADSHelper adsHelper;
    @Autowired
    private EDSHelper edsHelper;
    @Autowired
    private ProgramEmployeeDraftMapper programEmployeeDraftMapper;
    @Autowired
    private IProgramEmployeeDraftService programEmployeeDraftService;

    @Override
    @Transactional
    public boolean addProgramDraft(Map map) throws Exception{
        //删除旧数据
        map.put("operationType",100);
        this.deleteDraft(map);
        //插入新数据
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        Integer accountType = AccountUitl.getAccountType(map);
        ProgramDraft programDraft = JSONObject.toJavaObject(json, ProgramDraft.class);
        Product product = productMapper.selectByPrimaryKey(programDraft.getProductId());
        if(product != null){
            programDraft.setProductName(product.getName());
            programDraft.setProductCode(product.getCode());
        }
        programDraft.setProgramStatus(ProgramStatusNewEnum.WLX.getCode());
        programDraft.setCreateTime(TimeUtils.getTodayByDateTime());
        programDraft.setModifiedTime(TimeUtils.getTodayByDateTime());
        programDraft.setAccountType(accountType);
        programDraft.setApprovalStatus(0);
        //操作类型：100=添加项目
        programDraft.setOperationType(100);
        programDraftMapper.insert(programDraft);
        //处理人员
        this.dealPerson(json,programDraft);
        return true;
    }

    /**
     * 处理人员
     * @param json
     * @param programDraft
     */
    private void dealPerson(JSONObject json,ProgramDraft programDraft){
        // 项目责任人
        if(json.get("personLiableList")!=null){
            String jsonArrPl = json.get("personLiableList").toString();
            if (!"".equals(jsonArrPl)) {
                getAccountLongfor(programDraft, jsonArrPl, "0");
            }
        }

        // 产品经理
        if(json.get("productManagerList")!=null) {
            String jsonArrPm = json.get("productManagerList").toString();
            if (!"".equals(jsonArrPm)) {
                getAccountLongfor(programDraft, jsonArrPm, "1");

            }
        }
        // 项目经理
        if(json.get("programManagerList")!=null) {
            String jsonArrPMl = json.get("programManagerList").toString();
            if (!"".equals(jsonArrPMl)) {
                getAccountLongfor(programDraft, jsonArrPMl, "2");
            }
        }
        // 开发人员
        if(json.get("developerList")!=null) {
            String jsonArrDe = json.get("developerList").toString();
            if (!"".equals(jsonArrDe)) {
                getAccountLongfor(programDraft, jsonArrDe, "3");
            }
        }
        // UED人员
        if(json.get("uedList")!=null) {
            String jsonArrUed = json.get("uedList").toString();
            if (!"".equals(jsonArrUed)) {
                getAccountLongfor(programDraft, jsonArrUed, "4");
            }
        }
        // 测试人员
        if(json.get("testingList")!=null) {
            String jsonArrTest = json.get("testingList").toString();
            if (!"".equals(jsonArrTest)) {
                getAccountLongfor(programDraft, jsonArrTest, "5");
            }
        }
        // 业务人员
        if(json.get("businessList")!=null) {
            String jsonArrBusiness = json.get("businessList").toString();
            if (!"".equals(jsonArrBusiness)) {
                getAccountLongfor(programDraft, jsonArrBusiness, "6");
            }
        }

        // 运维人员
        if(json.get("operationList")!=null) {
            String jsonArrOperation = json.get("operationList").toString();
            if (!"".equals(jsonArrOperation)) {
                getAccountLongfor(programDraft, jsonArrOperation, "7");
            }
        }

        // 运营人员
        if(json.get("operateList")!=null) {
            String jsonArrOperate = json.get("operateList").toString();
            if (!"".equals(jsonArrOperate)) {
                getAccountLongfor(programDraft, jsonArrOperate, "8");
            }
        }
    }

    public boolean getAccountLongfor(ProgramDraft programDraft, String str, String id) {
        String[] strArr = str.split(",");
        for (int i = 0; i < strArr.length; i++) {
            String loginName = strArr[i].toString();
            AccountLongfor accountLongfor =
                    AccountUitl.getAccountByAccountTypes(loginName,adsHelper,edsHelper);
            if (accountLongfor != null) {
                ProgramEmployeeDraft pe = new ProgramEmployeeDraft();
                pe.setProgramId(programDraft.getId());
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
                programEmployeeDraftMapper.insert(pe);
            }
        }
        return true;
    }

    @Override
    @Transactional
    public boolean applyNode(Map map) throws Exception{
        //删除旧数据
        this.deleteDraft(map);
        //插入新数据
        JSONObject json = (JSONObject) JSONObject.toJSON(map);
        Integer accountType = AccountUitl.getAccountType(map);
        ProgramDraft programDraft = JSONObject.toJavaObject(json, ProgramDraft.class);
        programDraft.setAccountType(accountType);
        if(programDraft.getProductId()!=null){
            String productId = programDraft.getProductId().toString();
            if(StringUtils.isNotBlank(productId)){
                Product product = productMapper.selectByPrimaryKey(Long.valueOf(productId));
                if (product!=null) {
                    programDraft.setProductId(product.getId());
                    programDraft.setProductName(product.getName());
                    programDraft.setProductCode(product.getCode());
                }
            }
        }
        programDraftMapper.insert(programDraft);
        //处理人员
        this.dealPerson(json,programDraft);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteProgramDraft(Map map) {
        ProgramDraft programDraft = programDraftMapper.selectByPrimaryKey(Long.valueOf(map.get("id").toString()));
        if(programDraft!=null){
            ProgramEmployeeDraft programEmployeeDraft = new ProgramEmployeeDraft();
            programEmployeeDraft.setProgramId(programDraft.getId());
            programDraftMapper.deleteByPrimaryKey(programDraft.getId());//删除项目基本信息
            programEmployeeDraftMapper.delete(programEmployeeDraft);//删除项目人员信息
        }
        return true;
    }

    @Override
    public PsProgramDraftDetail getProgramDraftDetail(Map map) {
        PsProgramDraftDetail programDraft = programDraftMapper.getProgramDraftDetail(map);
        //人员信息
        if(programDraft!=null){
            setPsProgramDraftDetailInfo(programDraft);
            //关联产品
            if(StringUtils.isNotBlank(programDraft.getLikeProduct())){
                String likeProduct = programDraft.getLikeProduct().substring(1, programDraft.getLikeProduct().length());
                if(StringUtils.isNotBlank(likeProduct)) {
                    List<Product> product = productService.searchIdList(likeProduct);
                    programDraft.setProductList(product);
                }
            }
        }
        return programDraft;
    }

    /*设置快照文件信息和项目经理信息*/
    private void setPsProgramDraftDetailInfo (PsProgramDraftDetail model){
        if (model != null ) {
            Map map = new HashMap();
            map.put("programId",model.getId());
            map.put("type",model.getProgramStatus());
             /* 责任人 */
            map.put("employeeType", AvaStatusEnum.LIABLEAVA.getCode());
            List<ProgramEmployeeDraft> personLiableList = programEmployeeDraftService.selectTypeList(map);
            model.setPersonLiableList(personLiableList);
			/* 产品经理 */
            map.put("employeeType", AvaStatusEnum.MEMBERAVA.getCode());
            map.put("employeeTypeId", new Long(AvaStatusEnum.PRODAVA.getCode()));
            List<ProgramEmployeeDraft> programManagerList = programEmployeeDraftService.selectTypeList(map);
            model.setProductManagerList(programManagerList);
			/* 项目经理 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.PROGAVA.getCode()));
            List<ProgramEmployeeDraft> productManagerList = programEmployeeDraftService.selectTypeList(map);
            model.setProgramManagerList(productManagerList);
			/* 开发人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.DEVEAVA.getCode()));
            List<ProgramEmployeeDraft> developerList = programEmployeeDraftService.selectTypeList(map);
            model.setDeveloperList(developerList);
			/* UED人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.UEDAVA.getCode()));
            List<ProgramEmployeeDraft> uedList = programEmployeeDraftService.selectTypeList(map);
            model.setUedList(uedList);
			/* 测试人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.TESTINGAVA.getCode()));
            List<ProgramEmployeeDraft> testingList = programEmployeeDraftService.selectTypeList(map);
            model.setTestingList(testingList);
			/* 业务人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.BUSINESSAVA.getCode()));
            List<ProgramEmployeeDraft> businessList = programEmployeeDraftService.selectTypeList(map);
            model.setBusinessList(businessList);
			/* 运维人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.OPERATION.getCode()));
            List<ProgramEmployeeDraft> operationList = programEmployeeDraftService.selectTypeList(map);
            model.setOperationList(operationList);
            /* 运营人员 */
            map.put("employeeTypeId", new Long(AvaStatusEnum.OPERATE.getCode()));
            List<ProgramEmployeeDraft> operateList = programEmployeeDraftService.selectTypeList(map);
            model.setOperateList(operateList);
        }
    }

    /**
     * 根据操作类型跟人员删除草稿
     * @param map
     */
    private void deleteDraft (Map map){
        ProgramDraft oldProgramDraft = new ProgramDraft();
        oldProgramDraft.setModifiedAccountId(map.get("modifiedAccountId").toString());
        oldProgramDraft.setOperationType(Integer.valueOf(map.get("operationType").toString()));
        if(map.get("programId")!=null){
            oldProgramDraft.setProgramId(Long.valueOf(map.get("programId").toString()));
        }
        List<ProgramDraft>  list = programDraftMapper.select(oldProgramDraft);
        //先删除人员，后删除草稿
        for (ProgramDraft draft:list) {
            ProgramEmployeeDraft empty = new ProgramEmployeeDraft();
            empty.setProgramId(draft.getId());
            programEmployeeDraftMapper.delete(empty);

        }
        programDraftMapper.delete(oldProgramDraft);
    }

}

