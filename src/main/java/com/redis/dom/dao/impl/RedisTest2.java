package com.redis.dom.dao.impl;

public class RedisTest2 {
	public static void main(String[] args) {
		RedisUtil.setString("name", "zhangsan123");
		System.out.println(RedisUtil.getString("name"));
	}
}
