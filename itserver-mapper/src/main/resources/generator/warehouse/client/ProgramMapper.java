package generator.warehouse.client;

import generator.warehouse.entity.Program;
import generator.warehouse.entity.ProgramExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ProgramMapper extends Mapper<Program> {
    int countByExample(ProgramExample example);

    int deleteByExample(ProgramExample example);

    List<Program> selectByExample(ProgramExample example);

    int updateByExampleSelective(@Param("record") Program record, @Param("example") ProgramExample example);

    int updateByExample(@Param("record") Program record, @Param("example") ProgramExample example);
}