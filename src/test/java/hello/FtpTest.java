package hello;

import java.io.InputStream;

import org.sst.mytest.mytest.FileManageDaoImplByFTP;

public class FtpTest {
	public static void main(String[] args) {
		FileManageDaoImplByFTP fdbf= new FileManageDaoImplByFTP();
		//��ȡftp��1.txt
		InputStream ist= fdbf.getAttachmentFile("D:\\","1.xml");
		//�ϴ���2.txt
		fdbf.upload(ist,"cde","2.xml");
	}
}
