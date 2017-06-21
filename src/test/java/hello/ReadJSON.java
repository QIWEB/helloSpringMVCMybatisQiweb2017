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
            JsonParser parser=new JsonParser();  //����JSON������
            JsonObject object=(JsonObject) parser.parse(new FileReader("D:\\eclipse-workspace\\helloSpringMVCMybatisQiweb2017\\src\\test\\java\\hello\\test.json"));  //����JsonObject����
            System.out.println("cat="+object.get("cat").getAsString()); //��json����תΪΪString�͵�����
            System.out.println("pop="+object.get("pop").getAsBoolean()); //��json����תΪΪboolean�͵�����
            
            JsonArray array=object.get("language").getAsJsonArray();    //�õ�Ϊjson������
            for(int i=0;i<array.size();i++){
                System.out.println("---------------");
                JsonObject subObject=array.get(i).getAsJsonObject();
                System.out.println("id="+subObject.get("id").getAsInt());
                System.out.println("name="+subObject.get("name").getAsString());
                System.out.println("ide="+subObject.get("ide").getAsString());
            }
            System.out.println("����Ϊ�Բ�����");
            logger.info("------������־�ǲ��Ǽ�¼�ɹ���");
            //���ｫjson�ַ���ת����json�����õĲ���google��jar
            com.alibaba.fastjson.JSONObject jso=com.alibaba.fastjson.JSONObject.parseObject("{name:'zhangsan',age:25}");
            logger.info(String.format("json���ݣ�������%s���䣺%d",jso.get("name"),jso.get("age")));
            
            //json ����ת
            Person p =new Person("����",69);
            net.sf.json.JSONObject objjson=net.sf.json.JSONObject.fromObject(p);
            //objjson.get("name");
            logger.info(String.format("java����ת����json���ݣ�������%s���䣺%d",objjson.get("name"),objjson.get("age")));
            
            net.sf.json.JSONObject objjsonb=new net.sf.json.JSONObject();
            objjsonb.put("name", "lisi");
            
//            Person p2=(Person) net.sf.json.JSONObject.toBean(objjsonb);
//            System.out.println("==========name:"+p2.getName());
            
            String str=null;  
            str=String.format("Hi,%s", "����");  
            System.out.println(str);  
            /*str=String.format("Hi,%s:%s.%s", "����","����","����");            
            System.out.println(str);                           
            System.out.printf("��ĸa�Ĵ�д�ǣ�%c %n", 'A');  
            System.out.printf("3>7�Ľ���ǣ�%b %n", 3>7);  
            System.out.printf("100��һ���ǣ�%d %n", 100/2);  
            System.out.printf("100��16�������ǣ�%x %n", 100);  
            System.out.printf("100��8�������ǣ�%o %n", 100);  
            System.out.printf("50Ԫ�����8.5�ۿ��ǣ�%f Ԫ%n", 50*0.85);  
            System.out.printf("����۸��16�������ǣ�%a %n", 50*0.85);  
            System.out.printf("����۸��ָ����ʾ��%e %n", 50*0.85);  
            System.out.printf("����۸��ָ���͸���������ĳ��Ƚ϶̵��ǣ�%g %n", 50*0.85);  
            System.out.printf("������ۿ���%d%% %n", 85);  
            System.out.printf("��ĸA��ɢ�����ǣ�%h %n", 'A');  
            
            
            
		    //String str=null;  
		    //$ʹ��  
		    str=String.format("��ʽ����$��ʹ�ã�%1$d,%2$s", 99,"abc");             
		    System.out.println(str);                       
		    //+ʹ��  
		    System.out.printf("��ʾ�������ķ��ţ�%+d��%d%n", 99,-99);  
		    //��Oʹ��  
		    System.out.printf("��ţ�ı���ǣ�%03d%n", 7);  
		    //�ո�ʹ��  
		    System.out.printf("Tab����Ч���ǣ�% 8d%n", 7);  
		    //.ʹ��  
		    System.out.printf("���������Ч���ǣ�%,d%n", 9989997);  
		    //�ո��С����������  
		    System.out.printf("һ����ļ۸��ǣ�% 50.5fԪ%n", 49.8);  */
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}