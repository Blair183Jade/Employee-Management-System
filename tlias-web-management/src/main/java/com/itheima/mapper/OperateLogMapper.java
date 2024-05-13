package com.itheima.mapper;

import com.itheima.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper interface for operations related to the OperateLog entity.
 * This interface facilitates database interactions, particularly for inserting operation logs.
 */
@Mapper
public interface OperateLogMapper {

    /**
     * Inserts a new operation log into the database.
     * This method maps fields from the OperateLog object to the corresponding columns in the database table.
     *
     * @param log The OperateLog object containing data to be inserted into the database.
     */
    @Insert("insert into operate_log (operate_user, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateUser}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    void insert(OperateLog log);

}
