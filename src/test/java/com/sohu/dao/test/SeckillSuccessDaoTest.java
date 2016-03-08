/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sohu.dao.SeckillSuccessDao;
import com.sohu.model.SeckillSuccess;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月19日上午11:30:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/Spring-dao.xml"})
public class SeckillSuccessDaoTest {
    @Autowired
    SeckillSuccessDao seckillSuccessDao;

    private final Logger logger=LoggerFactory.getLogger(SeckillSuccessDaoTest.class);

    @Test
    public void testInsertSeckillSuccess() {
        int n=seckillSuccessDao.insertSeckillSuccess(1000,"15122687594");
        System.out.println("插入数据条数 ："+n);
    }

    @Test
    public void testGetSeckillSuccess() {
        SeckillSuccess seckillSuccess=seckillSuccessDao.getSeckillSuccess(1000,"15122687593");
        logger.info("{seckillSuccess}"+seckillSuccess);
    }

}
