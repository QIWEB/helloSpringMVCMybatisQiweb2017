package com.qiweb.mongodb.test;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public interface MongoDBService {
	public List<DBObject> find(DBObject query, DBObject sort);
	public List<DBObject> find(DBObject query, DBObject sort, int start,
			int limit) ;
	public void printListDBObj(List<DBObject> list) ;
	public void insertBatch(List<DBObject> list);
	public void insert(DBObject dbObject4);
	public List<DBObject> findAll();
	public long getCollectionCount();
	public long getCount(DBObject dbObject);
	public List<DBObject> find(DBObject dbObject);
	public void deleteBatch(List<DBObject> list);
	public void delete(DBObject dbObject5);
	public void update(DBObject setFields, DBObject whereFields);
}
