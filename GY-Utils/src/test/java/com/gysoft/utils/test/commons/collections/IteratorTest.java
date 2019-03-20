package com.gysoft.utils.test.commons.collections;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.iterators.ArrayListIterator;
import org.apache.commons.collections.iterators.FilterIterator;
import org.apache.commons.collections.iterators.LoopingIterator;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author 周宁
 * @Date 2018-08-04 8:48
 */
public class IteratorTest {

    public static void main(String[] args) {
        System.out.println(StringUtils.center("iterator test start", 31, "="));
        String[] weekDays = {
                "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"
        };
        //这里的list只能由数组转化成
        List weekDayList = Arrays.asList(weekDays);
        System.out.println("部分weekDays数据");
        Iterator iterator1 = new ArrayListIterator(weekDays, 0, 5);
        printColl("Partial:", iterator1, 5);

        Iterator iterator2 = new LoopingIterator(weekDayList);
        printColl("loop", iterator2, 15);

        Predicate notWeekendPredicate = object -> {
            String str = (String) object;
            if ("Saturday".equalsIgnoreCase(str) || "Sunday".equalsIgnoreCase(str)) {
                return false;
            }
            return true;
        };
        Iterator iterator3 = new FilterIterator(new LoopingIterator(weekDayList), notWeekendPredicate);
        printColl("not weekends loop:", iterator3, 15);
        System.out.println(StringUtils.center("iterator test end", 31, "="));

    }

    public static void printColl(String label, Iterator iter, int maxCount) {
        if (StringUtils.isNotBlank(label)) {
            System.out.println(label);
        }
        int i = 0;
        while (iter.hasNext() && i < maxCount) {
            System.out.println("# " + iter.next() + " #");
            i++;
        }
    }

}
