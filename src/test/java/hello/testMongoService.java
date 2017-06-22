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
	//ʹ��mongodb.cfg.properties�����õ����ݿ��뼯�ϣ���δָ����ʹ��MongoDBUtil��Ĭ�ϵ����ݿ��뼯��
	MongoDBService mongoDBService1 = new MongoDBServiceImpl();
	
	@Test
	public void testInsertJson(){
		BasicDBList dblist=new BasicDBList();
		dblist.add("cat");
		dblist.add("language");
		dblist.add("pop");
		DBObject obj=new BasicDBObject("uname","qiweb").append("age",15)
				.append("language", new BasicDBObject("pro","�㶫").append("web", "xml"))
				.append("uu", dblist);
		mongoDBService1.insert(obj);
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
	}
	
	//���Բ�������
	@Test
	public void testInsert(){
		//����һ�������û��������룬��ַ��Ϣ��ʡ�ݡ����У�������[��]
		BasicDBList dbList1 = new BasicDBList();
		dbList1.add("basketball");
		dbList1.add("music");
		dbList1.add("web");
		DBObject dbObject1 = new BasicDBObject("username","insert1")
			.append("age", 18)
			.append("address", new BasicDBObject("province","�㶫").append("city", "����"))
			.append("favourite", dbList1);
		//���ݶ�
		BasicDBList dbList2 = new BasicDBList();
		dbList2.add("football");
		dbList2.add("music");
		DBObject dbObject2 = new BasicDBObject("username","insert2")
			.append("age", 18)
			.append("address", new BasicDBObject("province","����").append("city", "����"))
			.append("favourite", dbList2);
		//������
		BasicDBList dbList3 = new BasicDBList();
		dbList3.add("Linux");
		DBObject dbObject3 = new BasicDBObject("username","insert3")
			.append("age", 18)
			.append("address", new BasicDBObject("province","�ӱ�").append("city", "����"))
			.append("favourite", dbList3);
		//������
		BasicDBList dbList4 = new BasicDBList();
		dbList4.add("swim");
		dbList4.add("android");
		DBObject dbObject4 = new BasicDBObject("username","insert4")
			.append("age", 18)
			.append("address", new BasicDBObject("province","�Ĵ�").append("city", "�ɶ�"))
			.append("favourite", dbList4);
		//������
		DBObject dbObject5 = new BasicDBObject("username", "insert5")
			.append("age", 28)
			.append("address", new BasicDBObject("city", "����"));
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
		System.out.println("������������������������������������insert collection������������������������������������");
		List<DBObject> list = new ArrayList<DBObject>();
		list.add(dbObject1);
		list.add(dbObject2);
		list.add(dbObject3);
		list.add(dbObject5);
		mongoDBService1.insertBatch(list);
		System.out.println("������������������������������������insert one������������������������������������");
		mongoDBService1.insert(dbObject4);
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
	}
	
	//���Բ�ѯ����
	@Test
	public void testFind(){
		DBObject dbObject = new BasicDBObject("username","insert1");
		System.out.println("������" + mongoDBService1.getCollectionCount());
		System.out.println("username=java������������" + mongoDBService1.getCount(dbObject));
		System.out.println("������������������������������������find all������������������������������������");
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
		System.out.println("������������������������������������find obj������������������������������������");
		mongoDBService1.printListDBObj(mongoDBService1.find(dbObject));
		System.out.println("������������������������������������find sort������������������������������������");
		mongoDBService1.printListDBObj(mongoDBService1.find(new BasicDBObject(), new BasicDBObject("age", 1)));
		System.out.println("������������������������������������find sort limit������������������������������������");
		mongoDBService1.printListDBObj(mongoDBService1.find(new BasicDBObject(), new BasicDBObject("age", 1), 1, 2));
	}

	//�������ݸ���
	@Test
	public void testUpdate(){
		BasicDBObject newDocument = new BasicDBObject("$set",new BasicDBObject("age",11));
				
		BasicDBObject searchQuery = new BasicDBObject().append("username", "insert2");
	
		mongoDBService1.printListDBObj(mongoDBService1.find(searchQuery));
		System.out.println("������������������������������������update������������������������������������");
		mongoDBService1.update(newDocument, searchQuery);
		mongoDBService1.printListDBObj(mongoDBService1.find(searchQuery));
	}
	
	//��������ɾ��
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
		System.out.println("������������������������������������delete list������������������������������������");
		mongoDBService1.deleteBatch(list);
		System.out.println("������������������������������������delete one������������������������������������");
		mongoDBService1.delete(dbObject5);
		//System.out.println("������������������������������������delete all������������������������������������");
		//mongoDBService1.delete(new BasicDBObject());
		mongoDBService1.printListDBObj(mongoDBService1.findAll());
	}
	@After
	public void after(){
		System.out.println("ִ�к�==:");
	}
	@Before
	public void before(){
		System.out.println("ִ��ǰ==:"+new Date());
	}

}