package com.allen.learningbootvalidation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author chan
 * @date 2020/10/22
 * description: TODO
 */
@Data
@AllArgsConstructor
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(200, null, data);
    }

    public static <T> Result<T> fail(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

}
