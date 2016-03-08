/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
package com.sohu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.sohu.dao.SeckillDao;
import com.sohu.dao.SeckillSuccessDao;
import com.sohu.dao.redis.RedisDao;
import com.sohu.dto.SeckillExposer;
import com.sohu.dto.SeckillResult;
import com.sohu.dto.SeckillStateEnum;
import com.sohu.exception.RepeatSeckillException;
import com.sohu.exception.SeckillCloseException;
import com.sohu.exception.SeckillException;
import com.sohu.model.Seckill;
import com.sohu.service.SeckillService;

/**
 * <P>
 * Description:
 * </p>
 *
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月22日下午8:12:17
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private static final String MD5 = "EWQEWQEWQ312312&*^7";
    @Autowired
    SeckillDao SeckillDao;
    @Autowired
    SeckillSuccessDao seckillSuccessDao;
    @Autowired
    RedisDao redisDao;
    @Override
    public List<Seckill> listSeckill() {

        return SeckillDao.querySeckill(0, 4);
    }

    @Override
    public Seckill querySeckById(long seckillId) {

        return SeckillDao.queryBySeckillid(seckillId);
    }

    @Override
    public SeckillExposer exportSeckillUrl(long seckillId) {
        Seckill seckill =redisDao.getSeckill(seckillId);
        if (seckill == null ) {
            seckill = SeckillDao.queryBySeckillid(seckillId);
            if(seckill==null){
                return new SeckillExposer(false);       //返回一个关闭的接口
            }else{
                redisDao.putSeckill(seckill);
            }
        }
            Date startTime = seckill.getStartTime();
            Date endTime = seckill.getEndTime();
            Date now = new Date();
            if (startTime.getTime() > now.getTime() || endTime.getTime() < now.getTime()) {
                return new SeckillExposer(false, startTime, endTime);
            }//开始的时间大于现在的时间或者 结束的时间小于现在的时间都算是接口已经关闭
            String md5 = getMd5(seckillId);//将返回的的id加密，并且暴漏接口
            return new SeckillExposer(seckillId, md5, true);

    }

    @Override
    @Transactional
    public SeckillResult executeSeckill(long seckillId, String md5, String userPhone)
            throws SeckillException, RepeatSeckillException, SeckillCloseException {
        try{
            if (md5 == null || !md5.equals(getMd5(seckillId))) {
                throw new SeckillException(SeckillStateEnum.URLCHANGED.getStateinfo());
            } else {
                int supdateRow = SeckillDao.reduceStock(seckillId, new Date());
                if (supdateRow <= 0) {
                    throw new SeckillCloseException(SeckillStateEnum.END.getStateinfo());
                } else {
                    int skupdateRow = seckillSuccessDao.insertSeckillSuccess(seckillId, userPhone);
                    if (skupdateRow<=0) {
                        throw new RepeatSeckillException(SeckillStateEnum.REPEATSECKILL.getStateinfo());
                    }
                    return new SeckillResult(seckillId, SeckillStateEnum.SUCCESS,seckillSuccessDao.getSeckillSuccess(seckillId,userPhone));
                }
            }
        }catch(SeckillCloseException e1){
            throw e1;
        }catch(RepeatSeckillException e2){
            throw e2;
        }catch(Exception e){
            throw new SeckillException(e.getMessage());
        }
    }

    @Override
    public String getMd5(long seckillId) {
        String md5 = seckillId + MD5;
        return new String(DigestUtils.md5DigestAsHex(md5.getBytes()));
    }

    @Override
    public int executeSeckillPromote(long seckillId, String md5, String userPhone) {
        Map<String,Object> map=new HashMap<String,Object>();
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException(SeckillStateEnum.URLCHANGED.getStateinfo());
        } else {
            map.put("id", seckillId);
            map.put("phone",userPhone);
            map.put("time", new Date());
            SeckillDao.promoteExecute(map);
        }
       int result=(int) map.get("result");
       return result;
    }

}
