package com.gz.example.demo.common;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String UNAUTHORIZED_MESSAGE = "您当前尚未登录或者登录已经过期，请重新登录";
    public static ResultMsg genSuccessResultMsg() {
        return new ResultMsg()
                .setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static ResultMsg genSuccessResultMsg(Object data) {
        return new ResultMsg()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static ResultMsg genFailResultMsg(String message) {
        return new ResultMsg()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    public static ResultMsg genUnAuthorityResultMsg(){
        return new ResultMsg()
                .setCode(ResultCode.UNAUTHORIZED)
                .setMessage(UNAUTHORIZED_MESSAGE);
    }

}