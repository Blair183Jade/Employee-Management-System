package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a standardized response structure for API operations.
 * This class is used to send uniform response formats including status codes, messages, and data payloads.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    /**
     * The response code where 1 represents success and 0 represents failure.
     * This is a simple binary indicator useful for quickly checking the operation's outcome.
     */
    private Integer code;

    /**
     * A descriptive message providing additional information about the API response.
     * This can include success messages or detailed error descriptions.
     */
    private String msg;

    /**
     * The data payload returned by the API operation. This can be any type of data
     * relevant to the request and can be null if the response does not require returning data.
     */
    private Object data;

    /**
     * Creates a success response without any data.
     * Useful for operations where only the status of the action is necessary (e.g., delete operations).
     *
     * @return A Result instance indicating a successful operation.
     */
    public static Result success() {
        return new Result(1, "success", null);
    }

    /**
     * Creates a success response with data.
     * Useful for queries or operations where returning data is necessary.
     *
     * @param data The data to return as part of the success response.
     * @return A Result instance indicating a successful operation with data included.
     */
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    /**
     * Creates an error response with a specific message.
     * Useful for indicating the nature of the error to the client.
     *
     * @param msg The error message describing why the operation failed.
     * @return A Result instance indicating a failed operation with the error message.
     */
    public static Result error(String msg) {
        return new Result(0, msg, null);
    }
}
