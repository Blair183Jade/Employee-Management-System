package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for managing employee-related operations.
 * Provides methods for creating, updating, deleting, retrieving, and authenticating employees,
 * as well as supporting advanced operations like pagination.
 */
public interface EmpService {

    /**
     * Retrieves a paginated list of employees based on filtering criteria.
     *
     * @param page The page number of the pagination.
     * @param pageSize The number of employees per page.
     * @param name Optional filter by employee name.
     * @param gender Optional filter by employee gender, where 1 might represent male and 2 female.
     * @param begin Optional filter by the start of the employment date.
     * @param end Optional filter by the end of the employment date.
     * @return A PageBean containing the paginated list of employees and the total count.
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * Deletes multiple employees from the system based on a list of employee IDs.
     *
     * @param ids The list of employee IDs to delete.
     */
    void delete(List<Integer> ids);

    /**
     * Saves a new employee to the database.
     *
     * @param emp The Emp object containing the employee data to be saved.
     */
    void save(Emp emp);

    /**
     * Retrieves a single employee based on their ID.
     *
     * @param id The unique identifier of the employee to be retrieved.
     * @return The Emp object corresponding to the specified ID.
     */
    Emp getById(Integer id);

    /**
     * Updates the information of an existing employee in the database.
     *
     * @param emp The Emp object containing the updated employee data.
     */
    void update(Emp emp);

    /**
     * Authenticates an employee using their username and password.
     *
     * @param emp The Emp object containing the login credentials.
     * @return The authenticated Emp object, or null if authentication fails.
     */
    Emp login(Emp emp);
}
