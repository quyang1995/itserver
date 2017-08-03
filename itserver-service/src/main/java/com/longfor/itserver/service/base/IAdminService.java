package com.longfor.itserver.service.base;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用接口
 */
@Service
public interface IAdminService<T> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    List<T> select(T entity);

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param id
     * @return
     */
    T selectById(Object id);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    T selectOne(T entity);

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param entity
     * @return
     */
    int selectCount(T entity);


    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    int insertSelective(T entity);


    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    int updateById(T entity);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    int updateByIdSelective(T entity);


    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param entity
     * @return
     */
    int delete(T entity);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param id
     * @return
     */
    int deleteById(Object id);

    List<T> searchByExample(Object example);

    List<T> searchByELExample(Object example);


}
