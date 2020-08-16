package com.allen.learningbootautoconfigure.exception;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/01 11:27:00
 */
public class DataSourceException extends RuntimeException {

    public DataSourceException() {
        super();
    }

    public DataSourceException(String message) {
        super(message);
    }

    public DataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceException(Throwable cause) {
        super(cause);
    }

}
