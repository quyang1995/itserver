package com.longfor.itserver.service;


import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ps.PsProductCount;
import com.longfor.itserver.service.base.IAdminService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IProductService extends IAdminService<Product> {
    List<PsProductCount> searchList(Map map);

    /**
     * 导出项目列表
     * @param map
     * @return
     */
    List<Map> exportList(Map map);

    List<PsProductCount> searchLikeList(Map map);
    Map<String, Object> addProduct(Map map);
    List<Product> searchIdList(String likeProduct);
    boolean updateProduct(Map map) throws Exception;

    /**
     * 修改产品访问地址，时间
     * @param map
     * @return
     */
    void updateProductUrl(Map map);
    /**
     * 产品列表A-Z排序
     * @return
     */
    List<Product> getListSort(Map map);

    List<Product> productCountList(Map map);
    /**
     * 修改产品状态
     * @param paramsMap
     * @return
     */
    boolean updateStatus(Map paramsMap);

    /**
     * 产品汇列表
     * @param map
     * @return
     */
    List<PsProductCount> productHui(Map<String,Object> map);

    /**
     * 产品汇列表（新）
     * @param paramsMap
     * @return
     */
    Map<String,Object> newProductHui(Map<String,Object> paramsMap);
    /**
     * 产品汇数量
     * @param map
     * @return
     */
    int productHuiNum(Map map);
    /**
     * 查询产品标签使用次数
     * @param label
     * @return
     */
    int getCountByLabelId(@Param(value = "label")String label);

    /**
     * 删除产品：产品下不存在项目的允许删除
     * @param map
     */
    Integer deleteProduct(Map map);
}
