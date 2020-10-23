package com.allen.learningbootvalidation.pojo;

/**
 * @author chan
 * @date 2020/10/22
 * description: TODO
 */
public enum ResultCode {
    SUCCESS(200, "SUCCESS"),
    PARAMVALIDATEERROR(200, "参数校验失败");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
