package com.longfor.itserver.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longfor.itserver.common.constant.ConfigConsts;
import com.longfor.itserver.common.enums.BizEnum;
import com.longfor.itserver.common.util.CommonUtils;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ProductEmployee;
import com.longfor.itserver.entity.Program;
import com.longfor.itserver.entity.ps.PsProduct;
import net.mayee.commons.helper.APIHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map list(HttpServletRequest request,HttpServletResponse response){
        /* 获得已经验证过的参数map */
        Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        /*获取查询用例*/
        ELExample elExample = new ELExample(request, Product.class);
        /*查询数据*/
        PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
        List<Product> products = this.getProductService().searchList(paramsMap);
        /*List<PsProduct> psProducts = this.getProductService().searchList(paramsMap);
        for (PsProduct psProduct:psProducts){
            String str="";
            Long id = psProduct.getId();
            ProductEmployee productEmployee = new ProductEmployee();
            productEmployee.setProductId(id);
            productEmployee.setEmployeeType(1);
            List<ProductEmployee> productEmployeeList = this.getProductEmployeeService().select(productEmployee);
            for(ProductEmployee productEmployee1:productEmployeeList){
                if (!"".equals(productEmployee1.getEmployeeName())) {
                    str += productEmployee1.getEmployeeName() + ", ";
                }
            }
            if(!"".equals(str)){
                str=str.substring(0,str.length()-2);
            }
            psProduct.setEmployeeName(str);
        }*/
        /*返回数据*/
        Map<String, Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        map.put("productList",products);
        map.put(APIHelper.PAGE_NUM, elExample.getPageNum());
        map.put(APIHelper.PAGE_SIZE, elExample.getPageSize());
        map.put(APIHelper.TOTAL, new PageInfo(products).getTotal());
        return map;
    }

    /**
     * 根据产品名称获得前20条产品信息
     * @param response
     * @param request
     * @return Map
     */
    @RequestMapping(value = "/nameList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map likeList(HttpServletRequest request,HttpServletResponse response){
        /* 获得已经验证过的参数map*/
        Map paramsMap = (Map) request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        /*查询数据*/
        List<Product> products = this.getProductService().searchLikeList(paramsMap);
        /*List<PsProduct> psProducts = this.getProductService().searchLikeList(paramsMap);
        for (PsProduct psProduct:psProducts){
            String str="";
            Long id = psProduct.getId();
            ProductEmployee productEmployee = new ProductEmployee();
            productEmployee.setProductId(id);
            productEmployee.setEmployeeType(1);
            List<ProductEmployee> productEmployeeList = this.getProductEmployeeService().select(productEmployee);
            for(ProductEmployee productEmployee1:productEmployeeList){
                if (!"".equals(productEmployee1.getEmployeeName())) {
                    str += productEmployee1.getEmployeeName() + ", ";
                }
            }
            if(!"".equals(str)){
                str=str.substring(0,str.length()-2);
            }
            psProduct.setEmployeeName(str);
        }*/
        /*返回数据*/
        Map<String, Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        map.put("productList",products);
        map.put(APIHelper.TOTAL, new PageInfo(products).getTotal());
        return map;
    }

    /**
     * 根据id获取产品信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map get(HttpServletRequest request,HttpServletResponse response){
        /* 获得已经验证过的参数map*/
        Map<String,String> map = (Map<String,String>)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        String id = map.get("id");
        ProductEmployee productEmployee = new ProductEmployee();
        productEmployee.setProductId(Long.parseLong(id));
        /*查询数据*/
        PsProduct psProduct = this.getPsProductService().getById(Integer.parseInt(id));
        /*产品相关人员*/
        //List<ProductEmployee> productEmployees = this.getProductEmployeeService().select(productEmployee);
        //psProduct.setProductEmployees(productEmployees);
        /*产品关联项目*/
        String likeProgram = psProduct.getLikeProgram();
        String[] strings = likeProgram.split(",");

        ArrayList<Program> list = new ArrayList();
        Program program = new Program();
        String str = "";
        for (int i=1;i<strings.length-1;i++){
            str=strings[i];
            if (str != null && str != ""){
                program = this.getProgramService().selectById(Long.parseLong(str));
                list.add(program);
            }
        }
        psProduct.setPrograms(list);
        /*返回数据*/
        Map<String, Object> resultMap = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS);
        resultMap.put("pro",psProduct);
        return resultMap;
    }

    /**
     * 新增产品信息
     * @param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map addProduct(HttpServletRequest request,HttpServletResponse response){
        /* 获得已经验证过的参数map*/
        Map<String,String> paramsMap = (Map<String,String>)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        /*新增产品信息*/
        this.getProductService().addProduct(paramsMap);
        /*返回数据*/
        Map<String, Object> map = CommonUtils.getResultMapByBizEnum(BizEnum.SSSS_C);
        return map;
    }

    /**
     * 更新产品信息
     * @param
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateProduct(HttpServletRequest request,HttpServletResponse response){
        Map<String,String> map = (Map<String,String>)request.getAttribute(ConfigConsts.REQ_PARAMS_MAP);
        return null;
    }

}