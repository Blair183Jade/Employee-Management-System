package com.itheima.service;

import com.itheima.pojo.DeptLog;

/**
 * Interface for services that manage logging for department-related actions.
 * This interface defines the operations that must be supported by any class that implements
 * department log handling, ensuring consistency and adherence to defined operations.
 */
public interface DeptLogService {

    /**
     * Inserts a department log entry into a data store.
     * This method is responsible for persisting log information related to department actions,
     * such as creation, updates, or deletions of department records.
     *
     * @param deptLog The DeptLog object containing the log details to be inserted.
     */
    void insert(DeptLog deptLog);
}
