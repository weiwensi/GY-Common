package com.gysoft.utils.exception;

/**
 * @Description: 结果已存在异常!
 * @Date: 2018/12/10 14:23
 * @Author: WangLingJi
 */
@SuppressWarnings("serial")
public class AlreadyExistException extends ApplicationException {
    public AlreadyExistException() {
    }

    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistException(Throwable cause) {
        super(cause);
    }

    public AlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
