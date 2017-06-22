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
 * 基于注解的定时器 
 * @author hj 
 */  
@Component  
public class MyTaskAnnotation {  
      
	//使用mongodb.cfg.properties中配置的数据库与集合，如未指定，使用MongoDBUtil中默认的数据库与集合
	MongoDBService mongoDBService1 = new MongoDBServiceImpl();
	private static Logger logger=Logger.getLogger(MyTaskAnnotation.class); 
    /**  
     * 定时计算。每天凌晨 01:00 执行一次  
     */    
    @Scheduled(cron = "0 0 1 * * *")   
    public void show(){  
        System.out.println("Annotation：is show run");  
    }
    public void insert(){
    	String dates=new SimpleDateFormat("yyyy-mm-dd H:mm:ss").format(new Date());
    	//数据一，包括用户名、密码，地址信息（省份、城市），爱好[…]
		BasicDBList dbList1 = new BasicDBList();
		dbList1.add("basketball");
		dbList1.add("music");
		dbList1.add("web");
		DBObject dbObject1 = new BasicDBObject("username","insert1")
			.append("age", 18).append("添加时间：", dates)
			.append("address", new BasicDBObject("province","广东").append("city", "广州"))
			.append("favourite", dbList1);
		mongoDBService1.insert(dbObject1);
		logger.info("spring task定时执行成功"+dates);
    }
      
    /**  
     * 心跳更新。启动时执行一次，之后每隔16秒执行一次  
     */    
    @Scheduled(fixedRate = 1000*16)   
    public void print(){  
        System.out.println("Annotation：print run");  
        insert();
    }  
}  