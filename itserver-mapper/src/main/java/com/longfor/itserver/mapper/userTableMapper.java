package generator.warehouse.client;

import generator.warehouse.entity.userTable;
import generator.warehouse.entity.userTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface userTableMapper extends Mapper<userTable> {
    int countByExample(userTableExample example);

    int deleteByExample(userTableExample example);

    List<userTable> selectByExample(userTableExample example);

    int updateByExampleSelective(@Param("record") userTable record, @Param("example") userTableExample example);

    int updateByExample(@Param("record") userTable record, @Param("example") userTableExample example);
}