package com.itheima.mapper;

import com.itheima.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper for operations related to the DeptLog entity.
 * Provides database interaction functionalities to insert department logs.
 */
@Mapper
public interface DeptLogMapper {

    /**
     * Inserts a new department log into the database.
     *
     * @param log The DeptLog object containing log details to be stored.
     */
    @Insert("insert into dept_log(create_time, description) values(#{createTime}, #{description})")
    void insert(DeptLog log);

}
