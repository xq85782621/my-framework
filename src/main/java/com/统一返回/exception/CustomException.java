package com.统一返回.exception;

import com.统一返回.code.ResultCode;

/**
 *    自定义异常
 *
 */
public class CustomException extends RuntimeException {

    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
