package org.sst.mytest.mytest;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileManageDao {  
    /** 
     * 上传文件 
     * @param inputStream：文件输入流 
     * @param fileName：上传文件名称 
     */  
    public void upload(InputStream inputStream,String directory,String fileName) ;  
    /** 
     * 下载文件 
     * @param outputStream：文件输出流 
     * @param fileName：下载文件名称 
     */  
    public void download(OutputStream outputStream,String directory,String fileName) ;  
    /** 
     * 获取文件的输入流 
     * @param directory 
     * @param fileName 
     * @return 
     */  
    public InputStream getAttachmentFile(String directory,String fileName);  
    /** 
     * 根据文档全名删除文件 
     * @param fileName 
     */  
    public void delete(String directory,String fileName) ;  
        /** 
     * 判断文件是否存在 
     * @param directory 
     * @param fileName 
     * @return 
     */  
    public boolean isExist(String directory,String fileName) ;  
}  