package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing department-related operations.
 * Provides endpoints for listing, adding, and deleting departments.
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * Retrieves a list of all departments.
     * Logs the action and returns the list wrapped in a Result object with success status.
     *
     * @return Result object containing a list of all departments and a success status.
     */
    @GetMapping
    public Result list() {
        log.info("Retrieving all department data");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }


    /**
     * Deletes a department based on the department ID provided.
     * Uses the @Log annotation to enable logging of the method call, including method parameters.
     *
     * @param id The ID of the department to be deleted.
     * @return Result object with success status.
     * @throws Exception if the delete operation fails.
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("Deleting department with ID: {}", id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * Adds a new department.
     * The department details are provided in the body of the POST request.
     * Uses the @Log annotation to enable logging of the method call.
     *
     * @param dept The Department object to add.
     * @return Result object with success status.
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("Adding new department: {}", dept);
        deptService.add(dept);
        return Result.success();
    }
}
