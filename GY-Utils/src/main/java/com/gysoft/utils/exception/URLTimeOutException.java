package com.gysoft.utils.exception;

/**
 * 上传下载接口地址验证超时异常
 * @author 彭佳佳
 * @data 2018年3月6日
 */
@SuppressWarnings("serial")
public class URLTimeOutException extends ApplicationException {

    public URLTimeOutException() {
    }

    public URLTimeOutException(String message) {
        super(message);
    }

    public URLTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public URLTimeOutException(Throwable cause) {
        super(cause);
    }

    public URLTimeOutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
