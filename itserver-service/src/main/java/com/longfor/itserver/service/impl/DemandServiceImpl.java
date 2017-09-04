package com.longfor.itserver.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.ads.helper.ADSHelper;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.DemandStatusEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.PsIndex;
import com.longfor.itserver.mapper.DemandChangeLogMapper;
import com.longfor.itserver.mapper.DemandFileMapper;
import com.longfor.itserver.mapper.DemandMapper;
import com.longfor.itserver.service.IDemandService;
import com.longfor.itserver.service.base.AdminBaseService;

import net.mayee.commons.TimeUtils;
import net.mayee.commons.helper.APIHelper;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		List<DemandFile> fileList = JSONArray.parseArray(json.getString("fileList"), DemandFile.class);
		if(fileList!= null && fileList.size()>0) {
			for (DemandFile demandFile : fileList) {
				demandFile.setDemandId(demand.getId());
				demandFile.setCreateTime(TimeUtils.getTodayByDateTime());
			}
			demandFileMapper.insertList(fileList);
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
		demand.setStatus(DemandStatusEnum.PENDING.getCode());
		// 获取发起人信息
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

		/*新增需求更新日志*/
		demand.setModifiedTime(TimeUtils.getTodayByDateTime());
		Map<String,Object> logMap = getChangeLog(selectDemandOne,demand);
		List<String> textList = (List)logMap.get("logList");
		List<DemandChangeLog> logList = new ArrayList<>();
		if(logList.size() > 0){
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
			demandChangeLogMapper.insertList(logList);
		}


		/*更新文件*/
		DemandFile demandFile = new DemandFile();
		demandFile.setDemandId(demand.getId());
		demandFileMapper.delete(demandFile);

		List<DemandFile> list = JSONArray.parseArray((String)map.get("fileList"),DemandFile.class);
		if(list != null && list.size()>0) {
			for (DemandFile file:list) {
				file.setDemandId(demand.getId());
				file.setCreateTime(TimeUtils.getTodayByDateTime());
			}
			demandFileMapper.insertList(list);
		}
		/*添加文件结束*/

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
		/* 查询数据 */
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
					append("新增了需求信息");
			textList.add(log.toString());
			map.put("type",2);
			map.put("logList",textList);
			return map;
		}

		if(oldDemand !=null && newDemand != null){
			if(!Objects.equals(oldDemand.getDescp(),newDemand.getDescp())){
				StringBuilder log = new StringBuilder();
				log.append(newDemand.getModifiedName()).
						append("修改了需求描述信息");
				textList.add(log.toString());
				map.put("type",1);
			}
			if(!Objects.equals(oldDemand.getStatus(),newDemand.getStatus())||
					!Objects.equals(oldDemand.getLevel(),newDemand.getLevel())){
				StringBuilder log = new StringBuilder();
				if (!Objects.equals(oldDemand.getStatus(),newDemand.getStatus())){

					log.append(newDemand.getModifiedName()).
							append("将 状态 由[").
							append(oldDemand.getStatus()).
							append("]更改为[").
							append(newDemand.getStatus()).
							append("]");
					textList.add(log.toString());
				}
				if(!Objects.equals(oldDemand.getLevel(),newDemand.getLevel())){
					if (StringUtils.isNotBlank(log)){
						log.append(",");
					}else{
						log.append(newDemand.getModifiedName());
					}
					log.append("将 优先级 由[").
							append(oldDemand.getLevel()).
							append("]更改为[").
							append(newDemand.getLevel()).
							append("]");
					textList.add(log.toString());
				}
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
						append("修改了需求基础信息");
				textList.add(log.toString());
				map.put("type",2);
			}
			map.put("logList",textList);
		}
		return map;
	}
}
