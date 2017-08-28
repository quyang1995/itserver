package generator.warehouse.client;

import generator.warehouse.entity.FeedBack;
import generator.warehouse.entity.FeedBackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface FeedBackMapper extends Mapper<FeedBack> {
    int countByExample(FeedBackExample example);

    int deleteByExample(FeedBackExample example);

    List<FeedBack> selectByExample(FeedBackExample example);

    int updateByExampleSelective(@Param("record") FeedBack record, @Param("example") FeedBackExample example);

    int updateByExample(@Param("record") FeedBack record, @Param("example") FeedBackExample example);
}