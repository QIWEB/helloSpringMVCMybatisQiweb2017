package hello;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qiweb.mongodb.test.MongoDBService;
import com.qiweb.mongodb.test.MongoDBServiceImpl;

public class testMongoService {
	//Ê¹ÓÃmongodb.cfg.propertiesÖÐÅäÖÃµÄÊý¾Ý¿âÓë¼¯ºÏ£¬ÈçÎ´Ö¸¶¨£¬Ê¹ÓÃMongoDBUtilÖÐÄ¬ÈÏµÄÊý¾Ý¿âÓë¼¯ºÏ
	MongoDBService mongoDBService1 = new MongoDBServiceImpl();
	
	@Test
	public void testInsertJson(){
		BasicDBList dblist=new BasicDBList();
		dblist.add("cat");
		dblist.add("language");
		dblist.add("pop");
		DBObject obj=new BasicDBObject("uname","qiweb").append("age",15)
				.append("language", new BasicDBObject("pro","¹ã¶«").append("web", "xml"))
				.append("uu", dblist);
		mongoDBService1.insert(obj);
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
	}
	
	//²âÊÔ²åÈëÊý¾Ý
	@Test
	public void testInsert(){
		//Êý¾ÝÒ»£¬°üÀ¨ÓÃ»§Ãû¡¢ÃÜÂë£¬µØÖ·ÐÅÏ¢£¨Ê¡·Ý¡¢³ÇÊÐ£©£¬°®ºÃ[¡­]
		BasicDBList dbList1 = new BasicDBList();
		dbList1.add("basketball");
		dbList1.add("music");
		dbList1.add("web");
		DBObject dbObject1 = new BasicDBObject("username","insert1")
			.append("age", 18)
			.append("address", new BasicDBObject("province","¹ã¶«").append("city", "¹ãÖÝ"))
			.append("favourite", dbList1);
		//Êý¾Ý¶þ
		BasicDBList dbList2 = new BasicDBList();
		dbList2.add("football");
		dbList2.add("music");
		DBObject dbObject2 = new BasicDBObject("username","insert2")
			.append("age", 18)
			.append("address", new BasicDBObject("province","ÉÂÎ÷").append("city", "Î÷°²"))
			.append("favourite", dbList2);
		//Êý¾ÝÈý
		BasicDBList dbList3 = new BasicDBList();
		dbList3.add("Linux");
		DBObject dbObject3 = new BasicDBObject("username","insert3")
			.append("age", 18)
			.append("address", new BasicDBObject("province","ºÓ±±").append("city", "±£¶¨"))
			.append("favourite", dbList3);
		//Êý¾ÝËÄ
		BasicDBList dbList4 = new BasicDBList();
		dbList4.add("swim");
		dbList4.add("android");
		DBObject dbObject4 = new BasicDBObject("username","insert4")
			.append("age", 18)
			.append("address", new BasicDBObject("province","ËÄ´¨").append("city", "³É¶¼"))
			.append("favourite", dbList4);
		//Êý¾ÝÎå
		DBObject dbObject5 = new BasicDBObject("username", "insert5")
			.append("age", 28)
			.append("address", new BasicDBObject("city", "º¼ÖÝ"));
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
		System.out.println("¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªinsert collection¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª");
		List<DBObject> list = new ArrayList<DBObject>();
		list.add(dbObject1);
		list.add(dbObject2);
		list.add(dbObject3);
		list.add(dbObject5);
		mongoDBService1.insertBatch(list);
		System.out.println("¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªinsert one¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª");
		mongoDBService1.insert(dbObject4);
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
	}
	
	//²âÊÔ²éÑ¯Êý¾Ý
	@Test
	public void testFind(){
		DBObject dbObject = new BasicDBObject("username","insert1");
		System.out.println("ÊýÁ¿£º" + mongoDBService1.getCollectionCount());
		System.out.println("username=javaµÄÊý¾ÝÊýÁ¿£º" + mongoDBService1.getCount(dbObject));
		System.out.println("¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªfind all¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª");
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
		System.out.println("¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªfind obj¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª");
		mongoDBService1.printListDBObj(mongoDBService1.find(dbObject));
		System.out.println("¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªfind sort¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª");
		mongoDBService1.printListDBObj(mongoDBService1.find(new BasicDBObject(), new BasicDBObject("age", 1)));
		System.out.println("¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªfind sort limit¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª");
		mongoDBService1.printListDBObj(mongoDBService1.find(new BasicDBObject(), new BasicDBObject("age", 1), 1, 2));
	}

	//²âÊÔÊý¾Ý¸üÐÂ
	@Test
	public void testUpdate(){
		BasicDBObject newDocument = new BasicDBObject("$set",new BasicDBObject("age",11));
				
		BasicDBObject searchQuery = new BasicDBObject().append("username", "insert2");
	
		mongoDBService1.printListDBObj(mongoDBService1.find(searchQuery));
		System.out.println("¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªupdate¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª");
		mongoDBService1.update(newDocument, searchQuery);
		mongoDBService1.printListDBObj(mongoDBService1.find(searchQuery));
	}
	
	//²âÊÔÊý¾ÝÉ¾³ý
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
		System.out.println("¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªdelete list¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª");
		mongoDBService1.deleteBatch(list);
		System.out.println("¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªdelete one¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª");
		mongoDBService1.delete(dbObject5);
		//System.out.println("¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ªdelete all¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª");
		//mongoDBService1.delete(new BasicDBObject());
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
	}
	@After
	public void after(){
		System.out.println("Ö´ÐÐºó==:");
	}
	@Before
	public void before(){
		System.out.println("Ö´ÐÐÇ°==:"+new Date());
	}

}