package com.gysoft.utils.excel.util;

import com.gysoft.utils.excel.annotation.ExcelField;
import com.gysoft.utils.excel.annotation.ExcelSheet;
import com.gysoft.utils.excel.bean.GyExcelSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel导出工具
 *
 * @author 周宁
 */
public class ExcelExportUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelExportUtil.class);


    /**
     * 导出Excel对象包含多个sheet页
     *
     * @param gyExcelSheets
     * @return Workbook
     */
    public static Workbook exportWorkbook(GyExcelSheet... gyExcelSheets) {
        Workbook workbook = new HSSFWorkbook();
        for (GyExcelSheet gyExcelSheet : gyExcelSheets) {
            makeSheet(workbook, gyExcelSheet.getSheetData(), gyExcelSheet.getClss());
        }
        return workbook;
    }

    private static void makeSheet(Workbook workbook, List<?> sheetDataList, Class<?> clzz) {
        Class<?> sheetClass = clzz;
        ExcelSheet excelSheet = sheetClass.getAnnotation(ExcelSheet.class);
        String sheetName = sheetClass.getSimpleName();
        int headColorIndex = -1;
        if (excelSheet != null) {
            if (excelSheet.name() != null && excelSheet.name().trim().length() > 0) {
                sheetName = excelSheet.name().trim();
            }
            headColorIndex = excelSheet.headColor().getIndex();
        }
        Sheet existSheet = workbook.getSheet(sheetName);
        if (existSheet != null) {
            for (int i = 2; i <= 1000; i++) {
                String newSheetName = sheetName.concat(String.valueOf(i));  // avoid sheetName repetition
                existSheet = workbook.getSheet(newSheetName);
                if (existSheet == null) {
                    sheetName = newSheetName;
                    break;
                } else {
                    continue;
                }
            }
        }

        Sheet sheet = workbook.createSheet(sheetName);
        List<Field> fields = new ArrayList<Field>();
        if (sheetClass.getDeclaredFields() != null && sheetClass.getDeclaredFields().length > 0) {
            for (Field field : sheetClass.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                fields.add(field);
            }
        }
        if (fields == null || fields.size() == 0) {
            throw new RuntimeException("data field can not be empty.");
        }
        CellStyle[] fieldDataStyleArr = new CellStyle[fields.size()];
        int[] fieldWidthArr = new int[fields.size()];
        Row headRow = sheet.createRow(0);
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            String fieldName = field.getName();
            int fieldWidth = 0;
            HorizontalAlignment align = null;
            if (excelField != null) {
                if (excelField.name() != null && excelField.name().trim().length() > 0) {
                    fieldName = excelField.name().trim();
                }
                fieldWidth = excelField.width();
                align = excelField.align();
            }
            fieldWidthArr[i] = fieldWidth;
            CellStyle fieldDataStyle = workbook.createCellStyle();
            if (align != null) {
                fieldDataStyle.setAlignment(align);
            }
            fieldDataStyleArr[i] = fieldDataStyle;
            CellStyle headStyle = workbook.createCellStyle();
            headStyle.cloneStyleFrom(fieldDataStyle);
            if (headColorIndex > -1) {
                headStyle.setFillForegroundColor((short) headColorIndex);
                headStyle.setFillBackgroundColor((short) headColorIndex);
                headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            }
            Cell cellX = headRow.createCell(i, CellType.STRING);
            cellX.setCellStyle(headStyle);
            cellX.setCellValue(String.valueOf(fieldName));
        }
        for (int dataIndex = 0; dataIndex < sheetDataList.size(); dataIndex++) {
            int rowIndex = dataIndex + 1;
            Object rowData = sheetDataList.get(dataIndex);
            Row rowX = sheet.createRow(rowIndex);
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                try {
                    field.setAccessible(true);
                    Object fieldValue = field.get(rowData);
                    String fieldValueString = FieldReflectionUtil.formatValue(field, fieldValue);
                    Cell cellX = rowX.createCell(i, CellType.STRING);
                    cellX.setCellValue(fieldValueString);
                    cellX.setCellStyle(fieldDataStyleArr[i]);
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(), e);
                    throw new RuntimeException(e);
                }
            }
        }
        for (int i = 0; i < fields.size(); i++) {
            int fieldWidth = fieldWidthArr[i];
            if (fieldWidth > 0) {
                sheet.setColumnWidth(i, fieldWidth);
            } else {
                sheet.autoSizeColumn((short) i);
            }
        }
    }


    /**
     * 导出Excel文件到磁盘
     *
     * @param filePath
     * @param dataList
     */
    public static void exportToFile(String filePath, List<?> dataList, Class<?> clzz) {
        exportToFile(filePath, GyExcelSheet.builder().sheetData(dataList).clss(clzz).build());
    }

    public static void exportToFile(String filePath, GyExcelSheet... gyExcelSheets) {
        Workbook workbook = exportWorkbook(gyExcelSheets);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            closeOutputStream(fileOutputStream);
        }
    }

    /**
     * 导出Excel的字节数据
     *
     * @param clzz
     * @param dataList
     * @return byte[]
     */
    public static byte[] exportToBytes(List<?> dataList, Class<?> clzz) {
        return exportToBytes(GyExcelSheet.builder().sheetData(dataList).clss(clzz).build());
    }

    public static byte[] exportToBytes(GyExcelSheet... gyExcelSheets) {
        Workbook workbook = exportWorkbook(gyExcelSheets);
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] result = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.flush();
            result = byteArrayOutputStream.toByteArray();
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            closeOutputStream(byteArrayOutputStream);
        }
    }

    /**
     * 关闭文件流
     *
     * @param outputStream
     */
    private static void closeOutputStream(OutputStream outputStream) {
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
