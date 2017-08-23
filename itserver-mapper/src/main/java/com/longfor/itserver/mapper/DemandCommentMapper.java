package com.longfor.itserver.mapper;


import com.longfor.itserver.entity.DemandComment;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;
public interface DemandCommentMapper extends BeeMapper<DemandComment> {

    List <DemandComment> selectListById(DemandComment demandComment);

    int add (DemandComment demandComment);
}