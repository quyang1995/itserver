package com.longfor.itserver.service.base;

import com.github.pagehelper.PageHelper;
import com.longfor.itserver.common.util.ELExample;
import com.longfor.itserver.mapper.base.BeeMapper;
import net.mayee.mysql.DTExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @author chi.zhang
 * @create 2017/5/4 上午10:20
 *
 * @version v1.0
 **/
public abstract class AdminBaseService<T> implements IAdminService<T> {
    @Autowired
    protected BeeMapper<T> mapper;

    public BeeMapper<T> getMapper() {
        return mapper;
    }

    public List<T> select(T entity) {
        return mapper.select(entity);
    }

    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    public int selectCount(T entity) {
        return mapper.selectCount(entity);
    }

    public int insert(T entity) {
        return mapper.insert(entity);
    }

    public int insertSelective(T entity) {
        return mapper.insertSelective(entity);
    }

    public int updateById(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    public int updateByIdSelective(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public int delete(T entity) {
        return mapper.delete(entity);
    }

    public int deleteById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public List<T> searchByExample(Object example) {
        DTExample dtExample = (DTExample)example;
        PageHelper.offsetPage(dtExample.getOffset(), dtExample.getLimit(), true);
        return mapper.selectByExample(dtExample.getExample());
    }

    public List<T> searchByELExample(Object example) {
        ELExample elExample = (ELExample)example;
        PageHelper.startPage(elExample.getPageNum(), elExample.getPageSize(), true);
        return mapper.selectByExample(elExample.getExample());
    }


}
