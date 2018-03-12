package com.jackey.util;

/**
 *
 * @author jackey
 * @version $Id: PageResult.java, v 0.1 2018-03-12 下午10:05 jackey Exp $
 */
public class PageResult<T> extends Result<T> {

    private long total;

    /**
     * Getter method for property <tt>total</tt>.
     *
     * @return property value of total
     */
    public long getTotal() {
        return total;
    }

    /**
     * Setter method for property <tt>total</tt>.
     *
     * @param total value to be assigned to property total
     */
    public void setTotal(long total) {
        this.total = total;
    }
}
