package org.sst.mytest.mytest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.springframework.util.StringUtils;

public class FileManageDaoImplByFTP extends FileManageDaoTemplate {  
    /** 
     * 获取ftp客户端连接 
     * @return 
     * @throws SocketException 
     * @throws IOException 
     */  
    public FTPClient getFtpClient(String directory) throws SocketException, IOException{  
        FTPClient ftpClient = new FTPClient();  
        ftpClient.connect(host, port);  
        ftpClient.setConnectTimeout(timeout);  
        boolean loginFlag = ftpClient.login(userName, password) ;  
        if(!loginFlag){  
            throw new SocketException("ftp login error ,please check setting .") ;  
        }  
        //设置文件上传目录  
        if(StringUtils.hasLength(directory.trim())){  
            directory = targetBaseLocation+"/"+StringUtils.trimLeadingCharacter(directory, '/');  
        }else{  
            directory = targetBaseLocation ;  
        }  
        //设置上传目录  
        boolean flag = ftpClient.changeWorkingDirectory(directory) ;  
        //如果目录不存在，则创建目录  
        if(!flag){  
            String path = null ;  
            for (String folderName : directory.split("/")) {  
                if(path==null){  
                    path = folderName ;  
                }else{  
                    path += "/"+folderName ;  
                }  
                ftpClient.makeDirectory(path);  
            }  
            flag = ftpClient.changeWorkingDirectory(path) ;  
        }  
          
        ftpClient.setBufferSize(1024);   
        ftpClient.setControlEncoding("utf-8");  
        //设置文件类型（二进制）         
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);   
//设置ftp上传模式为client向server传  
        ftpClient.enterLocalPassiveMode();   
        return ftpClient ;  
    }  

    @Override  
    public void upload(InputStream inputStream,String directory, String fileName) {  
        FTPClient ftpClient = null ;  
        try {  
            ftpClient = getFtpClient(directory);  
            //文件名称使用数字+英文，使用汉字会有乱码  
            boolean flag = ftpClient.storeFile(new String(fileName.getBytes("utf-8"),"iso-8859-1"), inputStream);   
            if(!flag){  
                throw new Exception("上传文件 "+fileName+" 失败。") ;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            //关闭连接  
            IOUtils.closeQuietly(inputStream);         
            close(ftpClient);   
        }  
    }  

    @Override  
    public void download(OutputStream outputStream,String directory, String fileName){  
        FTPClient ftpClient = null ;  
        try {  
            ftpClient = getFtpClient(directory);  
            boolean fileIsExist = isExist(ftpClient, fileName) ;  
            if(fileIsExist){  
                boolean flag = ftpClient.retrieveFile(fileName, outputStream) ;  
                if(!flag){  
                    throw new Exception("下载文件 "+fileName+" 失败。") ;  
                }  
            }else{  
                throw new Exception("FTP服务器文件 "+fileName+" 不存在.......") ;  
            }  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            System.out.println(e.getMessage());  
        }finally{  
            close(ftpClient);      
        }  
    }  

    @Override  
    public void delete(String directory, String fileName) {  
        FTPClient ftpClient = null ;  
        try {  
            ftpClient = getFtpClient(directory);  
            boolean flag = ftpClient.deleteFile(new String(fileName.getBytes("utf-8"),"iso-8859-1"));  
if(!flag){  
                throw new Exception("删除文件 "+fileName+" 失败。") ;  
            }  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
            //关闭连接  
            close(ftpClient);    
        }  
    }  
      
    public InputStream getAttachmentFile(String directory,String fileName){  
        FTPClient ftpClient = null ;  
        InputStream inputStream = null;   
        try {  
            ftpClient = getFtpClient(directory);  
            boolean fileIsExist = isExist(ftpClient, fileName) ;  
            if(fileIsExist){  
                inputStream = ftpClient.retrieveFileStream(fileName);  
            }else{  
                throw new Exception("FTP服务器文件 "+fileName+" 不存在.......") ;  
            }  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            System.out.println(e.getMessage());  
        }finally{  
            //关闭连接  
            close(ftpClient);    
        }  
        return inputStream ;  
    }  
      
    public void close(FTPClient ftpClient){  
        if(ftpClient!=null){  
            try {  
                ftpClient.disconnect();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  
/** 
     * 判断ftpClient当前所在目录下，名称为fileName的文件是否存在 
     * @param ftpClient 
     * @param fileName 
     * @return 
     */  
    public boolean isExist(FTPClient ftpClient,final String fileName) {  
        boolean flag = false ;  
        try {  
            FTPFile[] ftpFiles = ftpClient.listFiles(null, new FTPFileFilter() {  
                @Override  
                public boolean accept(FTPFile file) {  
                    if(file.getName().equals(fileName)){  
                        return true ;  
                    }  
                    return false ;  
                }  
            }) ;  
              
            if(ftpFiles!=null&&ftpFiles.length>0){  
                flag = true ;  
            }  
        } catch (Exception e) {  
            System.out.println("-------query ftp file exist fail......");  
        }  
          
        return flag ;  
    }  
       @Override  
    public boolean isExist(String directory, final String fileName) {  
        boolean flag = false ;  
        FTPClient ftpClient = null ;  
        try {  
            ftpClient = getFtpClient(directory) ;  
              
            FTPFile[] ftpFiles = ftpClient.listFiles(null, new FTPFileFilter() {  
                @Override  
                public boolean accept(FTPFile file) {  
                    if(file.getName().equals(fileName)){  
                        return true ;  
                    }  
                    return false ;  
                }  
            }) ;  
              
            if(ftpFiles!=null&&ftpFiles.length>0){  
                flag = true ;  
            }  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
            close(ftpClient);   
        }  
          
        return flag ;  
    }


}  