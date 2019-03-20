package com.gysoft.utils.test.commons.collections.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 周宁
 * @Date 2018-08-03 14:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String name;

    private int age;

    private Date datejoined;

    private String grade;

    private double salary;

}
