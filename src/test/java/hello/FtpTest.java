package hello;

import java.io.InputStream;

import org.sst.mytest.mytest.FileManageDaoImplByFTP;

public class FtpTest {
	public static void main(String[] args) {
		FileManageDaoImplByFTP fdbf= new FileManageDaoImplByFTP();
		//获取ftp上1.txt
		InputStream ist= fdbf.getAttachmentFile("D:\\","1.xml");
		//上传到2.txt
		fdbf.upload(ist,"cde","2.xml");
	}
}
