/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.model;

import java.util.Date;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月18日下午9:20:14
 */
public class SeckillSuccess {
    private long seckillid;
    private String userPhone;
    private  short state;
    private  Date createTime;
    private Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }
    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }
    public long getSeckillid() {
        return seckillid;
    }
    public void setSeckillid(long seckillid) {
        this.seckillid = seckillid;
    }
    public String getUserPhone() {
        return userPhone;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public short getState() {
        return state;
    }
    public void setState(short state) {
        this.state = state;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString() {
        return "SeckillSuccess [seckillid=" + seckillid + ", userPhone=" + userPhone + ", state=" + state
                + ", createTime=" + createTime + "]";
    }

}
