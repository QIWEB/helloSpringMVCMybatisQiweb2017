package hello;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * �����ļ���д��
 * Created by lijuan on 2016/8/26.
 */
public class FileUtils {
    /**
     * д����
     *
     * @param mContext ������
     * @param fileName �ļ���
     * @param writestr д���ļ����ַ���
     * @throws IOException
     */
//    public static void writeFile(Context mContext, String fileName, String writestr) throws IOException {
//        try {
//            //�������ļ�д����
//            //FileOutputStream fout = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
//            //��ȡ�����ַ���
//            byte[] bytes = writestr.getBytes();
//            //д����,�������ļ�fileName��
//            //fout.write(bytes);
//            //�ر���
//            //fout.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * ������
//     *
//     * @param mContext ������
//     * @param fileName �ļ���
//     * @return
//     * @throws IOException
//     */
//    public static String readFile(Context mContext, String fileName) throws IOException {
//        String res = "";
//        try {
//            //�������ļ�������
//            FileInputStream fin = mContext.openFileInput(fileName);
//            //ͨ��available����ȡ����������ַ���
//            byte[] buffer = new byte[fin.available()];
//            //������,������byte����
//            fin.read(buffer);
//            res = EncodingUtils.getString(buffer, "UTF-8");
//            //�ر���
//            fin.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return res;
//    }
}