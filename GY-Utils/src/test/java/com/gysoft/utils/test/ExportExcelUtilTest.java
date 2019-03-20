package com.gysoft.utils.test;

import com.gysoft.utils.excel.annotation.ExcelField;
import com.gysoft.utils.excel.annotation.ExcelSheet;
import com.gysoft.utils.excel.bean.GyExcelSheet;
import com.gysoft.utils.excel.util.ExcelExportUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 周宁
 * @Date 2018-05-03 21:02
 */
public class ExportExcelUtilTest {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>();
        Student student1 =new Student("张三",21,177);
        Student student2 =new Student("李四",19,174);
        Student student3 =new Student("赵武",18,168);
        Student student4 =new Student("周六",22,188);
        students.addAll(Arrays.asList(new Student[]{student1,student2,student3,student4}));
        List<Car> cars = new ArrayList<Car>();
        Car car1 = new Car("马自达","white",140000);
        Car car2 = new Car("大众","black",60000);
        Car car3 = new Car("丰田","green",220000);
        cars.addAll(Arrays.asList(new Car[]{car1,car2,car3}));
        ExcelExportUtil.exportToFile("C:\\Users\\Administrator\\Desktop\\mmm.xls",GyExcelSheet.builder().sheetData(students).clss(Student.class).build(),GyExcelSheet.builder().sheetData(cars).clss(Car.class).build());
        ExcelExportUtil.exportToFile("C:\\Users\\Administrator\\Desktop\\nnn.xls",students,Student.class);
    }

    @Data
    @AllArgsConstructor
    @ExcelSheet(name = "学生")
    static class Student{
        @ExcelField(name="学生姓名")
        private String name;
        @ExcelField(name="学生年龄")
        private int age;
        @ExcelField(name="学生身高")
        private int stature;

    }

    @Data
    @AllArgsConstructor
    @ExcelSheet(name = "车子")
    static class Car{
        @ExcelField(name="车名称")
        private String carName;
        @ExcelField(name="车颜色")
        private String color;
        @ExcelField(name="价格")
        private double money;
    }
}
