package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a container for paginated data in the application.
 * This generic class is used to hold any type of paginated result, making it easy to handle
 * pagination logic in a consistent manner across different parts of the application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {

    /**
     * The total number of records for the query. This is used to calculate the number of pages.
     */
    private Long total;

    /**
     * The list of records for the current page. This list can hold any type of object,
     * allowing for flexible use with different data entities.
     */
    private List rows;
}
