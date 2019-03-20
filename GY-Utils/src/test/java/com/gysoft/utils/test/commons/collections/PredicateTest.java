package com.gysoft.utils.test.commons.collections;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.collections.functors.InstanceofPredicate;
import org.apache.commons.collections.functors.NotNullPredicate;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 周宁
 * @Date 2018-08-03 13:52
 */
public class PredicateTest {

    public static void main(String[] args) {
        System.out.println(StringUtils.center("predicate test start", 31, "="));
        Predicate p1 = new InstanceofPredicate(String.class);
        Predicate p2 = NotNullPredicate.getInstance();
        Predicate p3 = object -> {
            String str = (String) object;
            return StringUtils.isAlphanumeric(str) && str.length() >= 6 && str.length() <= 10;
        };
        Predicate p4 = PredicateUtils.allPredicate(new Predicate[]{p1, p2, p3});
        String input = "ABCD1234";
        Object[] raw = new Object[]{
                "Is '",
                input,
                "' a valid input? ",
                BooleanUtils.toStringYesNo(p4.evaluate(input)),
                "."
        };
        System.out.println(ArrayUtils.toString(raw));
        System.out.println(StringUtils.center("predicate test end", 31, "="));
    }
}
