package com.gysoft.utils.test.commons.collections;

import com.gysoft.utils.test.commons.collections.bean.Employee;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author 周宁
 * @Date 2018-08-04 8:38
 */
public class ClosureTest {

    public static void main(String[] args) {
        System.out.println(StringUtils.center("closure test start", 31, "="));
        List<Employee> employees = new ArrayList() {{
            add(new Employee("Tony", 26, new Date(), "E4", 2000));
            add(new Employee("Michelle", 24, new Date(), "E4", 2000));
            add(new Employee("Jack", 28, new Date(), "E5", 3000));
        }};
        printColl("Before salary increase:", employees);
        //设置closure
        Closure salaryIncreaseClosure = input -> {
            Employee employee = (Employee) input;
            employee.setSalary(employee.getSalary() * 1.25);
        };
        //处理emplyees中所有employee的salary
        CollectionUtils.forAllDo(employees, salaryIncreaseClosure);
        printColl("Before salary increase:", employees);
        System.out.println(StringUtils.center("closure test end", 31, "="));
    }

    public static void printColl(String label, Collection c) {
        if (StringUtils.isNotBlank(label)) {
            System.out.println(label);
        }
        Iterator iter = c.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
