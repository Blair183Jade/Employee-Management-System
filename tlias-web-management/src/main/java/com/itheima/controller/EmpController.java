package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller for managing employee operations.
 * Provides endpoints for adding, deleting, updating, and querying employee data.
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * Retrieves a paginated list of employees based on provided criteria.
     *
     * @param page     the current page number, default is 1.
     * @param pageSize the number of records per page, default is 10.
     * @param name     optional filter by employee name.
     * @param gender   optional filter by gender (1 for male, 2 for female).
     * @param begin    optional filter for the start of the employment date range.
     * @param end      optional filter for the end of the employment date range.
     * @return Result object containing the paginated employee data.
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("Pagination query, Parameters: {},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    /**
     * Deletes employees by their IDs.
     *
     * @param ids a list of employee IDs to delete.
     * @return Result object indicating success of the operation.
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("Batch delete operation, IDs: {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * Adds a new employee.
     *
     * @param emp the employee data to save.
     * @return Result object indicating success of the operation.
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("Adding new employee, Employee: {}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * Retrieves detailed information about a specific employee by their ID.
     *
     * @param id the ID of the employee to retrieve.
     * @return Result object containing the employee data.
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("Querying employee information by ID, ID: {}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * Updates an existing employee's information.
     *
     * @param emp the employee data to update.
     * @return Result object indicating success of the operation.
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("Updating employee information, Employee: {}", emp);
        empService.update(emp);
        return Result.success();
    }
}
