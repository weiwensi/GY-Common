package com.gysoft.utils.test.commons.collections.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周宁
 * @Date 2018-08-03 14:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {
    private String name;

    private int age;

    private String applyFor;
}
