package com.gz.example.demo.common;

import com.alibaba.fastjson.JSON;

public class ResultMsg {
    private Integer code;
    private String message;
    private Object data;



    public Integer getCode() {
        return code;
    }

    public ResultMsg setCode(ResultCode resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultMsg setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultMsg setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
