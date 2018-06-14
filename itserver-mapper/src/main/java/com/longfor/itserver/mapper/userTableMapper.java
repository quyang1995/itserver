package com.longfor.itserver.mapper;

import com.longfor.itserver.entity.userTable;
import com.longfor.itserver.mapper.base.BeeMapper;

import java.util.List;


public interface userTableMapper extends BeeMapper<userTable> {

    List<userTable> selectUserTable();

}