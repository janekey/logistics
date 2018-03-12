package com.jackey.util;

/**
 *
 * @author jackey
 * @version $Id: CommonException.java, v 0.1 2018-03-12 上午12:17 jackey Exp $
 */
public class CommonException extends RuntimeException {

    private ResultEnum result;

    public CommonException(ResultEnum result) {
        super(result.code() + "," + result.msg());
        this.result = result;
    }

    /**
     * Getter method for property <tt>result</tt>.
     *
     * @return property value of result
     */
    public ResultEnum getResult() {
        return result;
    }

    /**
     * Setter method for property <tt>result</tt>.
     *
     * @param result value to be assigned to property result
     */
    public void setResult(ResultEnum result) {
        this.result = result;
    }
}
