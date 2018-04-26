package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.Product;
import com.longfor.itserver.entity.ps.PsProduct;
import com.longfor.itserver.entity.ps.PsProductCount;
import com.longfor.itserver.mapper.base.BeeMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductMapper extends BeeMapper<Product> {
    List<PsProductCount> selectList(Map map);

    /**
     * d导出项目列表
     * @param map
     * @return
     */
    List<Map> exportList(Map map);

    List<PsProductCount> selectLikeList(Map map);
    List<Product>  selectIdList(String likeProduct);
    /**
     * 产品列表A-Z排序
     * @return
     */
    List<Product> getListSort(Map map);

    List<Product> productCountList(Map map);

    String getNewCode();

    List<Product> getListByLikeAnalyzingConditions(Map map);

    /**
     * 产品汇
     * @param map
     * @return
     */
    List<PsProductCount> productHui(Map<String,Object> map);
    int productHuiNum(Map map);

    /**
     * 产品汇（新）
     * @param map
     * @return
     */
    List<PsProductCount> newProductHui(Map<String,Object> map);

    /**
     * 查询产品标签使用次数
     * @param label
     * @return
     */
    int getCountByLabelId(@Param(value = "label")String label);

    /**
     * 修改产品访问地址，时间
     * @param product
     * @return
     */
    Integer updateProductUrl(Product product);
}