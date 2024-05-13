package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service implementation for managing departments within the organization.
 * This service handles all department-related operations such as creation, deletion, and listing of departments.
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    /**
     * Retrieves a list of all departments from the database.
     * @return a list of Dept entities
     */
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    /**
     * Deletes a department and all associated employees, logging the operation.
     * This method is transactional, meaning all operations must complete successfully, or none will be applied.
     *
     * @param id the unique identifier of the department to be deleted
     * @throws Exception if there is an error during the delete operation
     */
    @Transactional
    @Override
    public void delete(Integer id) throws Exception {
        try {
            deptMapper.deleteById(id); // Delete the department by ID

            // Uncomment to simulate an error which triggers transaction rollback
            // int i = 1/0;
            // if(true) { throw new Exception("Simulated error..."); }

            empMapper.deleteByDeptId(id); // Delete all employees in the department
        } finally {
            // Log the action, even in case of exceptions (finally block ensures execution)
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("Executed department dissolution for department ID " + id);
            deptLogService.insert(deptLog);
        }
    }

    /**
     * Adds a new department to the database.
     * This method sets the creation and update times to the current datetime before inserting the department.
     *
     * @param dept the Dept object containing the department data to be added
     */
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }
}
