package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.DemandLevelEnum;
import com.longfor.itserver.common.enums.DemandStatusEnum;
import com.longfor.itserver.common.helper.DataPermissionHelper;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.PsIndex;
import com.longfor.itserver.mapper.DemandChangeLogMapper;
import com.longfor.itserver.mapper.DemandFileMapper;
import com.longfor.itserver.mapper.DemandMapper;
import com.longfor.itserver.mapper.FeedBackMapper;
import com.longfor.itserver.service.IDemandService;
import com.longfor.itserver.service.base.AdminBaseService;

import net.mayee.commons.TimeUtils;
import net.mayee.commons.helper.APIHelper;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wax Created on 2017/8/3 下午7:15
 * @version v1.0
 */
@Service("DemandService")
public class DemandServiceImpl extends AdminBaseService<Demand> implements IDemandService {
	@Autowired
	private DemandMapper demandMapper;
	@Autowired
	private ADSHelper adsHelper;
	@Autowired
	private DemandChangeLogMapper demandChangeLogMapper;
	@Autowired
	private DemandFileMapper demandFileMapper;
	@Autowired
	private FeedBackMapper feedBackMapper;

	/**
	 * 	新增需求信息
	 * @param map
	 * @return
	 */
	@Transactional
	@Override
	public boolean addDemand(Map map) {
		JSONObject json = (JSONObject) JSONObject.toJSON(map);
		Demand demand = JSONObject.toJavaObject(json, Demand.class);
		//获取状态信息(默认待处理)
		demand.setStatus(DemandStatusEnum.PENDING.getCode());
		//获取发起人信息
		AccountLongfor draftedAccountLongfor = adsHelper.getAccountLongforByLoginName(demand.getModifiedAccountId());
		if(draftedAccountLongfor!=null){
			demand.setDraftedAccountId(demand.getModifiedAccountId());
			demand.setDraftedEmployeeCode(Long.parseLong(draftedAccountLongfor.getPsEmployeeCode()));
			demand.setDraftedEmployeeName(draftedAccountLongfor.getName());
			demand.setDraftedFullDeptPath(draftedAccountLongfor.getPsDeptFullName());
		}
		//获取指派人信息
		AccountLongfor callonAccountLongfor = adsHelper.getAccountLongforByLoginName(demand.getCallonAccountId());
		if (callonAccountLongfor!=null){
			demand.setCallonEmployeeName(callonAccountLongfor.getName());
			demand.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
			demand.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
		}

		demand.setCreateTime(TimeUtils.getTodayByDateTime());
		demand.setModifiedTime(TimeUtils.getTodayByDateTime());

		demandMapper.insert(demand);

		//添加文件
		String filelist = json.getString("fileList");
		if(StringUtils.isNotBlank(filelist)) {
			List<DemandFile> fileList = JSONArray.parseArray(filelist, DemandFile.class);
			if (fileList != null && fileList.size() > 0) {
				for (DemandFile demandFile : fileList) {
					demandFile.setDemandId(demand.getId());
					demandFile.setCreateTime(TimeUtils.getTodayByDateTime());
				}
				demandFileMapper.insertList(fileList);
			}
		}

		/*新增需求更新日志*/
		demand.setModifiedTime(TimeUtils.getTodayByDateTime());
		Map<String,Object> logMap = getChangeLog(null,demand);
		List<String> textList = (List)logMap.get("logList");
		for (String demandChangeLog:textList){
			DemandChangeLog log = new DemandChangeLog();
			log.setDemandId(demand.getId());
			log.setBefDescp(demand.getDescp());
			log.setType((Integer)logMap.get("type"));
			log.setActionChangeInfo(demandChangeLog);
			log.setModifiedName(draftedAccountLongfor.getName());
			log.setModifiedAccountId(demand.getModifiedAccountId());
			log.setCreateTime(TimeUtils.getTodayByDateTime());
			log.setModifiedTime(TimeUtils.getTodayByDateTime());
			demandChangeLogMapper.insert(log);
		}


		return true;
	}

