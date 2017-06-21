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

		//��FTP������������1.txt���浽d:\\3.txt
		FileManageDaoImplByFTP fdbf= new FileManageDaoImplByFTP();

		File  file =new File("d:\\3.txt");
		try {
			OutputStream outputStream = new FileOutputStream(file);
			fdbf.download(outputStream, "abc", "1.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("//////���سɹ�");
		
		//��d�̵�1.xml�ϴ���ftp�� cde /5.xml
		file =new File("d:\\1.xml");
		try {
			InputStream inputstream= new FileInputStream(file);
			fdbf.upload(inputstream, "cde", "5.xml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("//////�ϴ��ɹ�");
	}
}
