package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * Mapper for managing employee-related database operations.
 */
@Mapper
public interface EmpMapper {

    /**
     * Retrieves a list of all employees that match the provided criteria.
     * @param name Optional name filter.
     * @param gender Optional gender filter.
     * @param begin Optional start date filter.
     * @param end Optional end date filter.
     * @return List of Emp entities matching the criteria.
     */
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * Deletes a list of employees based on their IDs.
     * @param ids List of employee IDs to delete.
     */
    void delete(List<Integer> ids);

    /**
     * Inserts a new employee into the database.
     * @param emp The Emp object to be inserted.
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values(#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
     * Retrieves an employee by their ID.
     * @param id The ID of the employee.
     * @return The Emp entity if found.
     */
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    /**
     * Updates an existing employee's information in the database.
     * @param emp The Emp object containing the updated data.
     */
    void update(Emp emp);

    /**
     * Retrieves an employee by username and password.
     * @param emp The Emp object containing the username and password.
     * @return The Emp entity if found.
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /**
     * Deletes all employees associated with a specific department ID.
     * @param deptId The department ID whose employees are to be deleted.
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
