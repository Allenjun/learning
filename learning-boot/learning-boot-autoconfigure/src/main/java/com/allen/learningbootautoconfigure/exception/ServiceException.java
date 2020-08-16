package com.allen.learningbootautoconfigure.exception;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/03 13:52:00
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
