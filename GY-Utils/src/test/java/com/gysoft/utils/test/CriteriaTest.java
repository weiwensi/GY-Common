package com.gysoft.utils.test;

import com.gysoft.bean.login.UserBasicInfo;
import com.gysoft.bean.page.Sort;
import com.gysoft.bean.utils.Pair;
import com.gysoft.utils.jdbc.annotation.Table;
import com.gysoft.utils.jdbc.bean.Criteria;
import com.gysoft.utils.jdbc.bean.CriteriaTree;
import com.gysoft.utils.jdbc.bean.Joins;
import com.gysoft.utils.jdbc.sqlhelp.SqlMakeTools;
import com.gysoft.utils.util.regex.RegexUtils;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 周宁
 * @Date 2018-05-04 15:06
 */
public class CriteriaTest {

    private String baseSql = "SELECT * FROM tb_test";

    @Test
    public void testQueryWithCriteria() {
        Criteria criteria = new Criteria();
        criteria.select("distinct(zhouning)");
//        criteria.in("set", new HashSet(Arrays.asList("1234567890","111111")));
//        criteria.in("password", Arrays.asList("1234567890","111111"));
        criteria.andCriteria(new Criteria().and("realName", "like", "%" + "周宁" + "%").or("userName", "in", Arrays.asList("zhou", "he")));
        criteria.orCriteria(new Criteria().where("ppid", "12305").and("special", "TJ").andCriteria(new Criteria().where("roleId", 123).and("pid", 1119).andCriteria(new Criteria().where("key", 123).orCriteria(new Criteria().where("hh", 3).or("mm", 4231)))));
        criteria.or("userName", "like", "%" + "zhouning" + "%")
                .andCriteria(new Criteria().and("realName", "like", "%" + "周宁" + "%").or("userName", "in", Arrays.asList("zhou", "he")))
                .notEqual("epid", 90001000).let("score", 60).isNotNull("constructId");
        criteria.andCriteria(new Criteria().lt("createTime", new Date()).in("productId", Arrays.asList(1, 2, 3, 4, 5, 6)))
                .andCriteria(new Criteria().lt("createTime", new Date()).or("createTime", new Date()).andCriteria(new Criteria().where("key", 12).in("name", Arrays.asList(1, 2, 3)))
                        .orCriteria(new Criteria().where("iinnerji", "我CA")));
        criteria.notIn("productNum", Arrays.asList("GY-008", "GY-009"));
        criteria.orderBy(new Sort("userName"), new Sort("createTime", "ASC"));
        criteria.groupBy("userName", "id");
        Pair<String, Object[]> pair = SqlMakeTools.doCriteria(criteria, new StringBuilder(baseSql));
        System.out.println(pair.getFirst());
        System.out.println(ArrayUtils.toString(pair.getSecond()));
    }

    @Test
    public void testQueryWithCriteriaLambda() {
        Criteria criteria = new Criteria();
        criteria.select(UserBasicInfo::getEpid, UserBasicInfo::getIp);
        criteria.where(UserBasicInfo::getEpid, 1);
        criteria.where(UserBasicInfo::getCurrentMills, ">", 2222);
        criteria.let(UserBasicInfo::getEpid, 1001);
        criteria.gt(UserBasicInfo::getEpid, 1002);
        criteria.gte(UserBasicInfo::getEpid, 1003);
        criteria.in(UserBasicInfo::getIp, Arrays.asList(1, 2, 3, 4));
        criteria.and(UserBasicInfo::getProductNum, "GY-008");
        criteria.or(UserBasicInfo::getProductNum, "GY-009");
        criteria.andCriteria(new Criteria().where(UserBasicInfo::getEpid, 9000100).or(UserBasicInfo::getIp, "like", 192)
                .orCriteria(new Criteria().where(UserBasicInfo::getUserName, "zhou").or(UserBasicInfo::getProductNum, "111")));
//        criteria.groupBy(UserBasicInfo::getEpid, UserBasicInfo::getProductNum);
//        criteria.orderBy(new Sort(UserBasicInfo::getUserName));
        Pair<String, Object[]> pair = SqlMakeTools.doCriteria(criteria, new StringBuilder(baseSql));
        System.out.println(pair.getFirst());
        System.out.println(ArrayUtils.toString(pair.getSecond()));
    }

