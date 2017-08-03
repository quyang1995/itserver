package com.longfor.itserver.mapper.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author mayee
 *         Created on 2017/2/9 下午3:42
 * @version v1.0
 */
public interface BeeMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
