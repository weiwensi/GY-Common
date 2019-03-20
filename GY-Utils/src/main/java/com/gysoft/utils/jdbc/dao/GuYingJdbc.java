package com.gysoft.utils.jdbc.dao;

import com.gysoft.bean.page.Page;
import com.gysoft.bean.page.PageResult;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

/**
 * JDBC封装类支持，原生sql操作。
 *
 * @author 彭佳佳
 * @data 2018年3月9日
 */
public interface GuYingJdbc {

    /**
     * sql添加并返回id
     *
     * @param sql
     * @return
     * @throws Exception
     */
    String insertForId(String sql) throws Exception;

    String insertForId(String sql, Object[] args) throws Exception;

    /**
     * sql批量添加返回执行成功数量
     *
     * @param sql
     * @param batchArgs
     * @return
     * @throws Exception
     */
    int batchInsert(String sql, List<Object[]> batchArgs) throws Exception;

    int batchInsert(String sql, List<Object[]> batchArgs, int[] types) throws Exception;

    int batchInsert(String sql, List<Object[]> batchArgs, int batchPageSize) throws Exception;

    int batchInsert(String sql, List<Object[]> batchArgs, int[] types, int batchPageSize) throws Exception;
    
    void batchUpdate(String sql, List<Object[]> batchArgs) throws Exception;

    void batchUpdate(String sql, List<Object[]> batchArgs, int batchPageSize) throws Exception;

    void batchUpdate(String sql, List<Object[]> batchArgs, int[] types) throws Exception;

    void batchUpdate(String sql, List<Object[]> batchArgs, int[] types, int batchPageSize) throws Exception;
    /**
     * sql批量更新操作
     *
     * @param sql
     * @param args
     * @return
     * @throws Exception
     */
    int update(String sql, Object[] args) throws Exception;

    /**
     * sql查询
     *
     * @param sql
     * @param rse
     * @return
     * @throws Exception
     */
    <T> T query(String sql, ResultSetExtractor<T> rse) throws Exception;

    <T> T query(String sql, Object[] args, ResultSetExtractor<T> rse) throws Exception;

    <T> List<T> query(String sql, RowMapper<T> rowMapper) throws Exception;

    <T> List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) throws Exception;

    <T> List<T> query(String sql, Class<T> elementType) throws Exception;

    <T> List<T> query(String sql, Object[] args, Class<T> elementType) throws Exception;

    /**
     * 可以像NamedParameterJdbcTemplate一样使用的方法
     * @author 周宁
     * @param sql
     * @param elementType
     * @param paramMap
     * @throws Exception
     * @version 1.0
     * @return
     */
    <T> List<T> query(String sql, Map<String,Object> paramMap, Class<T> elementType) throws Exception;
    /**
     * sql查询 Java原生对象类型，不支持自定义对象类型
     *
     * @param sql
     * @param requiredType
     * @return
     * @throws Exception
     */
    <T> T queryForObject(String sql, Class<T> requiredType) throws Exception;

    <T> T queryForObject(String sql, Object[] args, Class<T> requiredType) throws Exception;






    /**
     * 返回Map集合，可按照key自行循环组装对象
     *
     * @param sql
     * @param args
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> queryForList(String sql, Object[] args) throws Exception;
    /**
     * 分页查询
     *
     * @param page
     * @param sql
     * @param args
     * @param requiredType
     * @return PageResult<T>
     * @throws Exception
     * @author 周宁
     * @version 1.0
     */
    <T> PageResult<T> queryForPageResult(Page page, String sql, Object[] args, Class<T> requiredType) throws Exception;

    <T> PageResult<T> queryForPageResult(Page page, String sql, Map<String,Object> paramMap, Class<T> requiredType) throws Exception;
}
