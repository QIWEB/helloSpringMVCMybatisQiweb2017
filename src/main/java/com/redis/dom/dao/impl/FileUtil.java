package com.redis.dom.dao.impl;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtil {
    private static Properties prop;

    private static FileInputStream inputStream;

    private static String CONFIG_FILE = Thread
    		.currentThread()
    		.getContextClassLoader()
    		.getResource("redis.properties")
    		.getPath();
    static
    {
        prop = new Properties();
        try
        {
            /******** 读取prop配置 ********/
            inputStream = new FileInputStream(CONFIG_FILE);
            prop.load(inputStream);
            inputStream.close();
            inputStream = null;

//            pinyin_match = Boolean.parseBoolean(prop.getProperty("usePinyin"));
        }catch (Exception e) {
        	
		}
    }
	public static int getPropertyValueInt(String string, String string2) {
		return Integer.parseInt(prop.getProperty(string2));
	}
	public static String getPropertyValue(String string, String string2) {
		return prop.getProperty(string2);
	}
	public static boolean getPropertyValueBoolean(String string, String string2) {
		return Boolean.parseBoolean(prop.getProperty(string2));
	}
}
