/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sohu.model.Seckill;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月18日下午9:31:04
 */
public interface SeckillDao {
    /**
     *
    * @Title: reduceStock
    * @Description: (减少库存,并且返回更新的行数)
    * @param @param id
    * @param @return    设定文件
    * @return int    返回类型
    * @throws
     */
     int reduceStock(@Param("id")long id,@Param("nowTime")Date nowTime);
    /**
     *
    * @Title: querySeckill
    * @Description: (查询某一个Seckill的信息)
    * @param @param queryid
    * @param @return    设定文件
    * @return Seckill    返回类型
    * @throws
     */
     Seckill queryBySeckillid(long queryid);
    /**
     *
    * @Title: querySeckill
    * @Description:(根据偏移量和数量,返回一定量的Seckill)
    * @param @param offset
    * @param @param num
    * @param @return    设定文件
    * @return List<Seckill>    返回类型
    * @throws
     */
     List<Seckill> querySeckill(@Param("offset")long offset,@Param("limit")long limit);

     void promoteExecute(Map<String,Object> map);
}
