package com.gysoft.utils.mongo.dao.impl;

import static org.springframework.data.mongodb.core.query.Query.query;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.gysoft.utils.mongo.dao.MongoBaseDao;
import com.gysoft.utils.mongo.util.MongoBaseEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MongoBaseDaoImpl<T extends MongoBaseEntity> implements MongoBaseDao<T> {

	@Resource(name = "mongoTemplate")
	protected MongoTemplate mongoTemplate;

	protected Class<T> clazz;

	@SuppressWarnings("unchecked")
	public MongoBaseDaoImpl() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) throws Exception {
		mongoTemplate.save(entity);
	}

	@Override
	public T findOne(Query query) throws Exception {
		return mongoTemplate.findOne(query, clazz);
	}

	@Override
	public List<T> findAll() throws Exception {
		return mongoTemplate.find(new Query(), clazz);
	}

	@Override
	public void delete(T entity) throws Exception {
		mongoTemplate.remove(entity);
	}

	@Override
	public boolean exists() throws Exception {
		return mongoTemplate.exists(new Query(), clazz);
	}

	@Override
	public T findAndModify(Update update, Criteria where) throws Exception {
		return mongoTemplate.findAndModify(query(where), update, clazz);
	}

	@Override
	public List<T> findAllBy(Criteria where) throws Exception {
		return mongoTemplate.find(query(where), clazz);
	}

	@Override
	public void delete(Criteria where) throws Exception {
		mongoTemplate.remove(query(where), clazz);
	}

	@Override
	public List<T> findAllBy(Criteria where, Pageable page) throws Exception {
		return findAllBy(query(where).with(page));
	}

	@Override
	public List<T> findAllBy(Criteria... criterias) throws Exception {
		return findAllBy(query(new Criteria().andOperator(criterias)));
	}

	@Override
	public List<T> findAllBy(Sort sort, Criteria... criterias) throws Exception {
		return findAllBy(query(new Criteria().andOperator(criterias)).with(sort));
	}

	@Override
	public List<T> findAllBy(Query query) throws Exception {
		return mongoTemplate.find(query, clazz);
	}

	@Override
	public T findOne(Criteria where) throws Exception {
		return mongoTemplate.findOne(query(where), clazz);
	}

	@Override
	public long count(Criteria where) throws Exception {
		return mongoTemplate.count(query(where), clazz);
	}

	@Override
	public int update(Update update, Criteria where) throws Exception {
		return mongoTemplate.updateFirst(query(where), update, clazz).getN();
	}

	@Override
	public void multiUpdate(Update update, Criteria where) throws Exception {
		mongoTemplate.updateMulti(query(where), update, clazz);
	}

	@Override
	public int bathUpdate(DBCollection dbCollection, String collName, List<BathUpdateOptions> options, boolean ordered)
			throws Exception {
		return doBathUpdate(dbCollection, collName, options, ordered);
	}

	@Override
	public int bathUpdate(DBCollection dbCollection, Class<?> entityClass, List<BathUpdateOptions> options,
			boolean ordered) throws Exception {
		return doBathUpdate(dbCollection, determineCollectionName(entityClass), options, ordered);
	}

	@Override
	public int bathUpdate(DBCollection dbCollection, String collName, List<BathUpdateOptions> options)
			throws Exception {
		return doBathUpdate(dbCollection, collName, options, true);
	}

	@Override
	public int bathUpdate(DBCollection dbCollection, Class<?> entityClass, List<BathUpdateOptions> options)
			throws Exception {
		return doBathUpdate(dbCollection, determineCollectionName(entityClass), options, true);
	}

	@Override
	public int bathUpdate(MongoTemplate mongoTemplate, String collName, List<BathUpdateOptions> options,
			boolean ordered) throws Exception {
		return doBathUpdate(mongoTemplate, collName, options, ordered);
	}

	@Override
	public int bathUpdate(MongoTemplate mongoTemplate, Class<?> entityClass, List<BathUpdateOptions> options,
			boolean ordered) throws Exception {
		String collectionName = determineCollectionName(entityClass);
		return doBathUpdate(mongoTemplate, collectionName, options, ordered);
	}

	@Override
	public int bathUpdate(MongoTemplate mongoTemplate, String collName, List<BathUpdateOptions> options)
			throws Exception {
		return doBathUpdate(mongoTemplate, collName, options, true);
	}

	@Override
	public int bathUpdate(MongoTemplate mongoTemplate, Class<?> entityClass, List<BathUpdateOptions> options)
			throws Exception {
		String collectionName = determineCollectionName(entityClass);
		return doBathUpdate(mongoTemplate, collectionName, options, true);
	}

	private static int doBathUpdate(MongoTemplate mongoTemplate, String collName, List<BathUpdateOptions> options,
			boolean ordered) throws Exception {
		// 1000条命令分批更新
		int spliteLength = 1000; // 分页长度
		int dataLength = options.size(); // 数据总长度
		int affectCount = 0; // 受影响的行数
		if (dataLength > spliteLength) {
			int allPartLength = dataLength / spliteLength; // 共分几页
			allPartLength = dataLength % spliteLength == 0 ? allPartLength : ++allPartLength;
			int index = 0; // 计数使用,防止下角标超界异常
			for (int i = 0; i < allPartLength; i++) {
				DBObject command = new BasicDBObject();
				command.put("update", collName);
				List<BasicDBObject> updateList = new ArrayList<BasicDBObject>();
				for (int j = 0; j < spliteLength && index < dataLength; j++) {
					BathUpdateOptions option = options.get(index++);
					BasicDBObject update = new BasicDBObject();
					update.put("q", option.getQuery().getQueryObject());
					update.put("u", option.getUpdate().getUpdateObject());
					update.put("upsert", option.isUpsert());
					update.put("multi", option.isMulti());
					updateList.add(update);
				}
				command.put("updates", updateList);
				command.put("ordered", ordered);
				CommandResult commandResult = mongoTemplate.getDb().command(command);
				affectCount += Integer.parseInt(commandResult.get("n").toString());
			}

		} else {
			DBObject command = new BasicDBObject();
			command.put("update", collName);
			List<BasicDBObject> updateList = new ArrayList<BasicDBObject>();
			for (BathUpdateOptions option : options) {
				BasicDBObject update = new BasicDBObject();
				update.put("q", option.getQuery().getQueryObject());
				update.put("u", option.getUpdate().getUpdateObject());
				update.put("upsert", option.isUpsert());
				update.put("multi", option.isMulti());
				updateList.add(update);
			}
			command.put("updates", updateList);
			command.put("ordered", ordered);
			CommandResult commandResult = mongoTemplate.getDb().command(command);
			affectCount = Integer.parseInt(commandResult.get("n").toString());
		}
		return affectCount;
	}

	private static int doBathUpdate(DBCollection dbCollection, String collName, List<BathUpdateOptions> options,
			boolean ordered) {
		// 1000条命令分批更新
		int spliteLength = 1000; // 分页长度
		int dataLength = options.size(); // 数据总长度
		int affectCount = 0; // 受影响的行数
		if (dataLength > spliteLength) {
			int allPartLength = dataLength / spliteLength; // 共分几页
			allPartLength = dataLength % spliteLength == 0 ? allPartLength : ++allPartLength;
			int index = 0; // 计数使用,防止下角标超界异常
			for (int i = 0; i < allPartLength; i++) {
				DBObject command = new BasicDBObject();
				command.put("update", collName);
				List<BasicDBObject> updateList = new ArrayList<BasicDBObject>();
				for (int j = 0; j < spliteLength && index < dataLength; j++) {
					BathUpdateOptions option = options.get(index++);
					BasicDBObject update = new BasicDBObject();
					update.put("q", option.getQuery().getQueryObject());
					update.put("u", option.getUpdate().getUpdateObject());
					update.put("upsert", option.isUpsert());
					update.put("multi", option.isMulti());
					updateList.add(update);
				}
				command.put("updates", updateList);
				command.put("ordered", ordered);
				CommandResult commandResult = dbCollection.getDB().command(command);
				affectCount += Integer.parseInt(commandResult.get("n").toString());
			}

		} else {
			DBObject command = new BasicDBObject();
			command.put("update", collName);
			List<BasicDBObject> updateList = new ArrayList<BasicDBObject>();
			for (BathUpdateOptions option : options) {
				BasicDBObject update = new BasicDBObject();
				update.put("q", option.getQuery().getQueryObject());
				update.put("u", option.getUpdate().getUpdateObject());
				update.put("upsert", option.isUpsert());
				update.put("multi", option.isMulti());
				updateList.add(update);
			}
			command.put("updates", updateList);
			command.put("ordered", ordered);
			CommandResult commandResult = dbCollection.getDB().command(command);
			affectCount = Integer.parseInt(commandResult.get("n").toString());
		}
		return affectCount;
	}

	private static String determineCollectionName(Class<?> entityClass) {
		if (entityClass == null) {
			throw new InvalidDataAccessApiUsageException(
					"No class parameter provided, entity collection can't be determined!");
		}
		String collName = entityClass.getSimpleName();
		if (entityClass.isAnnotationPresent(Document.class)) {
			Document document = entityClass.getAnnotation(Document.class);
			collName = document.collection();
		} else {
			collName = collName.replaceFirst(collName.substring(0, 1), collName.substring(0, 1).toLowerCase());
		}
		return collName;
	}

	public static class BathUpdateOptions {
		private Query query;
		private Update update;
		private boolean upsert = false; // 为true时，如果更新条件不符合则插入新的 文档,相反则不做更新操作
		private boolean multi = false; // 为true时，将更新符合条件的多条文档，相反只更新第一条

		public BathUpdateOptions() {
			super();
		}

		public BathUpdateOptions(Query query, Update update) {
			super();
			this.query = query;
			this.update = update;
		}

		public BathUpdateOptions(Query query, Update update, boolean upsert, boolean multi) {
			super();
			this.query = query;
			this.update = update;
			this.upsert = upsert;
			this.multi = multi;
		}

		public boolean isUpsert() {
			return upsert;
		}

		public void setUpsert(boolean upsert) {
			this.upsert = upsert;
		}

		public boolean isMulti() {
			return multi;
		}

		public void setMulti(boolean multi) {
			this.multi = multi;
		}

		public Query getQuery() {
			return query;
		}

		public void setQuery(Query query) {
			this.query = query;
		}

		public Update getUpdate() {
			return update;
		}

		public void setUpdate(Update update) {
			this.update = update;
		}

	}
}
