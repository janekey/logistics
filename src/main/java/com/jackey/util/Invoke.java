package com.jackey.util;

/**
 *
 * @author jackey
 * @version $Id: Invoke.java, v 0.1 2018-03-12 上午12:12 jackey Exp $
 */
public abstract class Invoke<T> {

    public void paramValidate() {
    }

    public void preInvoke() {
    }

    public abstract T invoke();

    public void postInvoke() {
    }
}
