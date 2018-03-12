package com.jackey.util;

/**
 *
 * @author jackey
 * @version $Id: Result.java, v 0.1 2018-03-12 上午12:09 jackey Exp $
 */
public class Result<T> {

    /** 结果码 */
    private int    code = ResultEnum.SUCCESS.code();

    /** 结果消息 */
    private String msg  = ResultEnum.SUCCESS.msg();

    /** 返回的数据 */
    private T      data;

    public void setResult(ResultEnum result) {
        this.code = result.code();
        this.msg = result.msg();
    }

    /**
     * Getter method for property <tt>data</tt>.
     *
     * @return property value of data
     */
    public T getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     *
     * @param data value to be assigned to property data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>msg</tt>.
     *
     * @return property value of msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Setter method for property <tt>msg</tt>.
     *
     * @param msg value to be assigned to property msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
