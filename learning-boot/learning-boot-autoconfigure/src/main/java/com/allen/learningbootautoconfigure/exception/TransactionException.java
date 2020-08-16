package com.allen.learningbootautoconfigure.exception;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/01 11:27:00
 */
public class TransactionException extends DataSourceException {

    public TransactionException() {
        super();
    }

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionException(Throwable cause) {
        super(cause);
    }

}
