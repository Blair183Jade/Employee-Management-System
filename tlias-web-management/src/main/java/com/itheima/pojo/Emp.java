package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents an employee within the organization.
 * This class is used to model the employee data, encapsulating details such as personal identifiers,
 * work-related information, and timestamps for tracking creation and updates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    /**
     * Unique identifier for the employee, usually set by the database.
     */
    private Integer id;

    /**
     * Username for the employee login. Must be unique across the system.
     */
    private String username;

    /**
     * Password for employee access. It is recommended to store encrypted passwords only.
     */
    private String password;

    /**
     * Full name of the employee.
     */
    private String name;

    /**
     * Gender of the employee, where 1 represents male and 2 represents female.
     */
    private Short gender;

    /**
     * URL to the image representing the employee's photograph.
     */
    private String image;

    /**
     * Job position of the employee coded as numbers:
     * 1 - Class Supervisor,
     * 2 - Lecturer,
     * 3 - Student Affairs Supervisor,
     * 4 - Teaching Supervisor,
     * 5 - Consultant.
     */
    private Short job;

    /**
     * Date when the employee officially started working in the organization.
     */
    private LocalDate entrydate;

    /**
     * Department ID representing the department to which the employee belongs.
     */
    private Integer deptId;

    /**
     * Timestamp of when the employee record was created.
     */
    private LocalDateTime createTime;

    /**
     * Timestamp of the last update made to the employee record.
     */
    private LocalDateTime updateTime;
}
