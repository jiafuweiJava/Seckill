package com.sohu.dao.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestRedis {
	public static void main(String[] args) {
		//Jedis jedis = new Jedis("192.168.1.143", 6379);
		//System.out.println(jedis);
		//权限认证
		//jedis.auth("123456");
		
		JedisPool jedisPool = new JedisPool("192.168.1.143", 6379);
		Jedis jedis=jedisPool.getResource();
		System.out.println(jedis);
	}
}
