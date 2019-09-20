package com.统一返回.exception;

import com.google.common.collect.ImmutableMap;
import com.统一返回.code.CommonCode;
import com.统一返回.code.ResultCode;
import com.统一返回.response.ResponseResult;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常捕获
 * 1.首先会去捕获自己抛出的自定义异常
 * 2.再去捕获非自定义但是已经添加到map里的异常
 * 3.最后捕获未预知的异常
 */
@ControllerAdvice
public class ExceptionCatch {


    //定义map，配置异常类型所对应的错误代码
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
    //定义map的builder对象，去构建ImmutableMap
    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();



    // 未授权异常
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public ResponseResult error(HttpServletRequest request, HttpServletResponse response, AuthorizationException e) {
        return new ResponseResult(CommonCode.UN_AUTHORISE);
    }


    //捕获CustomException此类异常
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult customException(CustomException customException) {
        //记录日志
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }


    //捕获Exception此类异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception exception) {

        System.err.println("出现了异常:" + exception);
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();//EXCEPTIONS构建成功
        }
        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
        if (resultCode != null) {
            return new ResponseResult(resultCode);
        } else {
            //所有非自定义,也未提前预知的异常统一返回99999异常
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }


    }

    static {
        //定义异常类型所对应的错误代码
        builder.put(NullPointerException.class, CommonCode.SERVER_ERROR);
    }


}
