/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dao.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import com.sohu.model.Seckill;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月14日下午2:51:48
 */
public class RedisDao {

    private final JedisPool jedisPool;

    private final RuntimeSchema<Seckill> schema=RuntimeSchema.createFrom(Seckill.class);

    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    public RedisDao(String ip,int port){
        jedisPool=new JedisPool(ip,port);
    }

    public Seckill getSeckill(long seckillId){
        try {
            Jedis jedis=jedisPool.getResource();
            jedis.auth("123456");
            try {
                String key="seckill:"+seckillId;
                byte[] bytes=jedis.get(key.getBytes());
                if(bytes!=null){
                    Seckill seckill=schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    return seckill;
                }
            }finally{
                jedis.close();
            }
        } catch (Exception e) {
            logger.error("{seckillId}"+seckillId+e.getMessage(),e);
        }
        return null;
    }
    public String putSeckill(Seckill seckill){
        try {
            Jedis jedis=jedisPool.getResource();
            jedis.auth("123456");
            try {
                String key="seckill:"+seckill.getId();
               byte[] bytes=ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
               int timeout=60*60;
               String result=jedis.setex(key.getBytes(),timeout, bytes);
               return result;
            }finally{
                jedis.close();
            }
        } catch (Exception e) {
            logger.error("{seckill}"+seckill+e.getMessage(),e);
        }
        return null;
    }
}
