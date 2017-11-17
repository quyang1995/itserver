package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.helper.DataPermissionHelper;
import com.longfor.itserver.common.helper.JoddHelper;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.PsFeedBackDetail;
import jodd.props.Props;
import net.mayee.commons.helper.APIHelper;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 项目
 *
 * @author wax
 * @create 2017/8/5 下午2:19
 *
 * @version v1.0
 **/
@RequestMapping(value = "/api/feedBack")
@Controller
public class APIFeedBackController extends BaseController {

	/**
	 * 反馈列表
	 *
	 * @author lovex
	 * @create 2017/8/24 下午3:50
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map feedBackList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
        Map<String,String> paramsMap = (Map<String,String>)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		//二者不可都为空
		String accountId = paramsMap.get("accountId");
		String feedbackPhone = paramsMap.get("feedbackPhone");
		if ((accountId == null || StringUtils.isBlank(accountId)) && (feedbackPhone == null || StringUtils.isBlank(feedbackPhone))) {
			return CommonUtils.getResultMapByBizEnum(BizEnum.E1201, "accountId", "feedbackPhone");
		}
		/* 生成查询用Example */
		ELExample elExample = new ELExample(request, FeedBack.class);
		/* 查询数据 and admin权限判断 */
//		String accountId = paramsMap.get("accountId");
//		paramsMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");
		PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
		List<FeedBack> feedBackList = this.getFeedBackService().feedBackList(paramsMap);

		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("list", feedBackList);
		resultMap.put(APIHelper.PAGE_NUM, elExample.getPageNum());
		resultMap.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
		resultMap.put(APIHelper.TOTAL, new PageInfo(feedBackList).getTotal());
		return resultMap;
	}


	/**
	 * 新增反馈信息
	 *
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map feedBackAdd(HttpServletResponse response, HttpServletRequest request) throws IOException, JSONException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		// 返回报文
		return this.getFeedBackService().addFeedBack(paramsMap);
	}

	/**
	 * 通过ID获取反馈基本信息
	 *
	 * @author lovex
	 * @create 2017/8/5 下午2:25
	 *
	 * @version v1.0
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Map feedBackGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		long id = Long.parseLong(paramsMap.get("id").toString());

		PsFeedBackDetail feedBack = (PsFeedBackDetail) this.getFeedBackService().getFeedBackId(id);
		if(feedBack.getType().equals(1)){
			Demand demand = new Demand();
			demand.setFeedBackId(feedBack.getId());
			demand = this.getDemandService().selectOne(demand);
			if(demand != null){
				DemandComment demandComment = new DemandComment();
				demandComment.setDemandId(demand.getId());
				List<DemandComment> demandCommentList = this.getDemandCommentService().select(demandComment);
				feedBack.setDemandCommentList(demandCommentList);

				//需求相关文件信息
				DemandFile file = new DemandFile();
				file.setDemandId(demand.getId());
				List<DemandFile> fileList  = this.getDemandFileService().select(file);
				feedBack.setDemandFileList(fileList);
			}
		}else if(feedBack.getType().equals(0)){
			BugInfo bugInfo = new BugInfo();
			bugInfo.setFeedBackId(feedBack.getId());
			List<BugInfo> bugInfoList = this.getBugInfoService().select(bugInfo);
			if(bugInfoList.size() > 0){
				BugComment bugComment = new BugComment();
				bugComment.setBugId(bugInfoList.get(0).getId());
				List<BugComment> bugCommentList = this.getBugCommentService().select(bugComment);
				feedBack.setBugCommentList(bugCommentList);

				//bug相关文件信息
				BugFile file = new BugFile();
				file.setBugId(bugInfoList.get(0).getId());
				List<BugFile> fileList  = this.getBugFileService().select(file);
				feedBack.setBugFileList(fileList);
			}
		}

		/* 返回报文 */
		Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
		resultMap.put("data", feedBack);
		return resultMap;
	}


	/**
	 * 新增反馈评论
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addComment" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map addComment(HttpServletRequest request , HttpServletResponse response){
	    /* 获得已经验证过的参数map */
		@SuppressWarnings("unchecked")
		Map<String,String> paramsMap = (Map<String,String>)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

		Long feedBackId = Long.valueOf((String)paramsMap.get("feedBackId"));
		FeedBack feedBack =	this.getFeedBackService().selectById(feedBackId);
		if(feedBack != null){
			/*功能异常*/
			if(feedBack.getType().equals(0)){
				BugInfo bugInfo = new BugInfo();
				bugInfo.setFeedBackId(feedBackId);
				bugInfo = this.getBugInfoService().selectOne(bugInfo);
				if(bugInfo == null) return  CommonUtils.getResultMapByBizEnum(BizEnum.E1017,"该反馈对应的异常");

				paramsMap.put("bugId",bugInfo.getId().toString());
				return this.getBugCommentService().add(paramsMap);
			/*功能建议*/
			}else if(feedBack.getType().equals(1)){
				Demand demand = new Demand();
				demand.setFeedBackId(feedBackId);
				demand = this.getDemandService().selectOne(demand);
				if(demand == null ) return CommonUtils.getResultMapByBizEnum(BizEnum.E1017,"该反馈对应的建议");

				paramsMap.put("demandId",demand.getId().toString());
				return this.getDemandCommentService().add(paramsMap);
			}
		}
		return CommonUtils.getResultMapByBizEnum(BizEnum.E1017,"该反馈");
	}


	/**
	 * 反馈状态统计
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/countStatus" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map countStatus(HttpServletRequest request , HttpServletResponse response){
		@SuppressWarnings("unchecked")
		Map<String,String> paramsMap = (Map<String,String>)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		//二者不可都为空
		String accountId = paramsMap.get("accountId");
		String feedbackPhone = paramsMap.get("feedbackPhone");
		if ((accountId == null || StringUtils.isBlank(accountId)) && (feedbackPhone == null || StringUtils.isBlank(feedbackPhone))) {
			return CommonUtils.getResultMapByBizEnum(BizEnum.E1201, "accountId", "feedbackPhone");
		}
		/* 查询数据 and admin权限判断 */