    @Test
    public void testLambda() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM Table");
        Criteria criteria = new Criteria();
        criteria.update(Book::getName, "1");
        SqlMakeTools.doCriteria(criteria, sql);
    }

    @Data
    @Table(name = "tb_book")
    private class Book {
        private String name;
        private String num;
    }

    @Test
    public void testJoinWithCriteria() {
        String sd = null;
        Criteria criteria = new Criteria().select("t1.name", "t2.username").from(Book.class).as("t1")
                .rightJoin(new Joins().with(Book.class).as("t2").on("fds", "1sg2"))
                .and("sd", "in", Arrays.asList("sd1", "xg1")).gt("sdf", 12)
                .leftJoin(new Joins().with(Book.class).as("t3").on("pwd", "pwd")
                        .and("dx", "in", Arrays.asList(1, 2, 3, 4, 5)).on("fd13", "fdf")
                        .and("mmp", ">=", "sd").and("sd", ">=", "ssdfgh"))
                .innerJoin(new Joins().with(Book.class).as("t4").on("t4.f", "t1.f"))
                .andCriteria(new Criteria().where("k1", "v1").or("k2", "v2")).or("k3", "k5")
                .andIfAbsent("sds", RegexUtils.replaceEspStr(sd));
        Pair<String, Object[]> pair = SqlMakeTools.doCriteria(criteria, null);
        System.out.println(pair.getFirst());
        System.out.println(org.apache.commons.lang.ArrayUtils.toString(pair.getSecond()));
    }

    @Test
    public void testSubQuery() {
        Criteria sub0 = new Criteria();
        sub0.select("distinct(zhouning)");
//        criteria.in("set", new HashSet(Arrays.asList("1234567890","111111")));
//        criteria.in("password", Arrays.asList("1234567890","111111"));
        sub0.andCriteria(new Criteria().and("realName", "like", "%" + "周宁" + "%").or("userName", "in", Arrays.asList("zhou", "he")));
        sub0.orCriteria(new Criteria().where("ppid", "12305").and("special", "TJ").andCriteria(new Criteria().where("roleId", 123).and("pid", 1119).andCriteria(new Criteria().where("key", 123).orCriteria(new Criteria().where("hh", 3).or("mm", 4231)))));
        sub0.or("userName", "like", "%" + "zhouning" + "%")
                .andCriteria(new Criteria().and("realName", "like", "%" + "周宁" + "%").or("userName", "in", Arrays.asList("zhou", "he")))
                .notEqual("epid", 90001000).let("score", 60).isNotNull("constructId");
        sub0.andCriteria(new Criteria().lt("createTime", new Date()).in("productId", Arrays.asList(1, 2, 3, 4, 5, 6)))
                .andCriteria(new Criteria().lt("createTime", new Date()).or("createTime", new Date()).andCriteria(new Criteria().where("key", 12).in("name", Arrays.asList(1, 2, 3)))
                        .orCriteria(new Criteria().where("iinnerji", "我CA")));
        sub0.notIn("productNum", Arrays.asList("GY-008", "GY-009"));
        sub0.orderBy(new Sort("userName"), new Sort("createTime", "ASC"));
        sub0.groupBy("userName", "id");
        Criteria sub  = new Criteria().select("t1.name", "t2.username").from(Book.class).as("t1")
                .rightJoin(new Joins().with(Book.class).as("t2").on("fds", "1sg2"))
                .and("sd", "in", Arrays.asList("sd1", "xg1")).gt("sdf", 12)
                .leftJoin(new Joins().with(Book.class).as("t3").on("pwd", "pwd")
                        .and("dx", "in", Arrays.asList(1, 2, 3, 4, 5)).on("fd13", "fdf")
                        .and("mmp", ">=", "sd").and("sd", ">=", "ssdfgh"))
                .innerJoin(new Joins().with(Book.class).as("t4").on("t4.f", "t1.f"))
                .andCriteria(new Criteria().where("k1", "v1").or("k2", "v2")).or("k3", "k5");
        Criteria sub1 = new Criteria().select("t1.a,t1.b").from(Book.class).as("t1").where("t1.tid", 1).groupBy("t1.tid");
        Criteria sub2 = new Criteria().select("t2.f1").from(new Criteria().select("*").from(
                new Criteria().select("tb").from(Book.class).where("tb.id","in",Arrays.asList("id1","id2"))
        ).as("t2'").where("f1","v1")
                .innerJoin(new Joins().with(Book.class).as("t2''").on("t2'.id","t2''.id"))).as("t2").where("t2.roleId", 2);
        Criteria sub3 = new Criteria().select("sub3.us,sub3.as").from(new Criteria().select("sub3'.*").from(sub1,sub2).as("sub3'")).as("sub3").where("sub3.us","in",Arrays.asList("p1","p2")).orderBy(new Sort("sub3.su"));
        Criteria criteria = new Criteria().select("t3.k1,t3.k2").from(sub0,sub1,sub2,sub3).as("t3").rightJoin(new Joins()
                .with(Book.class).as("t4").on("t4.f2","t3.f3")).where("t3.id","vv");
        CriteriaTree criteriaTree = new CriteriaTree();
        Pair<String,Object[]> pair = SqlMakeTools.doCriteria(criteria,new StringBuilder(baseSql));
        criteriaTree.setId("0");
        criteriaTree.setParams(pair.getSecond());
        criteriaTree.setSql(pair.getFirst());
        criteriaTree.setChildCriteriaTree(new ArrayList<>());
        SqlMakeTools.buildCriteriaTree(criteria,criteriaTree);
        System.out.println(criteriaTree);
        System.out.println(SqlMakeTools.doSubCriteriaSql(criteriaTree,""));
        System.out.println(Arrays.toString(SqlMakeTools.doSubCriteriaParam(criteriaTree,new Object[]{})));

    }
}
