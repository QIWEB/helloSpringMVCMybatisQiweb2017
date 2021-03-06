package com.qiweb.mongodb.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class testMongoService {
	//聞喘mongodb.cfg.properties嶄塘崔議方象垂嚥鹿栽��泌隆峺協��聞喘MongoDBUtil嶄潮範議方象垂嚥鹿栽
	MongoDBService mongoDBService1 = new MongoDBServiceImpl();
	
	//霞編峨秘方象
	@Test
	public void testInsert(){
		//方象匯��淫凄喘薩兆、畜鷹��仇峽佚連��福芸、廓偏����握挫[´]
		BasicDBList dbList1 = new BasicDBList();
		dbList1.add("basketball");
		dbList1.add("music");
		dbList1.add("web");
		DBObject dbObject1 = new BasicDBObject("username","insert1")
			.append("age", 18)
			.append("address", new BasicDBObject("province","鴻叫").append("city", "鴻巒"))
			.append("favourite", dbList1);
		//方象屈
		BasicDBList dbList2 = new BasicDBList();
		dbList2.add("football");
		dbList2.add("music");
		DBObject dbObject2 = new BasicDBObject("username","insert2")
			.append("age", 18)
			.append("address", new BasicDBObject("province","病廉").append("city", "廉芦"))
			.append("favourite", dbList2);
		//方象眉
		BasicDBList dbList3 = new BasicDBList();
		dbList3.add("Linux");
		DBObject dbObject3 = new BasicDBObject("username","insert3")
			.append("age", 18)
			.append("address", new BasicDBObject("province","采臼").append("city", "隠協"))
			.append("favourite", dbList3);
		//方象膨
		BasicDBList dbList4 = new BasicDBList();
		dbList4.add("swim");
		dbList4.add("android");
		DBObject dbObject4 = new BasicDBObject("username","insert4")
			.append("age", 18)
			.append("address", new BasicDBObject("province","膨寒").append("city", "撹脅"))
			.append("favourite", dbList4);
		//方象励
		DBObject dbObject5 = new BasicDBObject("username", "insert5")
			.append("age", 28)
			.append("address", new BasicDBObject("city", "瑳巒"));
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
		System.out.println("！！！！！！！！！！！！！！！！！！insert collection！！！！！！！！！！！！！！！！！！");
		List<DBObject> list = new ArrayList<DBObject>();
		list.add(dbObject1);
		list.add(dbObject2);
		list.add(dbObject3);
		list.add(dbObject5);
		mongoDBService1.insertBatch(list);
		System.out.println("！！！！！！！！！！！！！！！！！！insert one！！！！！！！！！！！！！！！！！！");
		mongoDBService1.insert(dbObject4);
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
	}
	
	//霞編臥儂方象
	@Test
	public void testFind(){
		DBObject dbObject = new BasicDBObject("username","insert1");
		System.out.println("方楚��" + mongoDBService1.getCollectionCount());
		System.out.println("username=java議方象方楚��" + mongoDBService1.getCount(dbObject));
		System.out.println("！！！！！！！！！！！！！！！！！！find all！！！！！！！！！！！！！！！！！！");
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
		System.out.println("！！！！！！！！！！！！！！！！！！find obj！！！！！！！！！！！！！！！！！！");
		mongoDBService1.printListDBObj(mongoDBService1.find(dbObject));
		System.out.println("！！！！！！！！！！！！！！！！！！find sort！！！！！！！！！！！！！！！！！！");
		mongoDBService1.printListDBObj(mongoDBService1.find(new BasicDBObject(), new BasicDBObject("age", 1)));
		System.out.println("！！！！！！！！！！！！！！！！！！find sort limit！！！！！！！！！！！！！！！！！！");
		mongoDBService1.printListDBObj(mongoDBService1.find(new BasicDBObject(), new BasicDBObject("age", 1), 1, 2));
	}

	//霞編方象厚仟
	@Test
	public void testUpdate(){
		BasicDBObject newDocument = new BasicDBObject("$set",new BasicDBObject("age",11));
				
		BasicDBObject searchQuery = new BasicDBObject().append("username", "insert2");
	
		mongoDBService1.printListDBObj(mongoDBService1.find(searchQuery));
		System.out.println("！！！！！！！！！！！！！！！！！！update！！！！！！！！！！！！！！！！！！");
		mongoDBService1.update(newDocument, searchQuery);
		mongoDBService1.printListDBObj(mongoDBService1.find(searchQuery));
	}
	
	//霞編方象評茅
	@Test
	public void testDelete(){
		DBObject dbObject1 = new BasicDBObject("username", "insert1");
		DBObject dbObject2 = new BasicDBObject("username", "insert2");
		DBObject dbObject3 = new BasicDBObject("username", "insert3");
		DBObject dbObject4 = new BasicDBObject("username", "insert4");
		DBObject dbObject5 = new BasicDBObject("username", "insert5");
		List<DBObject> list = new ArrayList<DBObject>();
		list.add(dbObject1);
		list.add(dbObject2);
		list.add(dbObject3);
		list.add(dbObject4);
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
		System.out.println("！！！！！！！！！！！！！！！！！！delete list！！！！！！！！！！！！！！！！！！");
		mongoDBService1.deleteBatch(list);
		System.out.println("！！！！！！！！！！！！！！！！！！delete one！！！！！！！！！！！！！！！！！！");
		mongoDBService1.delete(dbObject5);
		//System.out.println("！！！！！！！！！！！！！！！！！！delete all！！！！！！！！！！！！！！！！！！");
		//mongoDBService1.delete(new BasicDBObject());
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
	}
}