	/**
	 *  修改需求
	 * @param map
	 * @return
	 */
	@Transactional
	@Override
	public Boolean updateDemand(Map map) {
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(map);
		Demand demand = JSONObject.toJavaObject(jsonObject, Demand.class);
		Demand selectDemandOne = demandMapper.selectByPrimaryKey(demand.getId());
		if (selectDemandOne==null){
			return false;
		}
		//获取状态信息(默认处理中)
		demand.setStatus(selectDemandOne.getStatus());
		// 获取发起人信息
		AccountLongfor draftedAccountLongfor = adsHelper.getAccountLongforByLoginName(demand.getModifiedAccountId());
//		if(draftedAccountLongfor!=null){
//			demand.setDraftedAccountId(demand.getModifiedAccountId());
//			demand.setDraftedEmployeeCode(Long.parseLong(draftedAccountLongfor.getPsEmployeeCode()));
//			demand.setDraftedEmployeeName(draftedAccountLongfor.getName());
//			demand.setDraftedFullDeptPath(draftedAccountLongfor.getPsDeptFullName());
//		}
		//获取指派人信息
		AccountLongfor callonAccountLongfor = adsHelper.getAccountLongforByLoginName(demand.getCallonAccountId());
		if (callonAccountLongfor!=null){
			demand.setCallonEmployeeName(callonAccountLongfor.getName());
			demand.setCallonEmployeeCode(Long.parseLong(callonAccountLongfor.getPsEmployeeCode()));
			demand.setCallonFullDeptPath(callonAccountLongfor.getPsDeptFullName());
		}

		/*新增需求更新日志*/
		demand.setModifiedTime(TimeUtils.getTodayByDateTime());
		Map<String,Object> logMap = getChangeLog(selectDemandOne,demand);
		List<String> textList = (List)logMap.get("logList");
		List<DemandChangeLog> logList = new ArrayList<>();
		for (String demandChangeLog:textList){
			DemandChangeLog log = new DemandChangeLog();
			log.setDemandId(demand.getId());
			log.setBefDescp(selectDemandOne.getDescp());
			log.setType((Integer)logMap.get("type"));
			log.setActionChangeInfo(demandChangeLog);
			log.setModifiedName(draftedAccountLongfor.getName());
			log.setModifiedAccountId(demand.getModifiedAccountId());
			log.setCreateTime(TimeUtils.getTodayByDateTime());
			log.setModifiedTime(TimeUtils.getTodayByDateTime());
			logList.add(log);
		}
		if(logList.size() > 0){
			demandChangeLogMapper.insertList(logList);
		}


		/*更新文件 不删除原有文件，在原有文件的基础上添加新文件*/
		String filelist = (String)map.get("fileList");
		if(StringUtils.isNotBlank(filelist)) {
			List<DemandFile> list = JSONArray.parseArray(filelist, DemandFile.class);
			if (list != null && list.size() > 0) {
				for (DemandFile file : list) {
					file.setDemandId(demand.getId());
					file.setCreateTime(TimeUtils.getTodayByDateTime());
				}
				demandFileMapper.insertList(list);
			}
		}

		/*添加文件结束*/
		if("".equals(demand.getDescp())){
			demand.setDescp(selectDemandOne.getDescp());
		}
		demand.setCreateTime(selectDemandOne.getCreateTime());
		demandMapper.updateByPrimaryKey(demand);
		return true;
	}


