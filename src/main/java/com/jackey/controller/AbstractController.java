package com.jackey.controller;

import com.jackey.util.CommonException;
import com.jackey.util.Invoke;
import com.jackey.util.Result;
import com.jackey.util.ResultEnum;

/**
 *
 * @author jackey
 * @version $Id: AbstractController.java, v 0.1 2018-03-12 上午12:06 jackey Exp $
 */
class AbstractController {

    protected <T> Result<T> process(Invoke<T> invoke) {
        Result<T> result = new Result<>();
        try {
            invoke.paramValidate();
            invoke.preInvoke();
            T data = invoke.invoke();
            result.setData(data);
            invoke.postInvoke();
            result.setResult(ResultEnum.SUCCESS);
        } catch (CommonException e) {
            result.setResult(e.getResult());
        } catch (Throwable th) {
            result.setResult(ResultEnum.SYSTEM_ERROR);
        }
        return result;
    }

}