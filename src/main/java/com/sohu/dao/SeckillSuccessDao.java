/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dao;

import org.apache.ibatis.annotations.Param;

import com.sohu.model.SeckillSuccess;


/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月18日下午9:31:18
 */
public interface SeckillSuccessDao {
    /**
     *
    * @Title: insertSeckillSuccess
    * @Description: (插入秒杀成功的用户信息,如果有冲突将返回0，成功返回1)
    * @param @param seckillid
    * @param @param userphone
    * @param @return    设定文件
    * @return int    返回类型
    * @throws
     */
     int insertSeckillSuccess(@Param("seckillid")long seckillid,@Param("userphone") String userphone);
    /**
     *
    * @Title: getSeckillSuccess
    * @Description: (根据秒杀商品的seckillid，返回秒杀成功的信息,并且携带秒杀对象)
    * @param @param seckillid
    * @param @return    设定文件
    * @return List<SeckillSuccess>    返回类型
    * @throws
     */
     SeckillSuccess getSeckillSuccess(@Param("seckillid")long seckillid,@Param("userphone") String userphone);
}
