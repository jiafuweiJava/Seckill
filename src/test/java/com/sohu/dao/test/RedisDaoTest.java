/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sohu.dao.SeckillDao;
import com.sohu.dao.redis.RedisDao;
import com.sohu.model.Seckill;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年2月14日下午3:30:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/Spring-dao.xml"})
public class RedisDaoTest {
    private final  long id=1001;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao  seckillDao;
    @Test
    public void testSeckill() {
        Seckill seckill=redisDao.getSeckill(id);
        if(seckill==null){
            seckill=seckillDao.queryBySeckillid(id);
            if(seckill!=null){
                String result=redisDao.putSeckill(seckill);
                System.out.println(result);
                 seckill=redisDao.getSeckill(id);
                 System.out.println(seckill);
            }
        }
    }

}
