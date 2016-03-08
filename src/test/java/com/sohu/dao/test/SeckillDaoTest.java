/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dao.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sohu.dao.SeckillDao;
import com.sohu.dto.SeckillStateEnum;
import com.sohu.model.Seckill;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月19日上午9:48:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/Spring-dao.xml"})
public class SeckillDaoTest {
    @Autowired
    SeckillDao SeckillDao;

    private final Logger logger= LoggerFactory.getLogger(SeckillDaoTest.class);


    @Test
    public void testReduceStock() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = "2015-12-01 11:11:11";
        Date date=sdf.parse(strDate);
        int n=SeckillDao.reduceStock(1000,date);
        if(n<0){
            System.out.println("更新失败");
        }else{
            System.out.println("更新了"+n+"行数据");
        }
    }

    @Test
    public void testQueryBySeckillid() {
        Seckill seckill= SeckillDao.queryBySeckillid(1000);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void testQuerySeckill() {
        List<Seckill> lists=SeckillDao.querySeckill(1, 2);
        for (Seckill seckill : lists) {
            logger.info("seckill={}",seckill);
        }
    }
    @Test
    public void testpromoteExecute(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id", 1002);
        map.put("phone", "15122687591");
        map.put("time", new Date());
        SeckillDao.promoteExecute(map);
        int a=(int) map.get("result");
        if(a==2){
            System.out.println(SeckillStateEnum.REPEATSECKILL.getStateinfo());
        }
    }
}
