package hello;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.sst.mytest.mytest.FileManageDaoImplByFTP;

public class FtpTest {
	public static void main(String[] args) {

		//从FTP服务器上下载1.txt保存到d:\\3.txt
		FileManageDaoImplByFTP fdbf= new FileManageDaoImplByFTP();

		File  file =new File("d:\\3.txt");
		try {
			OutputStream outputStream = new FileOutputStream(file);
			fdbf.download(outputStream, "abc", "1.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("//////下载成功");
		
		//将d盘的1.xml上传到ftp的 cde /5.xml
		file =new File("d:\\1.xml");
		try {
			InputStream inputstream= new FileInputStream(file);
			fdbf.upload(inputstream, "cde", "5.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("//////上传成功");
	}
}
