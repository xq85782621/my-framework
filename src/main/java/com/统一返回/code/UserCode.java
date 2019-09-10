package com.统一返回.code;


/**
 *       用户微服务中出现异常使用该类
 *       比如说 查询用户不存在的时候,可以直接throw  UserCode.USER_NOT_EXIST
 *
 *
 *
 *
 */
public enum UserCode implements  ResultCode {

    USER_NOT_EXIST(false,20001,"用户不存在"),

    ;



    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;


    UserCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }



}
