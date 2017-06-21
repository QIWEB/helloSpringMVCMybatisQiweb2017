package hello;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 本地文件读写类
 * Created by lijuan on 2016/8/26.
 */
public class FileUtils {
    /**
     * 写数据
     *
     * @param mContext 上下文
     * @param fileName 文件名
     * @param writestr 写入文件的字符串
     * @throws IOException
     */
//    public static void writeFile(Context mContext, String fileName, String writestr) throws IOException {
//        try {
//            //创建流文件写出类
//            //FileOutputStream fout = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
//            //获取流的字符数
//            byte[] bytes = writestr.getBytes();
//            //写出流,保存在文件fileName中
//            //fout.write(bytes);
//            //关闭流
//            //fout.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 读数据
//     *
//     * @param mContext 上下文
//     * @param fileName 文件名
//     * @return
//     * @throws IOException
//     */
//    public static String readFile(Context mContext, String fileName) throws IOException {
//        String res = "";
//        try {
//            //创建流文件读入类
//            FileInputStream fin = mContext.openFileInput(fileName);
//            //通过available方法取得流的最大字符数
//            byte[] buffer = new byte[fin.available()];
//            //读入流,保存在byte数组
//            fin.read(buffer);
//            res = EncodingUtils.getString(buffer, "UTF-8");
//            //关闭流
//            fin.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return res;
//    }
}