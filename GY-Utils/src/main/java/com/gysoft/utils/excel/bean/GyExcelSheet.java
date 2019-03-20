package com.gysoft.utils.excel.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 周宁
 * @Date 2018-10-23 9:13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GyExcelSheet {
    private List<?> sheetData;

    private Class<?> clss;

}
