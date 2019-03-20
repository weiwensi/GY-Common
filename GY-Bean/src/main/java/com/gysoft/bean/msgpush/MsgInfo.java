package com.gysoft.bean.msgpush;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周宁
 * @Date 2018-11-07 20:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MsgInfo<T> {

    private T msg;

    private MsgType msgType;
}
