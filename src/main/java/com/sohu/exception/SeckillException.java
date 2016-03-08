/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.exception;
/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月22日下午9:13:14
 */
public class SeckillException extends RuntimeException{
    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(String message) {
        super(message);
    }
}
