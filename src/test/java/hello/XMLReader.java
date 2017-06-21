package hello;
import java.io.File;  

import javax.xml.parsers.*;  

import org.w3c.dom.*;  

public class XMLReader {  
    /*  
    public static void reader(String readPath, JDBCConnector front, JDBCConnector rest){  
        //readPath = "F:/knowledgeExport";  
        File dir = new File(readPath);  
        File[] files = dir.listFiles();  
        for(int j=0;j<files.length;j++){  
            System.out.println((files[j].isDirectory()?"目录：":"文件：")+files[j].getName());  
            try {  
                //File f = new File("c:/work/project/xml/aaron.xml");   
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
                DocumentBuilder builder = factory.newDocumentBuilder();  
                Document doc = builder.parse(files[j]);   
                //NodeList nl = doc.getElementsByTagName("root");  
                String tableName = doc.getElementsByTagName("table_name").item(0).getFirstChild().getNodeValue();  
                //System.out.println("system_id:"+ doc.getElementsByTagName("system_id").item(0).getFirstChild().getNodeValue());   
                System.out.println("table_name:"+ doc.getElementsByTagName("table_name").item(0).getFirstChild().getNodeValue());   
                //NodeList dl = doc.getElementsByTagName("datalist");  
                //System.out.println("dl:"+dl.getLength());  
                NodeList data = doc.getElementsByTagName("data");  
                System.out.println("data-num:"+data.getLength());  
                  
                if("info_source_ general".equals(tableName)){  
                    //信源表不让改字段 暂时导入无法做 先放着  
                }else if("class_content".equals(tableName)){//classType=0  
                    yqpt_knowledge_class(doc, data, 0, front, rest);  
                }else if("class_person".equals(tableName)){//classType=1  
                    yqpt_knowledge_class(doc, data, 1, front, rest);  
                }else if("class_organ".equals(tableName)){//classType=2  
                    yqpt_knowledge_class(doc, data, 2, front, rest);  
                }else if("class_expert".equals(tableName)){//classType=6  
                    yqpt_knowledge_class(doc, data, 6, front, rest);  
                }else if("class_word".equals(tableName)){  
                    yqpt_knowledge_words(doc, data, front, rest);  
                }else if("personage_personage".equals(tableName)){  
                    yqpt_knowledge_personage(doc, data, front, rest);  
                }else if("personage_organ".equals(tableName)){  
                    yqpt_knowledge_organ(doc, data, front, rest);  
                }else if("personage_expert".equals(tableName)){  
                    yqpt_knowledge_professor(doc, data, front, rest);  
                }else if("date_service_work".equals(tableName)){//业务工作时间库   
                    knowledge_service_time(doc, data, front, rest);  
                }else if("date_time_node".equals(tableName)){//重要时间节点 历史事件  
                    yqpt_knowledge_event(doc, data, front, rest);  
                }else if("region_name".equals(tableName)){  
                    yqpt_knowledge_region(doc, data, front, rest);  
                }else if("sentiment_word".equals(tableName)){  
                    yqpt_knowledge_sentiment_word(doc, data, front, rest);  
                }else{  
                      
                }  
                  for (int i = 0; i < data.getLength(); i++) { 
                        System.out.println("system_id:"+ doc.getElementsByTagName("system_id").item(i).getFirstChild().getNodeValue());  
                        System.out.println("table_name:"+ doc.getElementsByTagName("table_name").item(i).getFirstChild().getNodeValue());  
                    }  
                  
                  
            } catch (Exception e) {  
                e.printStackTrace();  
            }   
        }  
    }  
      
    *//** 
     * <p>table knowledge_service_time</p> 
     * <p>业务工作时间库数据导入</p> 
     * <p>返回</p> 
     * @author Lingdong 
     * @since  2014-5-27 下午02:26:30 
     * @param doc 
     * @param data 
     * @param front 
     * @param rest 
     *//*  
    public static void knowledge_service_time(Document doc, NodeList data, JDBCConnector front, JDBCConnector rest){  
        //data_id,date,cycle,intro,range,prompt_start,source,u_time,u_user,u_type  
        //id,work_date,cycle,cycle_unit,describ,prompt_range,range_names,prompt_start,prompt_start_unit,state,source,create_time,create_user,modify_time,modify_user,u_type,system_id  
        for (int i = 0; i < data.getLength(); i++) {  
            String id = doc.getElementsByTagName("data_id").item(i).getFirstChild().getNodeValue();   
            String date = doc.getElementsByTagName("date").item(i).getFirstChild().getNodeValue();   
            String cycle = doc.getElementsByTagName("cycle").item(i).getFirstChild().getNodeValue();   
            String intro = doc.getElementsByTagName("intro").item(i).getFirstChild().getNodeValue();  
            String range = doc.getElementsByTagName("range").item(i).getFirstChild().getNodeValue();  
            String prompt_start = doc.getElementsByTagName("prompt_start").item(i).getFirstChild().getNodeValue();  
            String source = "";  
            if(doc.getElementsByTagName("source").item(i).hasChildNodes()){//判断节点是否为空  
                source = doc.getElementsByTagName("source").item(i).getFirstChild().getNodeValue();  
            }  
            String uTime = "null";  
            if(doc.getElementsByTagName("u_time").item(i).hasChildNodes()){//判断节点是否为空  
                uTime = doc.getElementsByTagName("u_time").item(i).getFirstChild().getNodeValue();  
            }  
            String userName = "null";  
            if(doc.getElementsByTagName("u_user").item(i).hasChildNodes()){//判断节点是否为空  
                userName = doc.getElementsByTagName("u_user").item(i).getFirstChild().getNodeValue();   
            }  
            String uType = doc.getElementsByTagName("u_type").item(i).getFirstChild().getNodeValue();   
            if("null".equals(uTime)||XString.isNullOrEmpty(uTime)){  
                uTime = DateUtil.getNowTime4String();//时间为空则用当前时间  
            }  
            String inserFrontSql = " INSERT INTO knowledge_service_time select null, '" + date + "','" + cycle   
            + "','','" + uTime + "', id, '" + uTime + "', id, " + id + " from user where name='" + userName + "' ";  
            if("null".equals(userName)||XString.isNullOrEmpty(userName)){  
                inserFrontSql = " INSERT INTO knowledge_service_time values (null, '" + date + "','" + cycle   
                + "','','" + uTime + "', null, '" + uTime + "', null, " + id + ") ";  
            }  
            String sql = "";  
            if("1".equals(uType)){//添加操作   
                //根据system_id先删除一下要导入的数据 防止重复   
                sql = " delete from knowledge_service_time where system_id=" + id;  
                front.updateBySql(sql);  
                sql = inserFrontSql;  
                front.updateBySql(sql);  
            }else if("2".equals(uType)){//修改操作  
                sql = " update knowledge_service_time set work_date='" + date + "',cycle='" + cycle   
                + "', modify_time='"   
                + uTime + "', modify_user=(select id from user where name='" + userName + "') where system_id=" + id;  
                front.updateBySql(sql);  
            }else{//删除操作  
                sql = " delete from knowledge_service_time where system_id=" + id;  
                front.updateBySql(sql);  
            }  
        }  
    }  
      
    *//** 
     * <p>table yqpt_knowledge_professor knowledge_professor</p> 
     * <p>专家库数据导入</p> 
     * <p>返回</p> 
     * @author Lingdong 
     * @since  2014-5-26 下午03:56:26 
     * @param doc 
     * @param data 
     * @param front 
     * @param rest 
     *//*  
    public static void yqpt_knowledge_professor(Document doc, NodeList data, JDBCConnector front, JDBCConnector rest){  
        //data_id,expert_name,class_id,organ,nationality,duty,research_field,viewpoint,blog,weibo,user_id,source,u_time,u_user,u_type  
        //id,name,class_id,class_names,organ,nationality,job,main_research,viewpoint,board_id,blog,weibo,state,source,create_time,flag,u_type,system_id  
        for (int i = 0; i < data.getLength(); i++) {  
            String id = doc.getElementsByTagName("data_id").item(i).getFirstChild().getNodeValue();   
            String expert_name = doc.getElementsByTagName("expert_name").item(i).getFirstChild().getNodeValue();   
            String class_id = doc.getElementsByTagName("class_id").item(i).getFirstChild().getNodeValue();   
            if(!XString.isNullOrEmpty(class_id)){//如果不为空 转换成YQ系统中需要的格式  
                class_id = "[" + class_id.replace(";", "],[") + "]";  
            }  
            String nationality = doc.getElementsByTagName("nationality").item(i).getFirstChild().getNodeValue();  
              
            String organ = "";  
            String duty = "";  
            String research_field = "";  
            String viewpoint = "";  
            String blog = "";  
            String weibo = "";  
            String user_id = "";  
            if(doc.getElementsByTagName("organ").item(i).hasChildNodes()){//判断节点是否为空  
                organ = doc.getElementsByTagName("organ").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("duty").item(i).hasChildNodes()){//判断节点是否为空  
                duty = doc.getElementsByTagName("duty").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("research_field").item(i).hasChildNodes()){//判断节点是否为空  
                research_field = doc.getElementsByTagName("research_field").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("viewpoint").item(i).hasChildNodes()){//判断节点是否为空  
                viewpoint = doc.getElementsByTagName("viewpoint").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("blog").item(i).hasChildNodes()){//判断节点是否为空  
                blog = doc.getElementsByTagName("blog").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("weibo").item(i).hasChildNodes()){//判断节点是否为空  
                weibo = doc.getElementsByTagName("weibo").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("user_id").item(i).hasChildNodes()){//判断节点是否为空  
                user_id = doc.getElementsByTagName("user_id").item(i).getFirstChild().getNodeValue();  
            }  
            String source = "";  
            if(doc.getElementsByTagName("source").item(i).hasChildNodes()){//判断节点是否为空  
                source = doc.getElementsByTagName("source").item(i).getFirstChild().getNodeValue();  
            }  
            String uTime = "null";  
            if(doc.getElementsByTagName("u_time").item(i).hasChildNodes()){//判断节点是否为空  
                uTime = doc.getElementsByTagName("u_time").item(i).getFirstChild().getNodeValue();  
            }  
            String userName = "null";  
            if(doc.getElementsByTagName("u_user").item(i).hasChildNodes()){//判断节点是否为空  
                userName = doc.getElementsByTagName("u_user").item(i).getFirstChild().getNodeValue();   
            }  
            String uType = doc.getElementsByTagName("u_type").item(i).getFirstChild().getNodeValue();   
            if("null".equals(uTime)||XString.isNullOrEmpty(uTime)){  
                uTime = DateUtil.getNowTime4String();//时间为空则用当前时间  
            }  
            String inserFrontSql = " INSERT INTO knowledge_professor select :id:, '" + expert_name + "','" + class_id   
            + "',''," + user_id + ",'" + uTime + "', id, '" + uTime + "', id, " + id + " from user where name='" + userName + "' ";  
            if("null".equals(userName)||XString.isNullOrEmpty(userName)){  
                inserFrontSql = " INSERT INTO knowledge_professor values (:id:, '" + expert_name + "','" + class_id   
                + "',''," + user_id + ",'" + uTime + "', null, '" + uTime + "', null, " + id + ") ";  
            }  
              
            String sql = "";  
            if("1".equals(uType)){//添加操作   
                //根据system_id先删除一下要导入的数据 防止重复   
                sql = " delete from yqpt_knowledge_professor where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_professor where system_id=" + id;  
                front.updateBySql(sql);  
                sql = " INSERT INTO yqpt_knowledge_professor VALUES(null, '" + expert_name + "','" + class_id + "','','"   
                    + organ + "','" + nationality + "','" + duty + "','" + research_field + "','" + viewpoint + "'," + user_id   
                    + ", '" +  blog + "','" +  weibo + "',0,'" + source + "','" + uTime + "',null,1," + id + ") ";  
                rest.updateBySql(sql);  
                int lastid = rest.findLastInsertId();//获取最后插入的id 入前台库表 id要rest和front同步  
                sql = inserFrontSql.replace(":id:", lastid+"");//替换为具体id  
                front.updateBySql(sql);  
                //data_id,expert_name,class_id,organ,nationality,duty,research_field,viewpoint,blog,weibo,user_id,source,u_time,u_user,u_type  
                //id,name,class_id,class_names,organ,nationality,job,main_research,viewpoint,board_id,blog,weibo,state,source,create_time,flag,u_type,system_id  
            }else if("2".equals(uType)){//修改操作  
                sql = " update yqpt_knowledge_professor set name='" + expert_name + "',class_id='" + class_id   
                            + "',organ='" + organ + "',job='" + duty + "',viewpoint='" + viewpoint   
                            + "',blog='" + blog + "',nationality='" + nationality + "',weibo='" + weibo   
                            + "',main_research='" + research_field + "',state=0, u_type=1,source='"   
                            + source + "' where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " update knowledge_professor set name='" + expert_name + "',class_id='" + class_id   
                + "',board_id=" + user_id + ", modify_time='"   
                + uTime + "', modify_user=(select id from user where name='" + userName + "') where system_id=" + id;  
                front.updateBySql(sql);  
            }else{//删除操作  
                sql = " delete from yqpt_knowledge_professor where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_professor where system_id=" + id;  
                front.updateBySql(sql);  
            }  
        }  
    }  
      
    *//** 
     * <p>table yqpt_knowledge_organ knowledge_organ</p> 
     * <p>机构库数据导入</p> 
     * <p>返回</p> 
     * @author Lingdong 
     * @since  2014-5-26 下午02:47:32 
     * @param doc 
     * @param data 
     * @param front 
     * @param rest 
     *//*  
    public static void yqpt_knowledge_organ(Document doc, NodeList data, JDBCConnector front, JDBCConnector rest){  
        //data_id,name,class_id,site_name,site_url,viewpoint,main_research,intro,nationality,importance,activity,source,u_time,u_user,u_type  
        for (int i = 0; i < data.getLength(); i++) {  
            String id = doc.getElementsByTagName("data_id").item(i).getFirstChild().getNodeValue();   
            String name = doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();   
            String class_id = doc.getElementsByTagName("class_id").item(i).getFirstChild().getNodeValue();   
            if(!XString.isNullOrEmpty(class_id)){//如果不为空 转换成YQ系统中需要的格式  
                class_id = "[" + class_id.replace(";", "],[") + "]";  
            }  
            String site_name = "";  
            String site_url = "";  
            String viewpoint = "";  
            String main_research = "";  
            String intro = "";  
            if(doc.getElementsByTagName("site_name").item(i).hasChildNodes()){//判断节点是否为空  
                site_name = doc.getElementsByTagName("site_name").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("site_url").item(i).hasChildNodes()){//判断节点是否为空  
                site_url = doc.getElementsByTagName("site_url").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("viewpoint").item(i).hasChildNodes()){//判断节点是否为空  
                viewpoint = doc.getElementsByTagName("viewpoint").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("main_research").item(i).hasChildNodes()){//判断节点是否为空  
                main_research = doc.getElementsByTagName("main_research").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("intro").item(i).hasChildNodes()){//判断节点是否为空  
                intro = doc.getElementsByTagName("intro").item(i).getFirstChild().getNodeValue();  
            }  
            String nationality = doc.getElementsByTagName("nationality").item(i).getFirstChild().getNodeValue();  
            String importance = doc.getElementsByTagName("importance").item(i).getFirstChild().getNodeValue();  
            String activity = doc.getElementsByTagName("activity").item(i).getFirstChild().getNodeValue();  
            String source = "";  
            if(doc.getElementsByTagName("source").item(i).hasChildNodes()){//判断节点是否为空  
                source = doc.getElementsByTagName("source").item(i).getFirstChild().getNodeValue();  
            }  
            String uTime = "null";  
            if(doc.getElementsByTagName("u_time").item(i).hasChildNodes()){//判断节点是否为空  
                uTime = doc.getElementsByTagName("u_time").item(i).getFirstChild().getNodeValue();  
            }  
            String userName = "null";  
            if(doc.getElementsByTagName("u_user").item(i).hasChildNodes()){//判断节点是否为空  
                userName = doc.getElementsByTagName("u_user").item(i).getFirstChild().getNodeValue();   
            }  
            String uType = doc.getElementsByTagName("u_type").item(i).getFirstChild().getNodeValue();   
            if("null".equals(uTime)||XString.isNullOrEmpty(uTime)){  
                uTime = DateUtil.getNowTime4String();//时间为空则用当前时间  
            }  
            String inserFrontSql = " INSERT INTO knowledge_organ select :id:, '" + name + "','" + class_id + "','"   
                + uTime + "', id, '" + uTime + "', id, " + id + " from user where name='" + userName + "' ";  
            if("null".equals(userName)||XString.isNullOrEmpty(userName)){  
                inserFrontSql = " INSERT INTO knowledge_organ values (:id:, '" + name + "','" + class_id + "','"   
                                + uTime + "', null, '" + uTime + "', null, " + id + ") ";  
            }  
              
            String sql = "";  
            if("1".equals(uType)){//添加操作   
                //根据system_id先删除一下要导入的数据 防止重复   
                sql = " delete from yqpt_knowledge_organ where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_organ where system_id=" + id;  
                front.updateBySql(sql);  
                sql = " INSERT INTO yqpt_knowledge_organ VALUES(null, '" + name + "','" + class_id + "','','"   
                    + intro + "','" + nationality + "','','" + main_research + "','" + viewpoint + "','" +  site_name  
                    + "','" +  site_url  
                    + "'," + importance + "," + activity + ",null,1,'" + source + "','" + uTime + "',1," + id + ") ";  
                rest.updateBySql(sql);  
                int lastid = rest.findLastInsertId();//获取最后插入的id 入前台库表 id要rest和front同步  
                sql = inserFrontSql.replace(":id:", lastid+"");//替换为具体id  
                front.updateBySql(sql);  
            }else if("2".equals(uType)){//修改操作  
                sql = " update yqpt_knowledge_organ set name='" + name + "',class_id='" + class_id   
                            + "',site_name='" + site_name + "',site_url='" + site_url + "',viewpoint='" + viewpoint   
                            + "',intro='" + intro + "',nationality='" + nationality + "',importance_degree=" + importance   
                            + ",active_degree=" + activity + ",main_research='" + main_research + "',state=0, u_type=1,source='"   
                            + source + "' where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " update knowledge_organ set name='" + name + "',class_ids='" + class_id + "', modify_time='"   
                + uTime + "', modify_user=(select id from user where name='" + userName + "') where system_id=" + id;  
                front.updateBySql(sql);  
            }else{//删除操作  
                sql = " delete from yqpt_knowledge_organ where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_organ where system_id=" + id;  
                front.updateBySql(sql);  
            }  
        }  
    }  
      
    *//** 
     * <p>table yqpt_knowledge_personage knowledge_personage</p> 
     * <p>人物库数据导入</p> 
     * <p>返回</p> 
     * @author Lingdong 
     * @since  2014-5-26 下午01:56:54 
     * @param doc 
     * @param data 
     * @param front 
     * @param rest 
     *//*  
    public static void yqpt_knowledge_personage(Document doc, NodeList data, JDBCConnector front, JDBCConnector rest){  
        //data_id,name,class_id,site_name,site_url,job,intro,nationality,importance,activity,source,u_time,u_user,u_type  
        for (int i = 0; i < data.getLength(); i++) {  
            String id = doc.getElementsByTagName("data_id").item(i).getFirstChild().getNodeValue();   
            String name = doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();   
            String class_id = doc.getElementsByTagName("class_id").item(i).getFirstChild().getNodeValue();   
            if(!XString.isNullOrEmpty(class_id)){//如果不为空 转换成YQ系统中需要的格式  
                class_id = "[" + class_id.replace(";", "],[") + "]";  
            }  
            String site_name = "";  
            String site_url = "";  
            String job = "";  
            String intro = "";  
            if(doc.getElementsByTagName("site_name").item(i).hasChildNodes()){//判断节点是否为空  
                site_name = doc.getElementsByTagName("site_name").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("site_url").item(i).hasChildNodes()){//判断节点是否为空  
                site_url = doc.getElementsByTagName("site_url").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("job").item(i).hasChildNodes()){//判断节点是否为空  
                job = doc.getElementsByTagName("job").item(i).getFirstChild().getNodeValue();  
            }  
            if(doc.getElementsByTagName("intro").item(i).hasChildNodes()){//判断节点是否为空  
                intro = doc.getElementsByTagName("intro").item(i).getFirstChild().getNodeValue();  
            }  
            String nationality = doc.getElementsByTagName("nationality").item(i).getFirstChild().getNodeValue();  
            String importance = doc.getElementsByTagName("importance").item(i).getFirstChild().getNodeValue();  
            String activity = doc.getElementsByTagName("activity").item(i).getFirstChild().getNodeValue();  
            String source = "";  
            if(doc.getElementsByTagName("source").item(i).hasChildNodes()){//判断节点是否为空  
                source = doc.getElementsByTagName("source").item(i).getFirstChild().getNodeValue();  
            }  
            String uTime = "null";  
            if(doc.getElementsByTagName("u_time").item(i).hasChildNodes()){//判断节点是否为空  
                uTime = doc.getElementsByTagName("u_time").item(i).getFirstChild().getNodeValue();  
            }  
            String userName = "null";  
            if(doc.getElementsByTagName("u_user").item(i).hasChildNodes()){//判断节点是否为空  
                userName = doc.getElementsByTagName("u_user").item(i).getFirstChild().getNodeValue();   
            }  
            String uType = doc.getElementsByTagName("u_type").item(i).getFirstChild().getNodeValue();   
            if("null".equals(uTime)||XString.isNullOrEmpty(uTime)){  
                uTime = DateUtil.getNowTime4String();//时间为空则用当前时间  
            }  
            String inserFrontSql = " INSERT INTO knowledge_personage select :id:, '" + name + "','" + class_id + "','"   
                + uTime + "', id, '" + uTime + "', id, " + id + " from user where name='" + userName + "' ";  
            if("null".equals(userName)||XString.isNullOrEmpty(userName)){  
                inserFrontSql = " INSERT INTO knowledge_personage values (:id:, '" + name + "','" + class_id + "','"   
                                + uTime + "', null, '" + uTime + "', null, " + id + ") ";  
            }  
              
            String sql = "";  
            if("1".equals(uType)){//添加操作   
                //根据system_id先删除一下要导入的数据 防止重复   
                sql = " delete from yqpt_knowledge_personage where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_personage where system_id=" + id;  
                front.updateBySql(sql);  
                sql = " INSERT INTO yqpt_knowledge_personage VALUES(null, '" + class_id + "','', '" + name + "','"   
                    + site_name + "','" + site_url + "','" + job + "','" + intro + "','" + nationality   
                    + "'," + importance + "," + activity + ",0,'" + source + "','" + uTime + "',1," + id + ") ";  
                rest.updateBySql(sql);  
                int lastid = rest.findLastInsertId();//获取最后插入的id 入前台库表 id要rest和front同步  
                sql = inserFrontSql.replace(":id:", lastid+"");//替换为具体id  
                front.updateBySql(sql);  
            }else if("2".equals(uType)){//修改操作  
                sql = " update yqpt_knowledge_personage set person_name='" + name + "',class_ids='" + class_id   
                            + "',site_name='" + site_name + "',site_url='" + site_url + "',job='" + job   
                            + "',intro='" + intro + "',nationality='" + nationality + "',import_degree=" + importance   
                            + ",active_degree=" + activity + ",state=0, u_type=1,source='"   
                            + source + "' where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " update knowledge_personage set person_name='" + name + "',class_ids='" + class_id + "', modify_time='"   
                + uTime + "', modify_user=(select id from user where name='" + userName + "') where system_id=" + id;  
                front.updateBySql(sql);  
            }else{//删除操作  
                sql = " delete from yqpt_knowledge_personage where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_personage where system_id=" + id;  
                front.updateBySql(sql);  
            }  
        }  
    }  
      
    *//** 
     * <p>table yqpt_knowledge_sentiment_word</p> 
     * <p>情感词库数据导入</p> 
     * <p>返回</p> 
     * @author Lingdong 
     * @since  2014-5-26 上午11:12:18 
     * @param doc 
     * @param data 
     * @param front 
     * @param rest 
     *//*  
    public static void yqpt_knowledge_sentiment_word(Document doc, NodeList data, JDBCConnector front, JDBCConnector rest){  
        //data_id,word,word_prop,frequency,source,u_time,u_user,u_type,  
        for (int i = 0; i < data.getLength(); i++) {  
            String id = doc.getElementsByTagName("data_id").item(i).getFirstChild().getNodeValue();   
            String word = doc.getElementsByTagName("word").item(i).getFirstChild().getNodeValue();   
            String word_prop = doc.getElementsByTagName("word_prop").item(i).getFirstChild().getNodeValue();   
            String frequency = doc.getElementsByTagName("frequency").item(i).getFirstChild().getNodeValue();  
              
            String uType = doc.getElementsByTagName("u_type").item(i).getFirstChild().getNodeValue();   
            String source = "";  
            if(doc.getElementsByTagName("source").item(i).hasChildNodes()){//判断节点是否为空  
                source = doc.getElementsByTagName("source").item(i).getFirstChild().getNodeValue();  
            }  
              
            String uTime = "null";  
            if(doc.getElementsByTagName("u_time").item(i).hasChildNodes()){//判断节点是否为空  
                uTime = doc.getElementsByTagName("u_time").item(i).getFirstChild().getNodeValue();  
            }  
            String userName = "null"; 
            if(doc.getElementsByTagName("u_user").item(i).hasChildNodes()){//判断节点是否为空 
                userName = doc.getElementsByTagName("u_user").item(i).getFirstChild().getNodeValue();  
            }  
            if("null".equals(uTime)||XString.isNullOrEmpty(uTime)){  
                uTime = DateUtil.getNowTime4String();//时间为空则用当前时间  
            }  
              
            String sql = "";  
            if("1".equals(uType)){//添加操作   
                //根据system_id先删除一下要导入的数据 防止重复   
                sql = " delete from yqpt_knowledge_sentiment_word where system_id=" + id;  
                rest.updateBySql(sql);  
                  
                sql = " delete from yqpt_knowledge_sentiment_word where system_id=" + id + " or word='" + word + "' ";  
                rest.updateBySql(sql);  
                sql = " INSERT INTO yqpt_knowledge_sentiment_word VALUES(null, '" + word + "', '" + word_prop   
                    + "'," + frequency + ",1,'" + source + "','" + uTime + "',1," + id + ") ";  
                rest.updateBySql(sql);  
            }else if("2".equals(uType)){//修改操作  
                sql = " update yqpt_knowledge_sentiment_word set word='" + word + "',word_prop='" + word_prop   
                            + "',frequency=" + frequency + ",source='"   
                            + source + "', u_type=1 where system_id=" + id;  
                rest.updateBySql(sql);  
            }else{//删除操作  
                sql = " delete from yqpt_knowledge_sentiment_word where system_id=" + id;  
                rest.updateBySql(sql);  
            }  
        }  
    }  
      
    *//** 
     * <p>table yqpt_knowledge_region knowledge_region</p> 
     * <p>地名库 数据导入</p> 
     * <p>返回</p> 
     * @author Lingdong 
     * @since  2014-5-23 下午04:17:58 
     * @param doc 
     * @param data 
     * @param front 
     * @param rest 
     *//*  
    public static void yqpt_knowledge_region(Document doc, NodeList data, JDBCConnector front, JDBCConnector rest){  
        for (int i = 0; i < data.getLength(); i++) {  
            String id = doc.getElementsByTagName("data_id").item(i).getFirstChild().getNodeValue();   
            String name = doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();   
            String province = doc.getElementsByTagName("province").item(i).getFirstChild().getNodeValue();   
            String country = doc.getElementsByTagName("country").item(i).getFirstChild().getNodeValue();  
            String frequency = doc.getElementsByTagName("frequency").item(i).getFirstChild().getNodeValue();  
            String source = "";  
            if(doc.getElementsByTagName("source").item(i).hasChildNodes()){//判断节点是否为空  
                source = doc.getElementsByTagName("source").item(i).getFirstChild().getNodeValue();  
            }  
              
            String uTime = "null";  
            if(doc.getElementsByTagName("u_time").item(i).hasChildNodes()){//判断节点是否为空  
                uTime = doc.getElementsByTagName("u_time").item(i).getFirstChild().getNodeValue();  
            }  
            String userName = "null";  
            if(doc.getElementsByTagName("u_user").item(i).hasChildNodes()){//判断节点是否为空  
                userName = doc.getElementsByTagName("u_user").item(i).getFirstChild().getNodeValue();   
            }  
            String uType = doc.getElementsByTagName("u_type").item(i).getFirstChild().getNodeValue();   
            if("null".equals(uTime)||XString.isNullOrEmpty(uTime)){  
                uTime = DateUtil.getNowTime4String();//时间为空则用当前时间  
            }  
            String inserFrontSql = " INSERT INTO knowledge_region select :id:, '" + name + "',0,'" + uTime   
                                + "', id, '" + uTime + "', id, " + id + " from user where name='" + userName + "' ";  
            if("null".equals(userName)||XString.isNullOrEmpty(userName)){  
                inserFrontSql = " INSERT INTO knowledge_words values (:id:, '" + name + "',0,'"   
                                + uTime + "', null, '" + uTime + "', null, " + id + ") ";  
            }  
              
            String sql = "";  
            if("1".equals(uType)){//添加操作 根据system_id或name先删除一下要导入的数据 防止重复   
                sql = " delete from yqpt_knowledge_region where system_id=" + id + " or name='" + name + "' ";  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_region where name='" + name + "' ";  
                front.updateBySql(sql);  
                sql = " INSERT INTO yqpt_knowledge_region VALUES(null, '" + name + "', '" + province   
                    + "','" + country + "'," + frequency + ",0,'" + source + "',0,'" + uTime + "',1," + id + ") ";  
                rest.updateBySql(sql);  
                int lastid = rest.findLastInsertId();//获取最后插入的id 入前台库表 id要rest和front同步  
                sql = inserFrontSql.replace(":id:", lastid+"");//替换为具体id  
                front.updateBySql(sql);  
            }else if("2".equals(uType)){//修改操作  
                sql = " update yqpt_knowledge_region set name='" + name + "',province='" + province   
                            + "',country='" + country + "',frequency=" + frequency + ",source='"   
                            + source + "', u_type=1 where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " update knowledge_region set name='" + name + "', modify_time='" + uTime   
                    + "', modify_user=(select id from user where name='" + userName + "') where system_id=" + id;  
                front.updateBySql(sql);  
            }else{//删除操作  
                sql = " delete from yqpt_knowledge_region where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_region where system_id=" + id;  
                front.updateBySql(sql);  
            }  
        }  
    }  
      
    *//** 
     * <p>yqpt_knowledge_class表导入 (分类体系的几个表，以classType区分)</p> 
     * <p>返回</p> 
     * @author Lingdong 
     * @since  2014-5-22 上午09:40:23 
     * @param doc 
     * @param data 
     * @param classType 
     * @param front 
     * @param rest 
     *//*  
    public static void yqpt_knowledge_class(Document doc, NodeList data, Integer classType, JDBCConnector front, JDBCConnector rest){  
        for (int i = 0; i < data.getLength(); i++) {  
            String id = doc.getElementsByTagName("data_id").item(i).getFirstChild().getNodeValue();   
            String name = doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();   
            //doc.getElementsByTagName("source").item(i).getFirstChild().getNodeValue();   
            String uType = doc.getElementsByTagName("u_type").item(i).getFirstChild().getNodeValue();   
            //doc.getElementsByTagName("u_user").item(i).getFirstChild().getNodeValue();   
            String uTime = "null";  
            if(doc.getElementsByTagName("u_time").item(i).hasChildNodes()){//判断节点是否为空  
                uTime = doc.getElementsByTagName("u_time").item(i).getFirstChild().getNodeValue();  
            }  
            if("null".equals(uTime)||XString.isNullOrEmpty(uTime)){  
                uTime = DateUtil.getNowTime4String();  
            }  
            String level = "1";  
            String parent_id = "0";  
            if(classType==0){  
                level = doc.getElementsByTagName("level").item(i).getFirstChild().getNodeValue();   
                parent_id = doc.getElementsByTagName("parent_id").item(i).getFirstChild().getNodeValue();   
            }  
            if("1".equals(uType)){  
                //根据system_id先删除一下要导入的数据 防止重复   
                String sql = " delete from yqpt_knowledge_class where system_id=" + id;  
                rest.updateBySql(sql);  
                  
                sql = " INSERT INTO yqpt_knowledge_class VALUES(null, '" + name + "', " + level + ", "   
                            + parent_id + ", " + classType + ", 1, '" + uTime + "'," + id + ") ";  
                rest.updateBySql(sql);  
            }else if("2".equals(uType)){  
                String sql = " update yqpt_knowledge_class set name='" + name + "',level=" + level   
                            + ",parent_id=" + parent_id + " where system_id=" + id;  
                rest.updateBySql(sql);  
            }else{  
                String sql = " delete from yqpt_knowledge_class where system_id=" + id;  
                rest.updateBySql(sql);  
            }  
              
        }  
    }  
      
    *//** 
     * <p>table yqpt_knowledge_words knowledge_words</p> 
     * <p>内容分类词库导入</p> 
     * <p>返回</p> 
     * @author Lingdong 
     * @since  2014-5-22 上午11:03:20 
     * @param doc 
     * @param data 
     * @param front 
     * @param rest 
     *//*  
    public static void yqpt_knowledge_words(Document doc, NodeList data, JDBCConnector front, JDBCConnector rest){  
        for (int i = 0; i < data.getLength(); i++) {  
            String id = doc.getElementsByTagName("data_id").item(i).getFirstChild().getNodeValue();   
            String word = doc.getElementsByTagName("word").item(i).getFirstChild().getNodeValue();   
            String word_prop = doc.getElementsByTagName("word_prop").item(i).getFirstChild().getNodeValue();   
            String class_id = doc.getElementsByTagName("class_id").item(i).getFirstChild().getNodeValue();  
            //doc.getElementsByTagName("source").item(i).getFirstChild().getNodeValue();   
            String uType = doc.getElementsByTagName("u_type").item(i).getFirstChild().getNodeValue();   
            String uTime = "null";  
            if(doc.getElementsByTagName("u_time").item(i).hasChildNodes()){//判断节点是否为空  
                uTime = doc.getElementsByTagName("u_time").item(i).getFirstChild().getNodeValue();  
            }  
            String userName = "null";  
            if(doc.getElementsByTagName("u_user").item(i).hasChildNodes()){//判断节点是否为空  
                userName = doc.getElementsByTagName("u_user").item(i).getFirstChild().getNodeValue();   
            }  
            if("null".equals(uTime)||XString.isNullOrEmpty(uTime)){  
                uTime = DateUtil.getNowTime4String();  
            }  
            String inserFrontSql = " INSERT INTO knowledge_words select :id:, '" + word + "','" + uTime   
                                + "', id, '" + uTime + "', id," + id + " from user where name='" + userName + "' ";  
            if("null".equals(userName)||XString.isNullOrEmpty(userName)){  
                inserFrontSql = " INSERT INTO knowledge_words values (:id:, '" + word + "','"   
                                + uTime + "', null, '" + uTime + "', null," + id + ") ";  
            }  
              
            String sql = "";  
            if("1".equals(uType)){  
                //根据system_id先删除一下要导入的数据 防止重复   
                sql = " delete from yqpt_knowledge_words where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_words where system_id=" + id;  
                front.updateBySql(sql);  
                sql = " INSERT INTO yqpt_knowledge_words VALUES(null, '" + word + "', '" + word_prop   
                            + "',null, null, " + class_id + ", 1, '" + uTime + "'," + id + ") ";  
                rest.updateBySql(sql);  
                int lastid = rest.findLastInsertId();//获取最后插入的id 入前台库表 id要rest和front同步  
                sql = inserFrontSql.replace(":id:", lastid+"");//替换为具体id  
                front.updateBySql(sql);  
            }else if("2".equals(uType)){  
                sql = " update yqpt_knowledge_words set word='" + word + "',word_prop=" + word_prop   
                            + ",level3_class=" + class_id + " where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " update knowledge_words set word='" + word + "', modify_time='" + uTime   
                    + "', modify_user=(select id from user where name='" + userName + "') where system_id=" + id;  
                front.updateBySql(sql);  
            }else{  
                sql = " delete from yqpt_knowledge_words where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_words where system_id=" + id;  
                front.updateBySql(sql);  
            }  
        }  
    }  
      
    *//** 
     * <p>table yqpt_knowledge_event knowledge_event</p> 
     * <p>重要时间节点库 历史事件</p> 
     * <p>返回</p> 
     * @author Lingdong 
     * @since  2014-5-22 下午04:03:19 
     * @param doc 
     * @param data 
     * @param front 
     * @param rest 
     *//*  
    public static void yqpt_knowledge_event(Document doc, NodeList data, JDBCConnector front, JDBCConnector rest){  
        for (int i = 0; i < data.getLength(); i++) {  
            String id = doc.getElementsByTagName("data_id").item(i).getFirstChild().getNodeValue();   
            String date = doc.getElementsByTagName("date").item(i).getFirstChild().getNodeValue();   
            String cycle = doc.getElementsByTagName("cycle").item(i).getFirstChild().getNodeValue();   
            String class_id = doc.getElementsByTagName("class_id").item(i).getFirstChild().getNodeValue();  
            String name = doc.getElementsByTagName("name").item(i).getFirstChild().getNodeValue();  
            String intro = doc.getElementsByTagName("intro").item(i).getFirstChild().getNodeValue();  
            String prompt_start = doc.getElementsByTagName("prompt_start").item(i).getFirstChild().getNodeValue();  
            String prompt_end = doc.getElementsByTagName("prompt_end").item(i).getFirstChild().getNodeValue();  
            //String source = doc.getElementsByTagName("source").item(i).getFirstChild().getNodeValue();   
            String uType = doc.getElementsByTagName("u_type").item(i).getFirstChild().getNodeValue();   
            //String uTime = doc.getElementsByTagName("u_time").item(i).getFirstChild().getNodeValue();   
            //String userName = doc.getElementsByTagName("u_user").item(i).getFirstChild().getNodeValue();   
            String source = "";  
            if(doc.getElementsByTagName("source").item(i).hasChildNodes()){//判断节点是否为空  
                source = doc.getElementsByTagName("source").item(i).getFirstChild().getNodeValue();  
            }  
              
            String uTime = "null";  
            if(doc.getElementsByTagName("u_time").item(i).hasChildNodes()){//判断节点是否为空  
                uTime = doc.getElementsByTagName("u_time").item(i).getFirstChild().getNodeValue();  
            }  
            String userName = "null";  
            if(doc.getElementsByTagName("u_user").item(i).hasChildNodes()){//判断节点是否为空  
                userName = doc.getElementsByTagName("u_user").item(i).getFirstChild().getNodeValue();   
            }  
            if("null".equals(uTime)||XString.isNullOrEmpty(uTime)){  
                uTime = DateUtil.getNowTime4String();  
            }  
            String inserFrontSql = " INSERT INTO knowledge_event select :id:, '" + name + "'," + date + "," + class_id   
                                + ",'" + uTime + "', id, '" + uTime + "', id," + id + " from user where name='" + userName + "' ";  
            if("null".equals(userName)||XString.isNullOrEmpty(userName)){  
                inserFrontSql = " INSERT INTO knowledge_event values (:id:, '" + name + "'," + date + "," + class_id   
                            + ",'" + uTime + "', null, '" + uTime + "', null," + id + ") ";  
            }  
              
            String sql = "";  
            if("1".equals(uType)){  
                //根据system_id先删除一下要导入的数据 防止重复   
                sql = " delete from yqpt_knowledge_event where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_event where system_id=" + id;  
                front.updateBySql(sql);  
                sql = " INSERT INTO yqpt_knowledge_event VALUES(null, " + date + ", " + cycle + "," + class_id   
                + ",'" + name + "','" + intro + "', " + prompt_start + "," + prompt_end + ",1, '" + source + "','" + uTime + "'," + id + ") ";  
                rest.updateBySql(sql);  
                int lastid = rest.findLastInsertId();//获取最后插入的id 入前台库表 id要rest和front同步  
                sql = inserFrontSql.replace(":id:", lastid+"");//替换为具体id  
                front.updateBySql(sql);  
            }else if("2".equals(uType)){  
                sql = " update yqpt_knowledge_event set date=" + date + ",cycle=" + cycle +",class_id=" + class_id   
                + ",name='" + name + "',intro='" + intro + "',prompt_start=" + prompt_start + ",prompt_end=" + prompt_end   
                + ",source='" + source + "' where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " update knowledge_event set name='" + name + "',date=" + date + " , class_id=" + class_id + " , modify_time='" + uTime   
                    + "', modify_user=(select id from user where name='" + userName + "') where system_id=" + id;  
                front.updateBySql(sql);  
            }else{  
                sql = " delete from yqpt_knowledge_event where system_id=" + id;  
                rest.updateBySql(sql);  
                sql = " delete from knowledge_event where system_id=" + id;  
                front.updateBySql(sql);  
            }  
        }  
    }  
      
    public static void main(String[] args) {  
        reader("",null,null);  
    }  
   */       
}  