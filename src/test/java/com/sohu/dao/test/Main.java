package com.sohu.dao.test;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
/**
 * 在redis中设置一个键为mykey，值为1000的变量.
 * 通俗点讲，watch命令就是标记一个键，如果标记了一个键，
 * 在提交事务前如果该键被别人修改过，那事务就会失败，这种情况通常可以在程序中重新再尝试一次。
 * @author jfw
 *
 */
public class Main {

    public static void main(String[] args) {
            new MyThread().start();
            new MyThread().start();
            new MyThread().start();
            new MyThread().start();
            new MyThread().start();
            new MyThread().start();
            new MyThread().start();
            new MyThread().start();
            new MyThread().start();
            new MyThread().start();
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            System.out.println(Thread.currentThread().getName());
            JedisPool jedisPool = new JedisPool("192.168.1.143", 6379);
    		Jedis jedis=jedisPool.getResource();
    		//权限认证
    		jedis.auth("123456");
            try {
                int stock = Integer.parseInt(jedis.get("mykey"));
                if (stock > 0) {
                    jedis.watch("mykey");
                    Transaction transaction = jedis.multi();
                    transaction.set("mykey", String.valueOf(stock - 1));
                    List<Object> result = transaction.exec();
                    if (result == null || result.isEmpty()) {
                        System.out.println("Transaction error...");// 可能是watch-key被外部修改，或者是数据操作被驳回
                    }
                } else {
                    System.out.println("库存为0");
                    break;
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                //RedisUtil.returnResource(jedis);
            }finally{
                //RedisUtil.returnResource(jedis);
            }

        }
    }

}