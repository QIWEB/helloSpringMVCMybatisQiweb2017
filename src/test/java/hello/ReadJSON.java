package hello;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
 
public class ReadJSON {
	private static Logger logger = Logger.getLogger(ReadJSON.class);  
    public static void main(String args[]){
        try {
        	ReadJSON rj=new ReadJSON();
        	File f = new File(rj.getClass().getResource("/").getPath());
        	System.out.println(f); 
        	//D:\eclipse-workspace\helloSpringMVCMybatisQiweb2017\target\test-classes
        	//request.getSession().getServletContext().getRealPath("/index.html") ;
            JsonParser parser=new JsonParser();  //创建JSON解析器
            JsonObject object=(JsonObject) parser.parse(new FileReader("D:\\eclipse-workspace\\helloSpringMVCMybatisQiweb2017\\src\\test\\java\\hello\\test.json"));  //创建JsonObject对象
            System.out.println("cat="+object.get("cat").getAsString()); //将json数据转为为String型的数据
            System.out.println("pop="+object.get("pop").getAsBoolean()); //将json数据转为为boolean型的数据
            
            JsonArray array=object.get("language").getAsJsonArray();    //得到为json的数组
            for(int i=0;i<array.size();i++){
                System.out.println("---------------");
                JsonObject subObject=array.get(i).getAsJsonObject();
                System.out.println("id="+subObject.get("id").getAsInt());
                System.out.println("name="+subObject.get("name").getAsString());
                System.out.println("ide="+subObject.get("ide").getAsString());
            }
            System.out.println("下面为自测数据");
            logger.info("------看看日志是不是记录成功了");
            //这里将json字符串转换成json对象用的不是google的jar
            com.alibaba.fastjson.JSONObject jso=com.alibaba.fastjson.JSONObject.parseObject("{name:'zhangsan',age:25}");
            logger.info(String.format("json内容：姓名：%s年龄：%d",jso.get("name"),jso.get("age")));
            
            //json 对象互转
            Person p =new Person("王力",69);
            net.sf.json.JSONObject objjson=net.sf.json.JSONObject.fromObject(p);
            //objjson.get("name");
            logger.info(String.format("java对象转换成json内容：姓名：%s年龄：%d",objjson.get("name"),objjson.get("age")));
            
            net.sf.json.JSONObject objjsonb=new net.sf.json.JSONObject();
            objjsonb.put("name", "lisi");
            
//            Person p2=(Person) net.sf.json.JSONObject.toBean(objjsonb);
//            System.out.println("==========name:"+p2.getName());
            
            String str=null;  
            str=String.format("Hi,%s", "王力");  
            System.out.println(str);  
            /*str=String.format("Hi,%s:%s.%s", "王南","王力","王张");            
            System.out.println(str);                           
            System.out.printf("字母a的大写是：%c %n", 'A');  
            System.out.printf("3>7的结果是：%b %n", 3>7);  
            System.out.printf("100的一半是：%d %n", 100/2);  
            System.out.printf("100的16进制数是：%x %n", 100);  
            System.out.printf("100的8进制数是：%o %n", 100);  
            System.out.printf("50元的书打8.5折扣是：%f 元%n", 50*0.85);  
            System.out.printf("上面价格的16进制数是：%a %n", 50*0.85);  
            System.out.printf("上面价格的指数表示：%e %n", 50*0.85);  
            System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50*0.85);  
            System.out.printf("上面的折扣是%d%% %n", 85);  
            System.out.printf("字母A的散列码是：%h %n", 'A');  
            
            
            
		    //String str=null;  
		    //$使用  
		    str=String.format("格式参数$的使用：%1$d,%2$s", 99,"abc");             
		    System.out.println(str);                       
		    //+使用  
		    System.out.printf("显示正负数的符号：%+d与%d%n", 99,-99);  
		    //补O使用  
		    System.out.printf("最牛的编号是：%03d%n", 7);  
		    //空格使用  
		    System.out.printf("Tab键的效果是：% 8d%n", 7);  
		    //.使用  
		    System.out.printf("整数分组的效果是：%,d%n", 9989997);  
		    //空格和小数点后面个数  
		    System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);  */
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}