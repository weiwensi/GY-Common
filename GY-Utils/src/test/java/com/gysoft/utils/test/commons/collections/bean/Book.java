package com.gysoft.utils.test.commons.collections.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周宁
 * @Date 2018-08-02 8:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String name;

    private String isbn;

    private double retailPrice;
}
