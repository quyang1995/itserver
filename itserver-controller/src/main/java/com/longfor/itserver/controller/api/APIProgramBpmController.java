package com.longfor.itserver.controller.api;

import com.alibaba.fastjson.JSON;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.ProgramStatusNewEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.vo.MoApprove.MoApproveListVo;
import com.longfor.itserver.common.vo.programBpm.ApproveListVo;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramApprovalSnapshot;
import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.entity.ProgramFile;
import com.longfor.itserver.esi.MoApproveUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目 -- 状态变更 bpm审批流程
 *
 **/
@RequestMapping(value = "/api/program/bpm")
@Controller
public class APIProgramBpmController extends BaseController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 提交立项申请
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map programList(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------apply:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.PRODAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().submit(paramsMap,program,ProgramStatusNewEnum.LX.getCode());
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 审核通过
	 */
	@RequestMapping(value = "/approvalPass", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map approvalPass(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------approvalPass:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			getProgramService().approvalPass(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 审核驳回
	 */
	@RequestMapping(value = "/approvalRebut", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map approvalRebut(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------approvalRebut:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			getProgramService().approvalRebut(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	private Program getProgram(Map<String, String> paramsMap){
		Long programId = Long.parseLong(paramsMap.get("programId"));
		Program program = getProgramService().getProgramId(programId);
		return program;
	}

	/**
	 * Demo评审
	 */
	@RequestMapping(value = "/demoReview", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map demoReview(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------demoReview:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.PRODAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().submit(paramsMap,program,ProgramStatusNewEnum.DPS.getCode());
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 招标文件
	 */
	@RequestMapping(value = "/tenderFile", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map tenderFile(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------tenderFile:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.PRODAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().submit(paramsMap,program,ProgramStatusNewEnum.ZTBSQ.getCode());
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 中标通知
	 */
	@RequestMapping(value = "/bidNotice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map bidNotice(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------bidNotice:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.PRODAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().submit(paramsMap,program,ProgramStatusNewEnum.ZBSQ.getCode());
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 产品评审
	 */
	@RequestMapping(value = "/productReview", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map productReview(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------productReview:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.PRODAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().submit(paramsMap,program,ProgramStatusNewEnum.CPPS.getCode());
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 进入开发
	 */
	@RequestMapping(value = "/development", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map development(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------development:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.DEVEAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().submit(paramsMap,program,ProgramStatusNewEnum.KFPS.getCode());
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 测试部署
	 */
	@RequestMapping(value = "/deploy", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map deploy(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------deploy:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.TESTINGAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().submit(paramsMap,program,ProgramStatusNewEnum.CSPS.getCode());
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 上线计划
	 */
	@RequestMapping(value = "/planOnline", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map planOnline(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------planOnline:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.PRODAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().submit(paramsMap,program,ProgramStatusNewEnum.SXPS.getCode());
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 灰度发布
	 */
	@RequestMapping(value = "/release", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map release(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------release:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.PRODAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().submit(paramsMap,program,ProgramStatusNewEnum.HDFB.getCode());
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 项目复盘
	 */
	@RequestMapping(value = "/projectReview", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map projectReview(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------projectReview:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.PRODAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().submit(paramsMap,program,ProgramStatusNewEnum.XMFP.getCode());
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}


	/**
	 *延期上线
	 */
	@RequestMapping(value = "/delay", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map delay(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------delay:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.PRODAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().delay(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 需求变更
	 */
	@RequestMapping(value = "/demandChange", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map demandChange(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------demandChange:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Program program = this.getProgram(paramsMap);
			if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.PRODAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
			getProgramService().demandChange(paramsMap,program);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

    /**
     * 终止项目
     */
    @RequestMapping(value = "/stopProgram", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Map stopProgram(HttpServletRequest request) throws IOException {
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
            LOG.info("------stopProgram:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

            Program program = this.getProgram(paramsMap);
            if(null==program)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			if (!checkAuth(paramsMap.get("programId"),paramsMap.get("modifiedAccountId"),AvaStatusEnum.PRODAVA.getCode())) {
				return CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
			}
            getProgramService().stopProgram(paramsMap,program);
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }

	/**
	 * 查看
	 */
	@RequestMapping(value = "/lookNodes", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map lookNodes(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------lookNodes:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			Map<String,Object> nodesMap = new HashMap<String,Object>();
			nodesMap.put("programId",new Long(paramsMap.get("id")));
			nodesMap.put("programStatus",paramsMap.get("programStatus"));
			List<Object> str = new ArrayList<Object>();
			str.add(paramsMap.get("programStatus"));
//			str.add(ProgramStatusNewEnum.YQSX.getCode());
//			str.add(ProgramStatusNewEnum.XQBG.getCode());
			nodesMap.put("programStatusList",str);
			List<ProgramApprovalSnapshot>  shotList= this.getProgramService().lookNodes(nodesMap);
			resultMap.put("list",shotList);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	/**
	 * 根据bpm流程id查询项目信息
	 */
	@RequestMapping(value = "/getProgramByBpmCode", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map getProgramByBpmCode(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try{
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------getProgramByBpmCode:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
			Map map = new HashMap();
			map.put("bpmCode",paramsMap.get("bpmCode"));
			ProgramApprovalSnapshot shot = getProgramService().getProgramByBpmCode(map);
			if(null==shot)return CommonUtils.getResultMapByBizEnum(BizEnum.E1301);
			resultMap.put("data",shot);
		}catch (Exception e){
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return resultMap;
	}

	private boolean checkAuth(String id,String accountId ,int avaStatusEnum){
		Map map = new HashMap();
		map.put("programId",id);
		map.put("accountId",accountId);
		map.put("employeeType", AvaStatusEnum.MEMBERAVA.getCode());
		map.put("employeeTypeId", new Long(avaStatusEnum));
		List<ProgramEmployee> empList  = getProgramEmployeeService().selectTypeList(map);
		if (empList != null && !empList.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 根据需求ID 获取相关需求文件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getFile" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map getFile(HttpServletRequest request){
		Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try {
			//获取文件
			ProgramFile result = this.getProgramFileService().selectById(Long.valueOf((String)paramsMap.get("id")));
			resultMap.put("data",result);
		} catch ( Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return  resultMap;
	}

	/**
	 * 根据需求ID 获取快照信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getSnapshot" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map getSnapshot(HttpServletRequest request){
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try {
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------getSnapshot:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
			Long id = Long.parseLong(paramsMap.get("id"));
			resultMap.put("data",getProgramService().getSnapshot(id));
		} catch ( Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return  resultMap;
	}

	/**
	 * 查看审批列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getApprovelapprovList" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public Map getApprovelapprovList(HttpServletRequest request){
		Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		try {
			Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
			LOG.info("------getApprovelapprovList:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");

			//调用移动审批接口获取列表
			MoApproveListVo moApproveListVo = MoApproveUtil.flowapiList(Integer.parseInt(paramsMap.get("approveStatus")),
										paramsMap.get("oaAccount"),
										paramsMap.get("searchType"),
										Integer.parseInt(paramsMap.get("page")),
										Integer.parseInt(paramsMap.get("pageSize")));

			if(null==moApproveListVo)return CommonUtils.getResultMapByBizEnum(BizEnum.E1303);

			ApproveListVo result = getProgramService().getApprovelapprovList(moApproveListVo);

			resultMap.put("data",result);
		} catch ( Exception e) {
			e.printStackTrace();
			resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
		}
		return  resultMap;
	}
}