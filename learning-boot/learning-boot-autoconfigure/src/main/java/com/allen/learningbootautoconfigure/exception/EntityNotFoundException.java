package com.allen.learningbootautoconfigure.exception;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/01 11:27:00
 */
public class EntityNotFoundException extends DataSourceException {

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

}
