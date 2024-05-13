package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper for managing department-related database operations.
 */
@Mapper
public interface DeptMapper {

    /**
     * Retrieves a list of all departments from the database.
     * @return List of Dept entities.
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * Deletes a department from the database based on its ID.
     * @param id The ID of the department to delete.
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * Inserts a new department into the database.
     * @param dept The Dept object to be inserted.
     */
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);
}
