/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dto;

import java.util.Date;

/**
 * <P>
 * Description:这个类主要是用于接口的暴漏
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月22日下午8:21:30
 */
public class SeckillExposer {
    /**
    * <p>Title: </p>
    * <p>Description: </p>
    * @param isExpose
    */
    /**秒杀商品的id*/
    private long seckillId;
    /**秒杀地址加密*/
    private String md5;
    /**秒杀开关*/
    private boolean isExpose;
    /**秒杀的开始时间*/
    private Date startTime;
    /**秒杀结束时间*/
    private Date endTime;

    public long getSeckillId() {
        return seckillId;
    }
    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }
    public String getMd5() {
        return md5;
    }
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    public boolean getIsExpose() {
        return isExpose;
    }
    public void setIsExpose(boolean isExpose) {
        this.isExpose = isExpose;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }



    public SeckillExposer(boolean isExpose) {
        super();
        this.isExpose = isExpose;
    }
    public SeckillExposer(boolean isExpose, Date startTime, Date endTime) {
        super();
        this.isExpose = isExpose;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public SeckillExposer(long seckillId, String md5, boolean isExpose) {
        super();
        this.seckillId = seckillId;
        this.md5 = md5;
        this.isExpose = isExpose;
    }

}
