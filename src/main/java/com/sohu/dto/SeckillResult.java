/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dto;

import com.sohu.model.SeckillSuccess;


/**
 * <P>
 * Description: 这个类是用于将秒杀的结果进行封装
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月22日下午8:25:40
 */
public class SeckillResult {
    private long seckillId;
    /**秒杀的的状态*/
    private int state;
    /**秒杀的输出的信息*/
    private String stateInfo;
    private SeckillSuccess seckillSuccess;

    public long getSeckillId() {
        return seckillId;
    }
    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getStateInfo() {
        return stateInfo;
    }
    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
    public SeckillSuccess getSeckillSuccess() {
        return seckillSuccess;
    }
    public void setSeckillSuccess(SeckillSuccess seckillSuccess) {
        this.seckillSuccess = seckillSuccess;
    }

    public SeckillResult(long seckillId, SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateinfo();
    }

    public SeckillResult(long seckillId, SeckillStateEnum seckillStateEnum, SeckillSuccess seckillSuccess) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateinfo();
        this.seckillSuccess = seckillSuccess;
    }
    public SeckillResult(long seckillId, String stateInfo) {
        super();
        this.seckillId = seckillId;
        this.stateInfo = stateInfo;
    }

}
