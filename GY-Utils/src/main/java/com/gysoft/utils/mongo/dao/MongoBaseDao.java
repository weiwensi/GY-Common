package com.gysoft.utils.mongo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.gysoft.utils.mongo.dao.impl.MongoBaseDaoImpl.BathUpdateOptions;
import com.gysoft.utils.mongo.util.MongoBaseEntity;
import com.mongodb.DBCollection;

/**
 * 
 * @Description:mongo封装基础操作
 * @author DJZ-PJJ
 * @date 2018年3月30日 上午9:31:39
 */
public interface MongoBaseDao<T extends MongoBaseEntity> {
	/**
	 * 
	 * @Description: 保存实体对象
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:32:42
	 * @param entity
	 * @throws Exception
	 */
	public void save(T entity) throws Exception; 
    /**
     * 
     * @Description: 查找第一个文档
     * @author DJZ-PJJ
     * @date 2018年3月30日 上午9:33:00
     * @return T
     * @throws Exception
     */
	public T findOne(Query query) throws Exception;       
    
	/**
	 * 
	 * @Description: 查找集合所有文档
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:33:44
	 * @return
	 * @throws Exception
	 */
	public List<T> findAll() throws Exception ;          
    
	/**
	 * 
	 * @Description: 删除一个实体
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:34:21
	 * @param entity
	 * @throws Exception
	 */
	public void delete(T entity) throws Exception;

	/**
	 * 
	 * @Description: 判断集合是否存在
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:34:58
	 * @return
	 * @throws Exception
	 */
	public boolean exists() throws Exception;
	
	/**
	 * @Description: 根据查询条件修改文档，并返回未执行修改之前查找到的文档对象
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:30:24
	 * @param update
	 * @param where
	 * @return T
	 * @throws Exception
	 */
	public T findAndModify(Update update, Criteria where) throws Exception;
	
	/**
	 * 
	 * @Description: 根据where条件查询文档集合
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:38:31
	 * @param where
	 * @return
	 * @throws Exception
	 */
	public List<T> findAllBy(Criteria where) throws Exception; 
	
	/**
	 * 
	 * @Description: 按照条件移除 
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:40:00
	 * @param where
	 * @throws Exception
	 */
	public void delete(Criteria where) throws Exception;

	/**
	 * 
	 * @Description: 分页条件查询 
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:41:53
	 * @param where
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<T> findAllBy(Criteria where, Pageable page) throws Exception;
	
	
	/**
	 * 
	 * @Description: 查询所有满足条件的文档，多个条件之间是and的逻辑
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:43:34
	 * @param criterias
	 * @return
	 * @throws Exception
	 */
    public List<T> findAllBy(Criteria... criterias) throws Exception;
    
    /**
     * 
     * @Description: 查询所有满足条件的文档，并按照sort参数对结果进行排序，多个条件之间是and的逻辑 
     * @author DJZ-PJJ
     * @date 2018年3月30日 上午9:44:23
     * @param sort
     * @param criterias
     * @return
     * @throws Exception
     */
    public List<T> findAllBy(Sort sort, Criteria... criterias) throws Exception;
    
    /**
     * 
     * @Description: 根据query对象查询文档
     * @author DJZ-PJJ
     * @date 2018年3月30日 上午9:45:15
     * @param query
     * @return
     */
    public List<T> findAllBy(Query query) throws Exception;
    
    /**
     * 
     * @Description: 条件查询一个
     * @author DJZ-PJJ
     * @date 2018年3月30日 上午9:47:07
     * @param where
     * @return
     */
    public T findOne(Criteria where) throws Exception;
	
    /**
     * 
     * @Description: 对满足条件的文档进行个数统计
     * @author DJZ-PJJ
     * @date 2018年3月30日 上午9:48:29
     * @param where
     * @return
     * @throws Exception
     */
    public long count(Criteria where) throws Exception;
    
    /**
     * 
     * @Description: 对满足条件的第一个文档执行修改
     * @author DJZ-PJJ
     * @date 2018年3月30日 上午9:51:22
     * @param update
     * @param where
     * @return
     * @throws Exception
     */
    public int update(Update update, Criteria where) throws Exception;
    
    /**
     * 
     * @Description: 对满足条件的所有文档执行修改
     * @author DJZ-PJJ
     * @date 2018年3月30日 上午9:52:14
     * @param update
     * @param where
     * @throws Exception
     */
    public void multiUpdate(Update update, Criteria where) throws Exception;
    
	/**
	 * 
	 * @Description: 批量执行更新操作
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:29:44
	 * @param dbCollection
	 * @param collName
	 * @param options
	 * @param ordered
	 * @return int
	 * @throws Exception
	 */
	public int bathUpdate(DBCollection dbCollection, String collName, List<BathUpdateOptions> options, boolean ordered) throws Exception;
	
	/**
	 * 
	 * @Description: 批量执行更新操作
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:35:37
	 * @param dbCollection
	 * @param entityClass
	 * @param options
	 * @param ordered
	 * @return
	 * @throws Exception
	 */
	public int bathUpdate(DBCollection dbCollection, Class<?> entityClass, List<BathUpdateOptions> options, boolean ordered) throws Exception;
	
	/**
	 * 
	 * @Description: 批量执行更新操作 
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:35:44
	 * @param dbCollection
	 * @param collName
	 * @param options
	 * @return
	 * @throws Exception
	 */
	public int bathUpdate(DBCollection dbCollection, String collName, List<BathUpdateOptions> options) throws Exception;
	
	/**
	 * 
	 * @Description: 批量执行更新操作
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午9:35:53
	 * @param dbCollection
	 * @param entityClass
	 * @param options
	 * @return
	 * @throws Exception
	 */
	public int bathUpdate(DBCollection dbCollection, Class<?> entityClass, List<BathUpdateOptions> options) throws Exception;
	
	/**
	 * 
	 * @Description: 批量执行更新操作
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午10:12:50
	 * @param mongoTemplate
	 * @param collName
	 * @param options
	 * @param ordered
	 * @return
	 * @throws Exception
	 */
	public  int bathUpdate(MongoTemplate mongoTemplate, String collName, List<BathUpdateOptions> options, boolean ordered) throws Exception;
	
	/**
	 * 
	 * @Description: 批量执行更新操作
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午10:13:02
	 * @param mongoTemplate
	 * @param entityClass
	 * @param options
	 * @param ordered
	 * @return
	 * @throws Exception
	 */
	public  int bathUpdate(MongoTemplate mongoTemplate, Class<?> entityClass, List<BathUpdateOptions> options, boolean ordered) throws Exception;
	
	/**
	 * 
	 * @Description: 批量执行更新操作 
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午10:13:29
	 * @param mongoTemplate
	 * @param collName
	 * @param options
	 * @return
	 * @throws Exception
	 */
	public  int bathUpdate(MongoTemplate mongoTemplate, String collName, List<BathUpdateOptions> options) throws Exception;
	
	/**
	 * 
	 * @Description: 批量执行更新操作
	 * @author DJZ-PJJ
	 * @date 2018年3月30日 上午10:13:36
	 * @param mongoTemplate
	 * @param entityClass
	 * @param options
	 * @return
	 * @throws Exception
	 */
	public  int bathUpdate(MongoTemplate mongoTemplate, Class<?> entityClass, List<BathUpdateOptions> options) throws Exception;
}
