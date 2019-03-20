package com.gysoft.utils.util.http.bean;
/**
 *
 * @Description：自定义HTTP异常
 * @author DJZ-HXF
 * @date 2019/1/22
 */
public enum CustomHttpStatus {

    FILE_COVERTT(9999,"file covert");

    private final int value;
    private final String reasonPhrase;

    CustomHttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
