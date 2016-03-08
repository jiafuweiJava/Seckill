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
 * @Date 2016年1月22日下午9:13:57
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillCloseException(String message) {
        super(message);
    }
}
