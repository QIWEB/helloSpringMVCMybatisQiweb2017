package org.sst.mytest.mytest;

import org.springframework.util.StringUtils;

public abstract class FileManageDaoTemplate implements FileManageDao {  
    //主机ip  
    public String host="" ;  
    //端口号，默认为22  
    public int port = 22 ;  
    //服务器用户名,默认为root  
    public String userName="root" ;  
    //服务器密码  
    public String password ;  
    //服务器上传地址  
    public String targetBaseLocation = "chrhc" ;  
    //服务器连接超时时间(ms)，默认60000  
    public int timeout = 60000;  
      
    public void setTargetBaseLocation(String targetBaseLocation) {  
        if(!StringUtils.hasLength(targetBaseLocation)){  
            return ;  
        }  
        targetBaseLocation = StringUtils.trimLeadingCharacter(targetBaseLocation, '/') ;  
        targetBaseLocation = StringUtils.trimTrailingCharacter(targetBaseLocation, '/') ;  
          
        this.targetBaseLocation = targetBaseLocation ;  
    }  
    // ....set and get method.....  
}  