package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a department within the organization.
 * This class is used to model the department data and map it to the corresponding database table.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    /**
     * Unique identifier for the department.
     */
    private Integer id; // ID of the department

    /**
     * Name of the department.
     */
    private String name; // Name of the department

    /**
     * The date and time when the department was created.
     */
    private LocalDateTime createTime; // Creation timestamp of the department

    /**
     * The date and time when the department details were last updated.
     */
    private LocalDateTime updateTime; // Last update timestamp of the department
}
