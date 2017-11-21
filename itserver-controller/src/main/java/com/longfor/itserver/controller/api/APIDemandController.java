package com.longfor.itserver.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.enums.BugStatusEnum;
import com.longfor.itserver.common.enums.DemandStatusEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.Demand;
import com.longfor.itserver.entity.DemandFile;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ps.PsDemandDetail;
import com.longfor.itserver.service.util.AccountUitl;
import org.json.JSONException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 需求
 * @author mochengkun
 * Created by  on 2017/8/22.
 *
 * @version v1.0
 */
@RequestMapping(value="/api/demand")
@Controller
public class APIDemandController extends BaseController {
    /**
     * 需求系列
     *
     * @param response
     * @param request
     * @return map
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map demandList(HttpServletRequest request, HttpServletResponse response) throws IOException {

          /*  获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
           /*获取查询用例*/
        ELExample elExample = new ELExample(request, Demand.class);
        /*查询数据*/
        Map<String, Object> map= this.getDemandService().getPageDemandList(paramsMap,elExample);
        return map;
    }

    /**
     * 导出需求列表
     *
     * @param response
     * @param request
     * @return map
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map exportList(HttpServletRequest request, HttpServletResponse response) throws IOException {

          /*  获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        /*查询数据*/
        Map<String, Object> map= this.getDemandService().getExcelDemandList(paramsMap);
        return map;
    }

    /**
     *  添加需求
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Map demandAdd(HttpServletRequest request,HttpServletResponse response)
            throws IOException, JSONException {
        try{
            Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
            this.getDemandService().addDemand(paramsMap);
            //返回成功信息
            return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
        }catch (Exception e){
            e.printStackTrace();
            return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
    }

    /**
     *  修改需求
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Map demandUpdate(HttpServletResponse response, HttpServletRequest request){
        try{
            Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
            this.getDemandService().updateDemand(paramsMap);
            return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_U);
        }catch (Exception e){
            e.printStackTrace();
            return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
    }

    /**
     *  通过ID获取需求信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Map demandGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
        //获得已经验证过的参数map
        @SuppressWarnings("unchecked")
        Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

        long id = Long.parseLong(paramsMap.get("id").toString());
        PsDemandDetail demand = (PsDemandDetail) this.getDemandService().getDemandById(id);
        if (demand != null){
                //关联产品
            if(demand.getLikeProduct() != null && !"".equals(demand.getLikeProduct())) {
                String likeProduct = demand.getLikeProduct().substring(1, demand.getLikeProduct().length());
                List<Product> product = this.getProductService().searchIdList(likeProduct);
                demand.setProductList(product);
            }
            //关联项目
            if(demand.getLikeProgram() != null && !"".equals(demand.getLikeProgram())) {
                String likeProgram = demand.getLikeProgram().substring(1, demand.getLikeProgram().length());
                List<Program> program = this.getProgramService().inProgramId(likeProgram);
                demand.setProgramList(program);
            }
            //归属产品/项目
            String relationName = "";
            if (demand.getRelationType().equals(1)) {
                Product prod = this.getProductService().selectById(demand.getRelationId());
                relationName = prod.getName();
            } else if (demand.getRelationType().equals(2)) {
                Program prom = this.getProgramService().selectById(demand.getRelationId());
                relationName = prom.getName();
            }
            demand.setRelationName(relationName);

            //需求相关文件信息
            DemandFile file = new DemandFile();
            file.setDemandId(demand.getId());
            List<DemandFile> fileList  = this.getDemandFileService().select(file);
            demand.setDemandFileList(fileList);

            //返回成功信息
            Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
            resultMap.put("data", demand);
            return resultMap;
        }
            //返回成功信息
            Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);


            resultMap.put("data", demand);
        return resultMap;
    }

    @RequestMapping(value = "/changeLog/list",method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Map changeLoglist(HttpServletRequest request,HttpServletResponse response){

        Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        return this.getDemandChangeLogService().orderLimitList(paramsMap);
    }



    /**
     * 变更状态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/update/status" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Map updateStatus(HttpServletRequest request ,HttpServletResponse response){
        try{
            Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
            //状态值有效性验证
            int code = Integer.parseInt((String) paramsMap.get("status"));
            DemandStatusEnum demandStatusEnum = DemandStatusEnum.getByCode(code);
            if(demandStatusEnum == null){
                return CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
            }
            this.getDemandService().updateStatus(paramsMap);
            Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
            resultMap.put("newStatusText", demandStatusEnum.getText());
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
    }

    /**
     * 变更指派人
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/update/callon" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Map updateCallon(HttpServletRequest request ,HttpServletResponse response){
        try{
            Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
            //人员信息有效性验证
            AccountLongfor accountLongfor = AccountUitl.getAccountByAccountTypes((String) paramsMap.get("callonAccountId"),getAdsHelper());
            if(accountLongfor == null){
                return CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
            }

            if(!this.getDemandService().updateCallon(paramsMap)){
                return CommonUtils.getResultMapByBizEnum(BizEnum.E1029);
            }

            Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
            resultMap.put("newCallonEmployeeText", accountLongfor.getName());
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
    }


    /**
     * 根据产品/项目ID 获取 待处理/处理中的BUG
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/statusList" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Map statusList(HttpServletRequest request ,HttpServletResponse response){
        Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

        return this.getDemandService().statusList(request, paramsMap);
    }

    /**
     * 根据需求ID 获取相关需求文件
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getFile" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Map getFile(HttpServletRequest request,HttpServletResponse response){
        Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        //获取文件
        DemandFile  result = this.getDemandFileService().selectById(Long.valueOf((String)paramsMap.get("id")));
        Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        resultMap.put("data",result);
        return  resultMap;
    }


    /**
     * 根据需求ID 删除相关需求文件
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delete/file" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Map deleteFile(HttpServletRequest request,HttpServletResponse response){
        Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        //删除文件
        this.getDemandFileService().deleteById(Long.valueOf((String)paramsMap.get("id")));

        return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_D);
    }

    /**
     * 定时任务 超过1天（24小时）未处理／处理中：通知被指派人
     */
    @Scheduled(cron = "0 0 9 ? * *")
    public void demandTask(){
        this.getDemandService().demandTask();
        System.out.println("Annotation：is show run");
    }
}
