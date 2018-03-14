package com.jackey.util;

/**
 *
 * @author jackey
 * @version $Id: ResultEnum.java, v 0.1 2018-03-12 上午12:15 jackey Exp $
 */
public enum ResultEnum {

    SUCCESS(0, "ok"),

    SYSTEM_ERROR(-1, "系统错误"),

    PARAM_ERROR(101, "参数有误"),

    DATE_INVALID(102, "日期格式不正确"),

    ID_NOT_EXIST(103, "ID不存在"),

    NOT_LOGIN(104, "未登录"),

    ONLY_CSV(105, "仅支持CSV格式"),

    ;

    private int    resultCode;

    private String resultMsg;

    ResultEnum(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public int code() {
        return resultCode;
    }

    public String msg() {
        return resultMsg;
    }
}
