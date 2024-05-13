package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service implementation for managing employees.
 * Provides functionality for listing, adding, updating, deleting, and retrieving employees,
 * as well as supporting pagination and authentication.
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * Retrieves a paginated list of employees based on search criteria.
     * Utilizes PageHelper to manage pagination logic before executing the query.
     *
     * @param page     The page number to retrieve.
     * @param pageSize The number of records per page.
     * @param name     Optional filter by employee name.
     * @param gender   Optional filter by gender.
     * @param begin    Optional filter for the start date of employment.
     * @param end      Optional filter for the end date of employment.
     * @return A PageBean containing the list of employees and total count.
     */
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        // Set up pagination
        PageHelper.startPage(page, pageSize);

        // Execute the query
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;

        // Wrap the result set and total number of records into a PageBean
        return new PageBean(p.getTotal(), p.getResult());
    }

    /**
     * Deletes a list of employees by their IDs.
     *
     * @param ids The list of employee IDs to delete.
     */
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    /**
     * Saves a new employee record to the database.
     * Sets the creation and update timestamps to the current time.
     *
     * @param emp The employee data to save.
     */
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The employee object if found.
     */
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * Updates an existing employee's data.
     * Sets the update timestamp to the current time.
     *
     * @param emp The updated employee data.
     */
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    /**
     * Authenticates an employee based on username and password.
     *
     * @param emp The employee data including username and password.
     * @return The authenticated employee if credentials match.
     */
    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
