package com.itheima.service.impl;

import com.itheima.mapper.DeptLogMapper;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the DeptLogService interface, providing logging services for department-related actions.
 * This service is responsible for handling all data access operations for DeptLog entities using the DeptLogMapper.
 */
@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;

    /**
     * Inserts a new department log record into the database.
     * This operation is wrapped in a transaction that is always executed in a new transaction context,
     * ensuring that the log insertion is handled independently of any surrounding transactional contexts.
     *
     * @param deptLog The DeptLog object containing information to be logged.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