	@Override
	public Demand getDemandById(Long id) {
			Demand demand = demandMapper.getDemandById(id);
		return demand;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> getPageDemandList(Map<String, Object> paramsMap, ELExample elExample) {
		/* 查询数据 and admin权限判断 */
		String accountId = String.valueOf(paramsMap.get("accountId"));
		paramsMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");
		PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
		List<Demand> demands = demandMapper.searchList(paramsMap);
		/* 返回数据 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("demandList", demands);
		resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
		resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
		resultMap.put(APIHelper.TOTAL, new PageInfo(demands).getTotal());
		return resultMap;
	}


	@Override
	public List<PsIndex> countPending(String id) {
		List<PsIndex> pendingList = demandMapper.countPending(id);
		return pendingList;
	}


	public Map getChangeLog(Demand  oldDemand , Demand newDemand) {
		Map<String,Object> map = new HashMap<>();
		List<String> textList = new ArrayList<String>();
		if(oldDemand == null && newDemand != null){
			StringBuilder log = new StringBuilder();
			log.append(newDemand.getModifiedName()).
					append(" 新增了需求信息");
			textList.add(log.toString());
			map.put("type",2);
			map.put("logList",textList);
			return map;
		}

		if(oldDemand !=null && newDemand != null){
			if(!Objects.equals(oldDemand.getDescp(),newDemand.getDescp())){
				StringBuilder log = new StringBuilder();
				log.append(newDemand.getModifiedName()).
						append(" 修改了需求描述信息");
				textList.add(log.toString());
				map.put("type",1);
			}
			if(!Objects.equals(oldDemand.getLevel(),newDemand.getLevel())
//					||
//					!Objects.equals(oldDemand.getStatus(),newDemand.getStatus())||
//					!Objects.equals(oldDemand.getCallonAccountId(),newDemand.getCallonAccountId())
					){
				StringBuilder log = new StringBuilder();
//				if (!Objects.equals(oldDemand.getStatus(),newDemand.getStatus())){
//
//					log.append(newDemand.getModifiedName()).
//							append("将 状态 由[").
//							append(DemandStatusEnum.getByCode(oldDemand.getStatus()).getText()).
//							append("]更改为[").
//							append(DemandStatusEnum.getByCode(newDemand.getStatus()).getText()).
//							append("]");
//					textList.add(log.toString());
//				}
				if(!Objects.equals(oldDemand.getLevel(),newDemand.getLevel())){
					if (StringUtils.isNotBlank(log)){
						log.append(",");
					}else{
						log.append(newDemand.getModifiedName())
								.append(" ");
					}
					log.append("将 优先级 由[").
							append(DemandLevelEnum.getByCode(oldDemand.getLevel()).getText()).
							append("]更改为[").
							append(DemandLevelEnum.getByCode(newDemand.getLevel()).getText()).
							append("]");
					textList.add(log.toString());
				}
//				if(!Objects.equals(oldDemand.getCallonAccountId(),newDemand.getCallonAccountId())){
//					if (StringUtils.isNotBlank(log)){
//						log.append(",");
//					}else{
//						log.append(newDemand.getModifiedName());
//					}
//					log.append("将 指派人 由[").
//							append(oldDemand.getCallonEmployeeName()).
//							append("]更改为[").
//							append(newDemand.getCallonEmployeeName()).
//							append("]");
//					textList.add(log.toString());
//				}
				map.put("type",2);
			}

			if(!(Objects.equals(oldDemand.getLikeProduct(),newDemand.getLikeProduct())
					&&Objects.equals(oldDemand.getLikeProgram(),newDemand.getLikeProgram())
					&&Objects.equals(oldDemand.getCcAccount(),newDemand.getCcAccount())
					&&Objects.equals(oldDemand.getName(),newDemand.getName())
					&&Objects.equals(oldDemand.getRelationId(),newDemand.getRelationId())
					&&Objects.equals(oldDemand.getRelationType(),newDemand.getRelationType()))
					){
				StringBuilder log = new StringBuilder();
				log.append(newDemand.getModifiedName()).
						append(" 修改了需求基础信息");
				textList.add(log.toString());
				map.put("type",2);
			}
			map.put("logList",textList);
		}
		return map;
	}

	public String statusLog(Demand oldDemand,Demand newDemand){
		StringBuilder log = new StringBuilder();
		if (newDemand.getStatus()!=null && !Objects.equals(oldDemand.getStatus(),newDemand.getStatus())){

			log.append(oldDemand.getModifiedName()).
				append(" 将 状态 由[").
				append(DemandStatusEnum.getByCode(oldDemand.getStatus()).getText()).
				append("]更改为[").
				append(DemandStatusEnum.getByCode(newDemand.getStatus()).getText()).
				append("]");
		}

		if(newDemand.getCallonAccountId()!=null && !Objects.equals(oldDemand.getCallonAccountId(),newDemand.getCallonAccountId())){

				log.append(oldDemand.getModifiedName()).
					append(" 将 指派人 由[").
					append(oldDemand.getCallonEmployeeName()).
					append("]更改为[").
					append(newDemand.getCallonEmployeeName()).
					append("]");
		}
		return log.toString();
	}


	@Override
	@Transactional
	public boolean updateStatus(Map<String,String> paramsMap) {

		JSONObject jsonObject   = (JSONObject)JSONObject.toJSON(paramsMap);
		String modifiedAccountId =  jsonObject.getString("modifiedAccountId");
		String modifiedName =  jsonObject.getString("modifiedName");
		Long demandId = Long.valueOf(jsonObject.getString("demandId"));
		Integer status = Integer.valueOf( jsonObject.getString("status"));
		Demand oldDemand = demandMapper.selectByPrimaryKey(demandId);
		Demand newDemand = new Demand();
		newDemand.setStatus(status);

        /*添加需求修改日志*/
		String log = statusLog(oldDemand,newDemand);
		if(StringUtils.isNotBlank(log)) {
			DemandChangeLog demandChangeLog = new DemandChangeLog();
			demandChangeLog.setDemandId(oldDemand.getId());
			demandChangeLog.setBefDescp(oldDemand.getDescp());
			demandChangeLog.setType(2);
			demandChangeLog.setActionChangeInfo(log);
			demandChangeLog.setModifiedName(modifiedName);
			demandChangeLog.setModifiedAccountId(modifiedAccountId);
			demandChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
			demandChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
			demandChangeLogMapper.insertUseGeneratedKeys(demandChangeLog);
		}

		oldDemand.setStatus(status);
		oldDemand.setModifiedTime(TimeUtils.getTodayByDateTime());
		demandMapper.updateByPrimaryKey(oldDemand);

		//如果需求对应的反馈建议不为空，则更新反馈建议状态信息
		FeedBack feedBack = feedBackMapper.selectByPrimaryKey(oldDemand.getFeedBackId());
		if(feedBack != null){
			feedBack.setStatus(status);
			feedBack.setModifiedTime(TimeUtils.getTodayByDateTime());
			feedBackMapper.updateByPrimaryKey(feedBack);
		}
		return true;
	}


	@Override
	@Transactional
	public boolean updateCallon(Map<String, String> paramsMap) {
		JSONObject jsonObject   = (JSONObject)JSONObject.toJSON(paramsMap);
		String modifiedAccountId =  jsonObject.getString("modifiedAccountId");
		String modifiedName =  jsonObject.getString("modifiedName");
		Long demandId = Long.valueOf(jsonObject.getString("demandId"));
		String callonAccountId =  jsonObject.getString("callonAccountId");
		Demand oldDemand = demandMapper.selectByPrimaryKey(demandId);
		Demand newDemand = new Demand();
		AccountLongfor accountLongfor =  adsHelper.getAccountLongforByLoginName(callonAccountId);
		newDemand.setCallonAccountId(callonAccountId);
		newDemand.setCallonEmployeeCode(Long.valueOf(accountLongfor.getPsEmployeeCode()));
		newDemand.setCallonEmployeeName(accountLongfor.getName());
		newDemand.setCallonFullDeptPath(accountLongfor.getPsDeptFullName());

        /*添加B需求修改日志*/
		String log = statusLog(oldDemand,newDemand);
		if(StringUtils.isNotBlank(log)) {
			DemandChangeLog demandChangeLog = new DemandChangeLog();
			demandChangeLog.setDemandId(oldDemand.getId());
			demandChangeLog.setBefDescp(oldDemand.getDescp());
			demandChangeLog.setType(2);
			demandChangeLog.setActionChangeInfo(log);
			demandChangeLog.setModifiedName(modifiedName);
			demandChangeLog.setModifiedAccountId(modifiedAccountId);
			demandChangeLog.setCreateTime(TimeUtils.getTodayByDateTime());
			demandChangeLog.setModifiedTime(TimeUtils.getTodayByDateTime());
			demandChangeLogMapper.insertUseGeneratedKeys(demandChangeLog);
		}

		oldDemand.setCallonAccountId(callonAccountId);
		oldDemand.setCallonEmployeeCode(Long.valueOf(accountLongfor.getPsEmployeeCode()));
		oldDemand.setCallonEmployeeName(accountLongfor.getName());
		oldDemand.setCallonFullDeptPath(accountLongfor.getPsDeptFullName());
		oldDemand.setModifiedTime(TimeUtils.getTodayByDateTime());
		demandMapper.updateByPrimaryKey(oldDemand);

		FeedBack feedBack = feedBackMapper.selectByPrimaryKey(oldDemand.getFeedBackId());
		//如果需求对应的反馈建议不为空，更新反馈建议接口人信息
		if(feedBack != null) {
			feedBack.setContactAccountId(callonAccountId);
			feedBack.setContactEmployeeCode(Long.valueOf(accountLongfor.getPsEmployeeCode()));
			feedBack.setContactEmployeeName(accountLongfor.getName());
			feedBack.setContactFullDeptPath(accountLongfor.getPsDeptFullName());
			feedBack.setModifiedTime(TimeUtils.getTodayByDateTime());
			feedBackMapper.updateByPrimaryKey(feedBack);
		}
		return true;
	}

	@Override
	public Map statusList(HttpServletRequest request , Map<String, String> paramsMap) {

		/* 生成查询用Example */
		ELExample elExample = new ELExample(request, BugInfo.class);
		PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);

		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		List<Demand> list = demandMapper.statusList(paramsMap);
		resultMap.put("list",list);
		resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
		resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
		resultMap.put(APIHelper.TOTAL, new PageInfo(list).getTotal());
		return resultMap;
	}
}
