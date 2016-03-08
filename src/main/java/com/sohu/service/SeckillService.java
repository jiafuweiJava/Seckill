/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.service;

import java.util.List;

import com.sohu.dto.SeckillExposer;
import com.sohu.dto.SeckillResult;
import com.sohu.exception.RepeatSeckillException;
import com.sohu.exception.SeckillCloseException;
import com.sohu.exception.SeckillException;
import com.sohu.model.Seckill;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月22日下午8:12:01
 */
public interface SeckillService {
    /**
     *
    * @Title: listSeckill
    * @Description: (列出秒杀商品)
    * @param @return    设定文件
    * @return List<Seckill>    返回类型
    * @throws
     */
    List<Seckill> listSeckill();
    /**
     *
    * @Title: querySeckById
    * @Description: (根据秒杀的商品的id获得秒杀的产品)
    * @param @param seckillId
    * @param @return    设定文件
    * @return Seckill    返回类型
    * @throws
     */
    Seckill querySeckById(long seckillId);
       /**
        *
       * @Title: exportSeckillUrl
       * @Description: (暴漏秒杀接口)
       * @param @param seckillId
       * @param @return    设定文件
       * @return SeckillSuccess    返回类型
       * @throws
        */
    SeckillExposer exportSeckillUrl(long seckillId);
    /**
     *
    * @Title: executeSeckill
    * @Description: (执行秒杀激化)
    * @param @param seckillId
    * @param @param md5
    * @param @param userPhone
    * @param @return    设定文件
    * @return SeckillResult    返回类型
    * @throws
     */
    SeckillResult executeSeckill(long seckillId, String md5, String userPhone)
                throws SeckillException,RepeatSeckillException,SeckillCloseException;

    String getMd5(long seckillId);

    int executeSeckillPromote(long seckillId, String md5, String userPhone);
}
