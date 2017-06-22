package com.qiweb.mongodb.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoDBServiceImpl implements MongoDBService {
	private String dbName;
	private String collName;
	private DB db;
	
	//�вι��췽����ָ�����ݿ����뼯����
	public MongoDBServiceImpl(String dbName, String collName) {
		this.dbName = dbName;
		this.collName = collName;
		try {
			db = getDb();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	//�޲ι��췽�������������ļ����õ����ݿ�������ã���������ļ���û�������򷵻�Ĭ�����ݿ��������
	public MongoDBServiceImpl() {
		getDb();
	}
	/*
	 * ��ȡ���ݿ����3����������ȼ��Ӹߵ��ͣ���
     *1�����췽��ָ��2�������ļ�ָ��3��Ĭ�����ݿ�
	 *�����2��3��MongoDButil�����ã�
	 */
	public DB getDb() {
		if (this.db == null) {
			if (this.dbName == null) {
				this.db = MongoDBUtil.getDB();
			} else {
				this.db = MongoDBUtil.getDBByName(this.dbName);
			}
		}
		return this.db;
	}
	
	/*
	 * ��ȡ���϶���3����������ȼ��Ӹߵ��ͣ���
     *1�����췽��ָ��2�������ļ�ָ��3��Ĭ�����ݿ�
	 *�����2��3��MongoDButil�����ã�
	 */
	public DBCollection getCollection() {
		if(this.collName != null){
			return db.getCollection(this.collName);
		}
		else {
			return MongoDBUtil.getDBCollection();
		}
	}

	public DBObject map2Obj(Map<String, Object> map) {
		DBObject obj = new BasicDBObject();
		if (map.containsKey("class") && map.get("class") instanceof Class)
			map.remove("class");
		obj.putAll(map);
		return obj;
	}
	//��������
	public void insert(DBObject obj) {
		getCollection().insert(obj);
	}
	//�����������
	public void insertBatch(List<DBObject> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		List<DBObject> listDB = new ArrayList<DBObject>();
		for (int i = 0; i < list.size(); i++) {
			listDB.add(list.get(i));
		}
		getCollection().insert(listDB);
	}
	//ɾ������
	public void delete(DBObject obj) {
		getCollection().remove(obj);
	}
	//ɾ����������
	public void deleteBatch(List<DBObject> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			getCollection().remove(list.get(i));
		}
	}
	//��ȡ�����е���������
	public long getCollectionCount() {
		return getCollection().getCount();
	}
	//���ҷ�����������������
	public long getCount(DBObject obj) {
		if (obj != null)
			return getCollection().getCount(obj);
		return getCollectionCount();
	}
	//���ҷ�������������
	public List<DBObject> find(DBObject obj) {
		DBCursor cur = getCollection().find(obj);
		return DBCursor2list(cur);
	}
	
	//���ҷ������������ݲ�����
	@Override
	public List<DBObject> find(DBObject query, DBObject sort) {
		DBCursor cur;
		if (query != null) {
			cur = getCollection().find(query);
		} else {
			cur = getCollection().find();
		}
		if (sort != null) {
			cur.sort(sort);
		}
		return DBCursor2list(cur);
	}

	//���ҷ������������ݲ����򣬹涨���ݸ���
	@Override
	public List<DBObject> find(DBObject query, DBObject sort, int start,
			int limit) {
		DBCursor cur;
		if (query != null) {
			cur = getCollection().find(query);
		} else {
			cur = getCollection().find();
		}
		if (sort != null) {
			cur.sort(sort);
		}
		if (start == 0) {
			cur.batchSize(limit);
		} else {
			cur.skip(start).limit(limit);
		}
		return DBCursor2list(cur);
	}
	
	//��DBCursorת��Ϊlist<DBObject>
	private List<DBObject> DBCursor2list(DBCursor cur) {
		List<DBObject> list = new ArrayList<DBObject>();
		if (cur != null) {
			list = cur.toArray();
		}
		return list;
	}

	//��������
	public void update(DBObject setFields, DBObject whereFields) {
		getCollection().updateMulti(whereFields, setFields);
	}
	//��ѯ��������������
	public List<DBObject> findAll() {
		DBCursor cur = getCollection().find();
		List<DBObject> list = new ArrayList<DBObject>();
		if (cur != null) {
			list = cur.toArray();
		}
		return list;
	}

	//��ID��ȡ����
	public DBObject getById(String id) {
		DBObject obj = new BasicDBObject();
		obj.put("_id", new ObjectId(id));
		DBObject result = getCollection().findOne(obj);
		return result;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
		this.db = MongoDBUtil.getDBByName(this.dbName);
	}

	public String getCollName() {
		return collName;
	}

	public void setCollName(String collName) {
		this.collName = collName;
	}
	@Override
	public void printListDBObj(List<DBObject> list) {
		// TODO Auto-generated method stub
		for(DBObject dbObject: list){
			System.out.println(dbObject);
		}
	}

	

}