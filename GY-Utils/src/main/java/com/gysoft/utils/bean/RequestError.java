package com.gysoft.utils.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @Description: 请求异常 
 * @author DJZ-PJJ
 * @date 2018年4月19日 下午2:30:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestError {
    private String name;
    private String code;
    private String message;
}
