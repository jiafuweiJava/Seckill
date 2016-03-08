/**
* @Title: Seckill.java
* @Package com.sohu.model
* @Description: (用一句话描述该文件做什么)
* @author wenbozhang
* @date 2016年1月18日 下午9:19:56
* @version V1.0
*/
/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.model;

import java.util.Date;

/**
 * @ClassName: Seckill
 * @Description: (这里用一句话描述这个类的作用)
 * @author wenbozhang
 * @date 2016年1月18日 下午9:19:56
 *
 */
public class Seckill {
      private long id;
      private String name;
      private long num;
      private Date startTime;
      private Date endTime;
      private Date createTime;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getNum() {
        return num;
    }
    public void setNum(long num) {
        this.num = num;
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
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Seckill [id=" + id + ", name=" + name + ", num=" + num + ", startTime=" + startTime + ", endTime="
                + endTime + ", createTime=" + createTime + "]";
    }

}
