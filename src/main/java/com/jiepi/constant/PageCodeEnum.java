package com.jiepi.constant;

public enum PageCodeEnum {
    ADD_SUCCESS(100,"新增成功"),

    ADD_ERROE(101,"新增失败");


    PageCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private  String msg;
    public static final String KEY = "pageCode";
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
