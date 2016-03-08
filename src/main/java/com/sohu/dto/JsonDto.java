/**
* @Title: JsonDto.java
* @Package com.sohu.dto
* @Description: (用一句话描述该文件做什么)
* @author wenbozhang
* @date 2016年1月28日 下午3:25:43
* @version V1.0
*/
/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.dto;
/**
 * @ClassName: JsonDto
 * @Description: 用于前后台传输json的工具类
 * @author wenbozhang
 * @date 2016年1月28日 下午3:25:43
 *
 */
public class JsonDto<T> {
    private  boolean isSuccess;
    private String error;
    private T data;

    public void setIsSuccess(boolean isSuccess){
        this.isSuccess=isSuccess;
    }
    public boolean getIsSuccess(){
        return isSuccess;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    /**
     *
    * <p>Title: </p>
    * <p>Description: 如果错误,返回错误信息
    * @param isSuccess
    * @param error
     */
    public JsonDto(boolean isSuccess, String error) {
        super();
        this.isSuccess = isSuccess;
        this.error = error;
    }
    /**
     *
    * <p>Title: </p>
    * <p>Description: 如果正确，返回正确的数据
    * @param isSuccess
    * @param data
     */
    public JsonDto(boolean isSuccess, T data) {
        super();
        this.isSuccess = isSuccess;
        this.data = data;
    }

}
