package com.gysoft.utils.exception;

/**
 * @author DJZ-HXF
 * @Description：文件转换异常
 * @date 2019/1/22
 */
public class FileCovertException extends ApplicationException {
    public FileCovertException() {
    }

    public FileCovertException(String message) {
        super(message);
    }

    public FileCovertException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCovertException(Throwable cause) {
        super(cause);
    }

    public FileCovertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
