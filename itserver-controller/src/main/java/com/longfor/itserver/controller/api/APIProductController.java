package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.ads.entity.AccountLongfor;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.helper.DataPermissionHelper;
import com.longfor.itserver.common.enums.ProductStatusEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ProgramEmployee;
import com.longfor.itserver.entity.ps.PsProductAll;
import com.longfor.itserver.entity.ps.PsProductCount;
import com.longfor.itserver.service.util.AccountUitl;
import net.mayee.commons.helper.APIHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 产品
 *
 * @author chi.zhang
 * @create 2017/8/3 下午5:26
 *
 * @version v1.0
 **/
@RequestMapping(value = "/api/product")
@Controller
public class APIProductController extends BaseController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * 产品列表
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map list(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

        /* 获取查询用例 */
        ELExample elExample = new ELExample(request, Product.class);

        /* 查询数据 and admin权限判断 */
        String accountId = paramsMap.get("accountId");
        paramsMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");
        PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
        List<PsProductCount> products = this.getProductService().searchList(paramsMap);

        /*返回数据*/
        Map<String, Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        map.put("productList", products);
        map.put(APIHelper.PAGE_NUM, elExample.getPageNum());
        map.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
        map.put(APIHelper.TOTAL, new PageInfo(products).getTotal());
        return map;
    }

    /**
     * 产品列表带各状态统计
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/productList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map productList(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        /*获取查询用例*/
        ELExample elExample = new ELExample(request, Product.class);
        /* 查询数据 and admin权限判断 */
        String accountId = paramsMap.get("accountId");
        paramsMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");
        PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
        List<Product> products = this.getProductService().productCountList(paramsMap);
        /*返回数据*/
        Map<String, Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        map.put("productList", products);
        map.put(APIHelper.PAGE_NUM, elExample.getPageNum());
        map.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
        map.put(APIHelper.TOTAL, new PageInfo(products).getTotal());
        return map;
    }

    /**
     * 根据产品名称获得前20条产品信息
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/nameList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map likeList(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map*/
        Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        /*查询数据*/
        List<Product> products = this.getProductService().searchLikeList(paramsMap);
        /*返回数据*/
        Map<String, Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        map.put("productList", products);
        map.put(APIHelper.TOTAL, new PageInfo(products).getTotal());
        return map;
    }

    /**
     * 根据产品名称获得产品信息A-Z排序
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/getListSort", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map sortList(HttpServletRequest request, HttpServletResponse response) {
        /*查询数据*/
        List<Product> products = this.getProductService().getListSort();
        /*返回数据*/
        Map<String, Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        map.put("productList", products);
        map.put(APIHelper.TOTAL, new PageInfo(products).getTotal());
        return map;
    }

    /**
     * 根据id获取产品信息
     *
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map get(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map*/
        Map<String, String> map = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        String id = map.get("id");
        ProductEmployee productEmployee = new ProductEmployee();
        productEmployee.setProductId(Long.parseLong(id));
        /*查询数据*/
        PsProductAll psProduct = this.getPsProductService().getById(Integer.parseInt(id));
        /*产品相关人员*/
        /*责任人*/
        List<ProductEmployee> personLiableList = this.getProductEmployeeService()
                .searchTypeList(new Long(id), AvaStatusEnum.LIABLEAVA.getCode(), null);
        /*产品经理*/
        List<ProductEmployee> productManagerList = this.getProductEmployeeService()
                .searchTypeList(new Long(id), AvaStatusEnum.MEMBERAVA.getCode(), new Long(AvaStatusEnum.PRODAVA.getCode()));
        /*项目经理*/
        List<ProductEmployee> programManagerList = this.getProductEmployeeService()
                .searchTypeList(new Long(id), AvaStatusEnum.MEMBERAVA.getCode(), new Long(AvaStatusEnum.PROGAVA.getCode()));
        /*开发人员*/
        List<ProductEmployee> developerList = this.getProductEmployeeService()
                .searchTypeList(new Long(id), AvaStatusEnum.MEMBERAVA.getCode(), new Long(AvaStatusEnum.DEVEAVA.getCode()));
        /*UED人员*/
        List<ProductEmployee> uedList = this.getProductEmployeeService()
                .searchTypeList(new Long(id), AvaStatusEnum.MEMBERAVA.getCode(), new Long(AvaStatusEnum.UEDAVA.getCode()));
        /* 测试人员 */
        List<ProductEmployee> testingList = this.getProductEmployeeService()
                .searchTypeList(new Long(id), AvaStatusEnum.MEMBERAVA.getCode(), new Long(AvaStatusEnum.TESTINGAVA.getCode()));
         /* 业务人员 */
        List<ProductEmployee> businessList = this.getProductEmployeeService()
                .searchTypeList(new Long(id), AvaStatusEnum.MEMBERAVA.getCode(), new Long(AvaStatusEnum.BUSINESSAVA.getCode()));


        /*产品关联项目*/
        String likeProgram = psProduct.getLikeProgram();
        List<Program> list = new ArrayList();
        if (likeProgram != null && !"".equals(likeProgram)) {
            String substring = likeProgram.substring(1, likeProgram.length());
            list = this.getProgramService().inProgramId(substring);
            psProduct.setPrograms(list);
        }
        psProduct.setPersonLiableList(personLiableList);
        psProduct.setProductManagerList(productManagerList);
        psProduct.setProgramManagerList(programManagerList);
        psProduct.setDeveloperList(developerList);
        psProduct.setUedList(uedList);
        psProduct.setTestingList(testingList);
        psProduct.setBusinessList(businessList);
        /*返回数据*/
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        resultMap.put("pro", psProduct);
        return resultMap;
    }

    /**
     * 新增产品信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map addProduct(HttpServletRequest request, HttpServletResponse response) {
        try{
            Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
            return this.getProductService().addProduct(paramsMap);
        }catch (Exception e){
            e.printStackTrace();
            return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
    }

    /**
     * 更新产品信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map updateProduct(HttpServletRequest request, HttpServletResponse response) {
        try{
/* 获得已经验证过的参数map*/
            Map<String, String> paramsMap = (Map<String, String>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
            String id = paramsMap.get("id");
        /*责任人*/
            boolean isAllow = false;
            List<ProductEmployee> personLiableList = this.getProductEmployeeService()
                    .searchTypeList(new Long(id), AvaStatusEnum.LIABLEAVA.getCode(), null);
            if (!"".equals(paramsMap.get("modifiedAccountId"))) {
                for (ProductEmployee productEmployee : personLiableList) {
                    if (productEmployee.getAccountId().equals(paramsMap.get("modifiedAccountId"))) {
                        isAllow = true;
                        break;
                    }
                }
            } else {
                return CommonUtils.getResultMapByBizEnum(BizEnum.E9993, "modifiedAccountId");
            }

            Map<String, Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_U);
            if (isAllow) {
            /*更新操作*/
                this.getProductService().updateProduct(paramsMap);
            } else {
                map = CommonUtils.getResultMapByBizEnum(BizEnum.E1026);
            }
        /*返回数据*/
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
    }


    @RequestMapping(value = "/changeLog/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map changeLogList(HttpServletRequest request, HttpServletResponse response) {

        Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Map map = this.getProductEmployeeChangeLogService().orderList(paramsMap);

        return map;
    }

    /**
     * 根据accountId productId 删除产品相关人员
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/delEmployee", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map delEmp(HttpServletRequest request, HttpServletResponse response) {
        try{
            Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
            String productId = (String) paramsMap.get("productId");
            Product product = this.getProductService().selectById(Long.valueOf(productId));
            String accountId = (String) paramsMap.get("accountId");
            String accountType = String.valueOf(AccountUitl.getAccountType(paramsMap));
            if (accountId.equals(product.getContactAccountId())) {
                return CommonUtils.getResultMapByBizEnum(BizEnum.E1027, " 接口人");
            }
            ProductEmployee employee = new ProductEmployee();
            employee.setAccountId(accountId);
            employee.setProductId(Long.valueOf(productId));
            employee.setEmployeeType(AvaStatusEnum.LIABLEAVA.getCode());
            ProductEmployee persionaLiable = this.getProductEmployeeService().selectOne(employee);
            if (persionaLiable != null) {
                //是责任人
                ProductEmployee productEmployee = new ProductEmployee();
                productEmployee.setProductId(Long.valueOf(productId));
                productEmployee.setEmployeeType(AvaStatusEnum.LIABLEAVA.getCode());
                int persionaLiableCount =  this.getProductEmployeeService().selectCount(productEmployee);
                if (persionaLiableCount > 1) {
                    //删除当前用户
                    this.getProductEmployeeService().delEmployee(employee,accountType);
                } else {
                    return CommonUtils.getResultMapByBizEnum(BizEnum.E1027, " 唯一责任人");
                }
            } else {
                //删除成员
                employee.setEmployeeType(AvaStatusEnum.MEMBERAVA.getCode());
                this.getProductEmployeeService().delEmployee(employee,accountType);
            }

            return CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        }catch (Exception e){
            e.printStackTrace();
            return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
    }



    @RequestMapping(value = "/update/status" , method = RequestMethod.POST , produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Map updateStatus(HttpServletRequest request , HttpServletResponse response){
        try{
            Map paramsMap = (Map)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);

            int code = Integer.parseInt((String)paramsMap.get("status"));
            ProductStatusEnum productStatusEnum  =  ProductStatusEnum.getByCode(code);
            if(productStatusEnum == null ){
                return CommonUtils.getResultMapByBizEnum(BizEnum.E9994);
            }

            this.getProductService().updateStatus(paramsMap);
            Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_U);
            resultMap.put("newStatusText", productStatusEnum.getText());
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            return CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
    }
}