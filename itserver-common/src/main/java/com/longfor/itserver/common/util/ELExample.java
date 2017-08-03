package com.longfor.itserver.common.util;

import com.longfor.itserver.common.constant.ConfigConsts;
import net.mayee.commons.entity.api.APIObject;
import net.mayee.commons.helper.APIHelper;
import net.mayee.mysql.SearchFilter;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 针对WEB端接口的列表查询，封装类,简化工作量
 *
 * @author mayee
 *         Created on 2017/2/10 下午4:30
 * @version v1.0
 */
public class ELExample {

    protected HttpServletRequest request;
    private Example example;
    private int pageNum;
    private int pageSize;

    //用于模糊搜索时的字段分隔符
    private static final String SEARCH_PLACE_HOLDER = "@";
    //基于约定的排序, 表中必须含有create_time字段
    private static final String ORDER_BY_CREATETIME = "create_time DESC";
    private static List<String> IGNORE_LIST = new ArrayList<>();

    /**
     * IGNORE_LIST为例外字段
     */
    static {
        IGNORE_LIST.add(APIHelper.PAGE_NUM);
        IGNORE_LIST.add(APIHelper.PAGE_SIZE);
    }

    public ELExample(HttpServletRequest request, Class<?> entityClass) {
        this.request = request;
        example = new Example(entityClass);
        buildExample(null);
        example.setOrderByClause(ORDER_BY_CREATETIME);
    }

    public ELExample(HttpServletRequest request, Class<?> entityClass, List<String> additionalConditionsList) {
        this.request = request;
        example = new Example(entityClass);
        buildExample(additionalConditionsList);
        example.setOrderByClause(ORDER_BY_CREATETIME);
    }

    private void buildExample(List<String> additionalConditionsList) {
        //约定名称
        String searchText = "searchText";
        /* 获得已经验证过的参数map */
        @SuppressWarnings("unchecked")
        Map<String, String> paramsMap = (Map<String, String>) request
                .getAttribute(ConfigConsts.REQ_PARAMS_MAP);

        this.pageNum = Integer.parseInt(paramsMap.get(APIHelper.PAGE_NUM));
        this.pageSize = Integer.parseInt(paramsMap.get(APIHelper.PAGE_SIZE));

        List<String> searchCellList = new ArrayList<>();
        List<String> eqCellList = new ArrayList<>();

        String apiId = paramsMap.get(APIHelper.API_ID);
        APIObject apiObject = APIHelper.getInstance().getAPIObject(apiId);
        apiObject.getRequestPropertyList().forEach(item -> {
            if (searchText.equals(item.getName()) && StringUtils.isNotBlank(item.getSearchFor())) {
                String[] cellList = item.getSearchFor().split(SEARCH_PLACE_HOLDER);
                for (String cell : cellList) {
                    if (!searchCellList.contains(cell)) {
                        searchCellList.add(cell);
                    }
                }
            } else {
                if (!IGNORE_LIST.contains(item.getName())
                        && !APIHelper.getInstance().isReservedName(item.getName())
                        && !eqCellList.contains(item.getName())) {
                    eqCellList.add(item.getName());
                }
            }
        });

        searchCellList.forEach(item -> {
            Example.Criteria c = example.or().andLike(item, "%" + paramsMap.get(searchText) + "%");
            eqCellList.forEach(item2 -> {
                c.andEqualTo(item2, paramsMap.get(item2));
            });
            if(additionalConditionsList != null){
                additionalConditionsList.forEach(item3 -> {
                    if(item3.startsWith(SearchFilter.Operator.EQ.toString())
                            && item3.contains(SearchFilter.getSplitKey1())
                            && item3.contains("=")){
                        String[] values = item3.split(SearchFilter.getSplitKey1());
                        String[] eqValues = values[1].split("=");
                        if(StringUtils.isNotBlank(eqValues[1])){
                            c.andEqualTo(eqValues[0], eqValues[1]);
                        }
                    }
                });
            }
        });
    }

    public Example getExample() {
        return example;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }
}
