/*
 * Copyright (c) 2016 Sohu TV. All rights reserved.
 */
package com.sohu.dto;


/**
 * <P>
 * Description:这个类是将秒杀的信息封装成数据字典
 * </p>
 *
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月22日下午8:29:36
 */
public enum SeckillStateEnum {
    NORESULT(-2, "系统异常"),
    NOBEGIN(-1, "秒杀还没开始"),
    END(0, "秒杀结束"),
    SUCCESS(1, "秒杀成功"),
    REPEATSECKILL(2, "重复秒杀"),
    URLCHANGED(3, "URL被篡改,不正常访问"),
    NOLOGIN(4, "未登录");

    private int state;
    private String stateinfo;

    public int getState() {
        return state;
    }


    public void setState(int state) {
        this.state = state;
    }


    public String getStateinfo() {
        return stateinfo;
    }


    public void setStateinfo(String stateinfo) {
        this.stateinfo = stateinfo;
    }




    SeckillStateEnum(int state, String stateinfo) {
        this.state = state;
        this.stateinfo = stateinfo;
    }


    public static SeckillStateEnum getState(int index){
            for(SeckillStateEnum seckillStateEnum:values()){
                if(seckillStateEnum.getState()==index){
                    return seckillStateEnum;
                }
            }
            return null;
    }
}
