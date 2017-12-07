package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.ProgramDevTypeEnum;
import com.longfor.itserver.common.enums.ProgramStatusEnum;
import com.longfor.itserver.common.enums.PublicTypeEnum;
import com.longfor.itserver.common.util.DateUtil;
import com.longfor.itserver.common.util.StringUtil;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.mapper.*;
import com.longfor.itserver.service.IProgramService;
import com.longfor.itserver.service.base.AdminBaseService;
import com.longfor.itserver.service.util.AccountUitl;
import net.mayee.commons.TimeUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wax Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("ProgramService")
public class ProgramServiceImpl extends AdminBaseService<Program> implements IProgramService {

	@Autowired
	private ProgramMapper programMapper;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProgramEmployeeMapper programEmployeeMapper;

	@Autowired
	private ADSHelper adsHelper;

	@Autowired
	private ProgramEmployeeChangeLogMapper programEmployeeChangeLogMapper;

	@Autowired
	private ProgramApprovalSnapshotMapper programApprovalSnapshotMapper;

	@Override
	public List<Program> programList(Map map) {
//		List<Program> programList = Lists.newArrayList();
//		for (Program program : programMapper.programList(map)) {
//			Product product = productMapper.selectByPrimaryKey(program.getProductId());
//			if (null != product) {
//				program.setProductName(product.getName());
//			}
//			programList.add(program);
//		}
		return programMapper.programList(map);
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
	@Transactional
	public boolean addProgram(Map map) {
		JSONObject json = (JSONObject) JSONObject.toJSON(map);
		Integer accountType = AccountUitl.getAccountType(map);
		Program program = JSONObject.toJavaObject(json, Program.class);
		Product product = productMapper.selectByPrimaryKey(program.getProductId());
		if(product == null){
			return false;
		}
		program.setProductName(product.getName());
		program.setProductCode(product.getCode());
		program.setCreateTime(TimeUtils.getTodayByDateTime());
		program.setModifiedTime(TimeUtils.getTodayByDateTime());
		program.setAccountType(accountType);
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
		selectOneProgram.setCommitDate(program.getCommitDate());
		selectOneProgram.setStartDate(program.getStartDate());
		selectOneProgram.setUedDate(program.getUedDate());
		selectOneProgram.setArchitectureDate(program.getArchitectureDate());
		selectOneProgram.setGrayReleaseDate(program.getGrayReleaseDate());
		selectOneProgram.setReleaseDate(program.getReleaseDate());
		selectOneProgram.setLikeProduct(program.getLikeProduct());
		selectOneProgram.setLikeProgram(program.getLikeProgram());
		selectOneProgram.setType(program.getType());
		selectOneProgram.setProgramStatus(program.getProgramStatus());
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
			if(Objects.nonNull(ProgramStatusEnum.getByCode(newProgram.getProgramStatus())) && !Objects.equals(oldProgram.getProgramStatus(), newProgram.getProgramStatus())){
				sb.append(newProgram.getModifiedName())
						.append(" 将 项目状态 从 [")
						.append(ProgramStatusEnum.getByCode(oldProgram.getProgramStatus()).getText())
						.append("] 更新为 [")
						.append(ProgramStatusEnum.getByCode(newProgram.getProgramStatus()).getText())
						.append("] ");
			}
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

			textList.add(sb.toString());
		}

		//立项时间
		if(!TimeUtils.sameDate(oldProgram.getCommitDate(), newProgram.getCommitDate())){
			StringBuilder sb = new StringBuilder();
			sb.append(newProgram.getModifiedName());
			sb.append(" 将 立项时间 从 [")
					.append(TimeUtils.getTime(oldProgram.getCommitDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
					.append("] 更新为 [")
					.append(TimeUtils.getTime(newProgram.getCommitDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
					.append("]");
			textList.add(sb.toString());
		}
		//启动时间
		if(!TimeUtils.sameDate(oldProgram.getStartDate(), newProgram.getStartDate())){
			StringBuilder sb = new StringBuilder();
			sb.append(newProgram.getModifiedName());
			sb.append(" 将 启动时间 从 [")
					.append(TimeUtils.getTime(oldProgram.getStartDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
					.append("] 更新为 [")
					.append(TimeUtils.getTime(newProgram.getStartDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
					.append("]");
			textList.add(sb.toString());
		}
		//UED评审时间
		if(!"".equals(oldProgram.getUedDate()) && oldProgram.getUedDate() != null){
			if(!TimeUtils.sameDate(oldProgram.getUedDate(), newProgram.getUedDate())){
				StringBuilder sb = new StringBuilder();
				sb.append(newProgram.getModifiedName());
				sb.append(" 将 UED评审时间 从 [")
						.append(TimeUtils.getTime(oldProgram.getUedDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
						.append("] 更新为 [")
						.append(TimeUtils.getTime(newProgram.getUedDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
						.append("]");
				textList.add(sb.toString());
			}
		}

		//架构评审时间
		if(!"".equals(oldProgram.getArchitectureDate()) && oldProgram.getArchitectureDate() != null) {
			if (!TimeUtils.sameDate(oldProgram.getArchitectureDate(), newProgram.getArchitectureDate())) {
				StringBuilder sb = new StringBuilder();
				sb.append(newProgram.getModifiedName());
				sb.append(" 将 架构评审时间 从 [")
						.append(TimeUtils.getTime(oldProgram.getArchitectureDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
						.append("] 更新为 [")
						.append(TimeUtils.getTime(newProgram.getArchitectureDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
						.append("]");
				textList.add(sb.toString());
			}
		}
		//灰度时间
		if(!TimeUtils.sameDate(oldProgram.getGrayReleaseDate(), newProgram.getGrayReleaseDate())){
			StringBuilder sb = new StringBuilder();
			sb.append(newProgram.getModifiedName());
			sb.append(" 将 灰度时间 从 [")
					.append(TimeUtils.getTime(oldProgram.getGrayReleaseDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
					.append("] 更新为 [")
					.append(TimeUtils.getTime(newProgram.getGrayReleaseDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
					.append("]");
			textList.add(sb.toString());
		}
		//发布时间
		if(!TimeUtils.sameDate(oldProgram.getReleaseDate(), newProgram.getReleaseDate())){
			StringBuilder sb = new StringBuilder();
			sb.append(newProgram.getModifiedName());
			sb.append(" 将 发布时间 从 [")
					.append(TimeUtils.getTime(oldProgram.getReleaseDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
					.append("] 更新为 [")
					.append(TimeUtils.getTime(newProgram.getReleaseDate().getTime(), TimeUtils.JDATE_FORMAT_DEFAULT))
					.append("]");
			textList.add(sb.toString());
		}

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
	 * 提交立项申请
	 */
	@Override
	@Transactional(value="transactionManager")
	public void apply(Map<String, String> paramsMap,Program program) {
		try{
			//调用bpm接口:创建流程接口获取流程id
			String bpmCode = "";

			//program表
			program.setDevType(Integer.parseInt(paramsMap.get("devType")));//研发方式
			program.setAnalyzingConditions(Integer.parseInt(paramsMap.get("analyzingConditions")));//判断条件
			program.setDevWorkload(Integer.parseInt(paramsMap.get("devWorkload")));//研发工作量预估
			program.setOverallCost(new BigDecimal(paramsMap.get("devWorkload")));//整体费用预估
			program.setCommitDate(DateUtil.string2Date(paramsMap.get("commitDate"),DateUtil.PATTERN_TIMESTAMP));
			program.setDemoApprovalDate(DateUtil.string2Date(paramsMap.get("demoApprovalDate"),DateUtil.PATTERN_TIMESTAMP));
			program.setGrayReleaseDate(DateUtil.string2Date(paramsMap.get("grayReleaseDate"),DateUtil.PATTERN_TIMESTAMP));
			if(program.getDevType() == ProgramDevTypeEnum.ZTB.getCode()){//招投标需要设置招标和中标时间
				program.setBiddingDate(DateUtil.string2Date(paramsMap.get("biddingDate"),DateUtil.PATTERN_TIMESTAMP));//招标时间
				program.setWinningBidDate(DateUtil.string2Date(paramsMap.get("winningBidDate"),DateUtil.PATTERN_TIMESTAMP));//中标时间
			}
			programMapper.updateByPrimaryKey(program);

			//program快照表
			ProgramApprovalSnapshot programApprovalSnapshot = new ProgramApprovalSnapshot();
			BeanUtils.copyProperties(program,programApprovalSnapshot);
			programApprovalSnapshot.setBpmCode(bpmCode);
			programApprovalSnapshot.setRemark(paramsMap.get("remark"));
			programApprovalSnapshotMapper.insert(programApprovalSnapshot);

			//附件表




		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("发生异常");
		}
	}
}
