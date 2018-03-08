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

    List<PsProductCount> productHui(Map<String,Object> map);
    int productHuiNum(Map map);

    /**
     * 查询产品标签使用次数
     * @param label
     * @return
     */
    int getCountByLabelId(@Param(value = "label")String label);
}