//		String accountId = paramsMap.get("accountId");
//		paramsMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");
		return this.getFeedBackService().countStatus(paramsMap);
	}


	@RequestMapping(value = "/baseFileUpload", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map fileCAjax(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String,String> paramsMap = (Map<String,String>)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
		Props props = JoddHelper.getInstance().getJoddProps();
		String filePath = props.getValue("filePath.path");
		File tmpDir = new File(filePath);
		if (!tmpDir.exists()) {
			tmpDir.mkdirs();
		}
		//生成图片路径
		StringBuffer sbRealPath = new StringBuffer();
		Map<String, Object> filemap = new HashMap<String, Object>();
		String sb = paramsMap.get("baseFile");

		if(sb == null){
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9994, "");
		}
		sb = sb.substring(sb.indexOf(",")+1,sb.length());
		BASE64Decoder decoder = new BASE64Decoder();
		try{
			//Base64解码
			byte[] b = decoder.decodeBuffer(sb.toString());
			for(int i=0;i<b.length;++i){
				if(b[i]<0){
					//调整异常数据
					b[i]+=256;
				}
			}
			String uuid = UUID.randomUUID().toString();
			sbRealPath.append(filePath).append("/").append(uuid).append(".").append("jpg");
			OutputStream out = new FileOutputStream(sbRealPath.toString());
			out.write(b);
			out.flush();
			out.close();
			FileInputStream fis = new FileInputStream(sbRealPath.toString());
			filemap.put("filePath",sbRealPath.toString());
			filemap.put("fileName", uuid + ".jpg");
			filemap.put("fileSuffix", "jpg");
			filemap.put("fileSize", fis.available());
		}catch (Exception e){
			e.printStackTrace();
			return CommonUtils.getResultMapByBizEnum(BizEnum.E9999, "");
		}
		return filemap;
	}

}