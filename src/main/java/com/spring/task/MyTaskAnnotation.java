package com.spring.task;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.qiweb.mongodb.test.MongoDBService;
import com.qiweb.mongodb.test.MongoDBServiceImpl;  
  
/** 
 * ����ע��Ķ�ʱ�� 
 * @author hj 
 */  
@Component  
public class MyTaskAnnotation {  
      
	//ʹ��mongodb.cfg.properties�����õ����ݿ��뼯�ϣ���δָ����ʹ��MongoDBUtil��Ĭ�ϵ����ݿ��뼯��
	MongoDBService mongoDBService1 = new MongoDBServiceImpl();
	private static Logger logger=Logger.getLogger(MyTaskAnnotation.class); 
    /**  
     * ��ʱ���㡣ÿ���賿 01:00 ִ��һ��  
     */    
    @Scheduled(cron = "0 0 1 * * *")   
    public void show(){  
        System.out.println("Annotation��is show run");  
    }
    public void insert(){
    	String dates=new SimpleDateFormat("yyyy-mm-dd H:mm:ss").format(new Date());
    	//����һ�������û��������룬��ַ��Ϣ��ʡ�ݡ����У�������[��]
		BasicDBList dbList1 = new BasicDBList();
		dbList1.add("basketball");
		dbList1.add("music");
		dbList1.add("web");
		DBObject dbObject1 = new BasicDBObject("username","insert1")
			.append("age", 18).append("���ʱ�䣺", dates)
			.append("address", new BasicDBObject("province","�㶫").append("city", "����"))
			.append("favourite", dbList1);
		mongoDBService1.insert(dbObject1);
		logger.info("spring task��ʱִ�гɹ�"+dates);
    }
      
    /**  
     * �������¡�����ʱִ��һ�Σ�֮��ÿ��16��ִ��һ��  
     */    
    @Scheduled(fixedRate = 1000*16)   
    public void print(){  
        System.out.println("Annotation��print run");  
        insert();
    }  
}  