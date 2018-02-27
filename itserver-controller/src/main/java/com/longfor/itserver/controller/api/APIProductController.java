package com.longfor.itserver.controller.api;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.AvaStatusEnum;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.helper.DataPermissionHelper;
import com.longfor.itserver.common.enums.ProductStatusEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.*;
import com.longfor.itserver.entity.ps.PsProductAll;
import com.longfor.itserver.entity.ps.PsProductCount;
import com.longfor.itserver.service.util.AccountUitl;
import net.mayee.commons.helper.APIHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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
//        paramsMap.put("isAdmin", DataPermissionHelper.getInstance().isShowAllData(accountId) ? "1" : "0");
        paramsMap.put("isAdmin", "1");
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
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        String id = map.get("id");
        String accountId = map.get("accountId");
        ProductEmployee productEmployee = new ProductEmployee();
        productEmployee.setProductId(Long.parseLong(id));
        /*查询数据*/
        PsProductAll psProduct = this.getPsProductService().getById(Integer.parseInt(id));
        /*产品相关人员*/
        /*责任人*/
        List<ProductEmployee> personLiableList = this.getProductEmployeeService()
                .searchTypeList(new Long(id), AvaStatusEnum.LIABLEAVA.getCode(), null);

        //验证当前人员权限********beg
        if (0 == psProduct.getType() && StringUtils.isNotBlank(accountId)
                && !psProduct.getContactAccountId().equals(accountId)
                && !psProduct.getContactAccountId1().equals(accountId)){
            Map<String, Object> accountMap = new HashMap<String, Object>();
            accountMap.put("accountId", accountId);
            accountMap.put("productId", id);
            List<ProductEmployee> accountList = this.getProductEmployeeService().searchTypeListMap(accountMap);
            if (accountList==null || accountList.isEmpty()){
                resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E1036,personLiableList.get(0).getEmployeeName());
                return resultMap;
            }
            map.remove("accountId");
        }
        //验证当前人员权限********end

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

    /**
     * 置顶，取消置顶
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/productIsTop" ,method = RequestMethod.POST ,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Map productIsTop(HttpServletRequest request){
        Map resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try {
            Map<String,String> paramsMap = (Map<String,String>)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
            LOG.info("------productIsTop:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
            Product product = new Product();
            product.setId(Long.valueOf(paramsMap.get("id")));
            product.setIsTop(paramsMap.get("isTop"));
            this.getProductService().updateByIdSelective(product);
        } catch ( Exception e) {
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return  resultMap;
    }

    /**
     * 产品汇列表
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/productHui", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map productHui(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            /* 获得已经验证过的参数map */
            @SuppressWarnings("unchecked")
            Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
            CommonUtils.buildPageParams(paramsMap);
            List<PsProductCount> productHui = this.getProductService().productHui(paramsMap);

            resultMap.put("productList", productHui);
            resultMap.put(APIHelper.PAGE_NUM, paramsMap.get("pageNum"));
            resultMap.put(APIHelper.PAGE_SIZE, paramsMap.get("pageSize"));
            resultMap.put(APIHelper.TOTAL, this.getProductService().productHuiNum(paramsMap));
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }

    /**
     * 产品标签类型列表
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/productLabelTypeList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map productLabelTypeList(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            LOG.info("------productLabelTypeList:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
            /*分页参数处理*/
            CommonUtils.buildPageParams(paramsMap);
             /* 获取查询用例 */
            List<ProductLabelType> productLabelTypeList = this.getProductLabelTypeService().getLabelTypeList(paramsMap);

            /*返回数据*/
            resultMap.put("productLabelTypeList", productLabelTypeList);
            resultMap.put(APIHelper.PAGE_NUM, paramsMap.get("pageNum"));
            resultMap.put(APIHelper.PAGE_SIZE, paramsMap.get("pageSize"));
            resultMap.put(APIHelper.TOTAL,  this.getProductLabelTypeService().getLabelTypeCount(paramsMap));
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }

    /**
     * 产品标签列表
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/productLabelList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map productLabelList(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            LOG.info("------productLabelList:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
            /*分页参数处理*/
            CommonUtils.buildPageParams(paramsMap);
             /* 获取查询用例 */
            List<ProductLabel> productLabelList = this.getProductLabelService().getLabelList(paramsMap);

            /*返回数据*/
            resultMap.put("productLabelList", productLabelList);
            resultMap.put(APIHelper.PAGE_NUM, paramsMap.get("pageNum"));
            resultMap.put(APIHelper.PAGE_SIZE, paramsMap.get("pageSize"));
            resultMap.put(APIHelper.TOTAL,  this.getProductLabelService().getLabelCount(paramsMap));
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }

    /**
     * 添加产品标签类型
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/addProductLabelType", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map addProductLabelType(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            LOG.info("------addProductLabelType:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
            Date date = new Date();
            ProductLabelType  productLabelType = new ProductLabelType();
            productLabelType.setLabelName(paramsMap.get("labelName").toString());
            productLabelType.setModifiedAccountId(paramsMap.get("accountId").toString());
            productLabelType.setCreateTime(date);
            productLabelType.setModifiedTime(date);
            this.getProductLabelTypeService().insert(productLabelType);
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }

    /**
     * 添加产品标签
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/addProductLabel", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map addProductLabel(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            LOG.info("------addProductLabel:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
            Date date = new Date();
            ProductLabel  productLabel = new ProductLabel();
            productLabel.setLabelTypeId(Long.valueOf(paramsMap.get("labelTypeId").toString()));
            productLabel.setLabelName(paramsMap.get("labelName").toString());
            productLabel.setModifiedAccountId(paramsMap.get("accountId").toString());
            productLabel.setCreateTime(date);
            productLabel.setModifiedTime(date);
            this.getProductLabelService().insert(productLabel);
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }

    /**
     * 删除产品标签类型
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/delProductLabelType", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map delProductLabelType(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            LOG.info("------delProductLabelType:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
            ProductLabelType productLabelType = new ProductLabelType();
            productLabelType.setId(Long.valueOf(paramsMap.get("id").toString()));
            this.getProductLabelTypeService().delete(productLabelType);
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }

    /**
     * 删除产品标签
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/delProductLabel", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map delProductLabel(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            LOG.info("------delProductLabel:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
            ProductLabel productLabel = new ProductLabel();
            productLabel.setId(Long.valueOf(paramsMap.get("id").toString()));
            this.getProductLabelService().delete(productLabel);
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }

    /**
     * 修改产品标签类型
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/updateProductLabelType", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map updateProductLabelType(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            LOG.info("------updateProductLabelType:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
            ProductLabelType  productLabelType = new ProductLabelType();
            productLabelType.setId(Long.valueOf(paramsMap.get("id").toString()));
            productLabelType.setLabelName(paramsMap.get("labelName").toString());
            productLabelType.setModifiedAccountId(paramsMap.get("accountId").toString());
            this.getProductLabelTypeService().updateByIdSelective(productLabelType);
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }

    /**
     * 修改产品标签
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/updateProductLabel", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map updateProductLabel(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            LOG.info("------updateProductLabel:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
            ProductLabel  productLabel = new ProductLabel();
            productLabel.setId(Long.valueOf(paramsMap.get("id").toString()));
            productLabel.setLabelTypeId(Long.valueOf(paramsMap.get("labelTypeId").toString()));
            productLabel.setLabelName(paramsMap.get("labelName").toString());
            productLabel.setModifiedAccountId(paramsMap.get("accountId").toString());
            this.getProductLabelService().updateByIdSelective(productLabel);
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }

    /**
     * 获取产品标签类型
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/getProductLabelType", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map getProductLabelType(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            LOG.info("------getProductLabelType:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
            ProductLabelType productLabelType = new ProductLabelType();
            productLabelType.setId(Long.valueOf(paramsMap.get("id").toString()));
            resultMap.put("data",this.getProductLabelTypeService().select(productLabelType));
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }

    /**
     * 获取产品标签
     *
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/getProductLabel", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map getProductLabel(HttpServletRequest request, HttpServletResponse response) {
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = (Map<String, Object>) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        try{
            LOG.info("------getProductLabel:-----------------"+ JSON.toJSONString(paramsMap)+"-----------------------");
            ProductLabel productLabel = new ProductLabel();
            productLabel.setId(Long.valueOf(paramsMap.get("id").toString()));
            resultMap.put("data",this.getProductLabelService().select(productLabel));
        }catch (Exception e){
            e.printStackTrace();
            resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.E9999);
        }
        return resultMap;
    }
}