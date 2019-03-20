package com.gysoft.utils.test.commons.collections;

import com.gysoft.utils.test.commons.collections.bean.Applicant;
import com.gysoft.utils.test.commons.collections.bean.Employee;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.SwitchTransformer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 周宁
 * @Date 2018-08-03 14:16
 */
public class TransformerTest {

    public static void main(String[] args) {
        System.out.println(StringUtils.center("transformer test start", 32, "="));
        //日期转换容器
        Transformer<Long, String> timetranS = input -> new SimpleDateFormat("yyyy年MM月dd日").format(input);
        List<Long> times = new ArrayList() {{
            add(1533278660000L);
            add(1433278660000L);
        }};
        Collection<String> result = CollectionUtils.collect(times, timetranS);
        System.out.println(ArrayUtils.toString(result.toArray()));
        //复杂demo
        List<Applicant> applicants = new ArrayList() {
            {
                add(new Applicant("yhw", 31, "manager"));
                add(new Applicant("scf", 33, "boss"));
                add(new Applicant("zhouning", 25, "engineer"));

            }
        };
        //设定predicate
        Predicate isEngineer = o -> {
            Applicant app = (Applicant) o;
            return "engineer".equalsIgnoreCase(app.getApplyFor());
        };

        Predicate isManager = o -> {
            Applicant app = (Applicant) o;
            return "manager".equalsIgnoreCase(app.getApplyFor());
        };

        Predicate isBoss = o -> {
            Applicant app = (Applicant) o;
            return "boss".equalsIgnoreCase(app.getApplyFor());
        };
        Predicate[] checkApplyFor = new Predicate[]{
                isEngineer,
                isManager,
                isBoss
        };

        Transformer developerTransformer = obj -> {
            Applicant app = (Applicant) obj;
            return new Employee(
                    app.getName(), app.getAge(), new Date(), "E4", 2000
            );
        };
        Transformer testerTransformer = obj -> {
            Applicant app = (Applicant) obj;
            return new Employee(
                    app.getName(), app.getAge(), new Date(), "E5", 4000
            );
        };
        Transformer pmTransformer = obj -> {
            Applicant app = (Applicant) obj;
            return new Employee(
                    app.getName(), app.getAge(), new Date(), "E6", 5000
            );
        };
        Transformer[] transformers = new Transformer[]{
                developerTransformer,
                testerTransformer,
                pmTransformer
        };

        Transformer employTransformer = new SwitchTransformer(
                checkApplyFor, transformers, null
        );

        Collection employed = CollectionUtils.collect(applicants, employTransformer);
        // output
        System.out.println("Applicants: ");
        Iterator iter1 = applicants.iterator();
        while (iter1.hasNext()) {
            System.out.println(iter1.next());
        }
        System.out.println("Employed: ");
        Iterator iter2 = employed.iterator();
        while (iter2.hasNext()) {
            System.out.println(iter2.next());
        }
        System.out.println(StringUtils.repeat("=", 40));
    }
}
