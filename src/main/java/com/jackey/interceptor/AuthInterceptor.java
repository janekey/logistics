package com.jackey.interceptor;

import com.alibaba.fastjson.JSON;
import com.jackey.util.Const;
import com.jackey.util.Result;
import com.jackey.util.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author jackey
 * @version $Id: AuthInterceptor.java, v 0.1 2018-03-12 下午9:56 jackey Exp $
 */
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        Object login = request.getSession().getAttribute(Const.SESSION_LOGIN_KEY);
        if (login instanceof String && Const.LOGIN_VALUE.equals(login)) {
            return true;
        } else {
            Result result = new Result();
            PrintWriter out = null;
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=UTF-8");

                out = response.getWriter();
                result.setResult(ResultEnum.NOT_LOGIN);
                out.append(JSON.toJSONString(result));
            } catch (IOException e) {
                LOGGER.error("authInterceptor", e);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
            //在请求处理之前进行调用
            return false;
        }
    }
}
