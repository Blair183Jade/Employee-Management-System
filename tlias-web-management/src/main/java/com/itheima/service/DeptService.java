package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

/**
 * Service interface for department management operations.
 * This interface provides methods for managing department data, ensuring that all department management
 * functionalities are implemented in a consistent and predictable manner.
 */
public interface DeptService {

    /**
     * Retrieves a list of all departments from the database.
     * This method is used to fetch a complete list of department entries for display or further processing.
     *
     * @return A list of Dept objects, each representing a department.
     */
    List<Dept> list();

    /**
     * Deletes a department identified by its ID.
     * This method handles the deletion of a department and ensures that any necessary cleanup related to the department
     * is performed, such as removing associated employees or department-specific data.
     *
     * @param id The unique identifier of the department to be deleted.
     * @throws Exception If any error occurs during the process that prevents the department from being deleted properly.
     */
    void delete(Integer id) throws Exception;

    /**
     * Adds a new department to the database.
     * This method is responsible for creating a new department entry. It sets initial values and saves the department data
     * to the database, ensuring that all required fields are populated and valid.
     *
     * @param dept The Dept object containing the data for the new department to be added.
     */
    void add(Dept dept);
}
