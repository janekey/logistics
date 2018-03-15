package com.jackey.controller;

import com.jackey.util.CommonException;
import com.jackey.util.Const;
import com.jackey.util.Invoke;
import com.jackey.util.Result;
import com.jackey.util.ResultEnum;
import com.jackey.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 *
 * @author jackey
 * @version $Id: LoginController.java, v 0.1 2018-03-13 下午9:34 jackey Exp $
 */
@Controller
public class LoginController extends AbstractController {

    @RequestMapping("/login")
    public @ResponseBody Result login(HttpSession session, String name, String password) {
        return process(new Invoke<Object>() {
            public Object invoke() {
                if (StringUtil.isEmpty(name) || StringUtil.isEmpty(password)
                    || !"admin".equals(name) || !"admin".equals(password)) {
                    throw new CommonException(ResultEnum.LOGIN_FAILED);
                }
                session.setAttribute(Const.SESSION_LOGIN_KEY, Const.LOGIN_VALUE);
                return null;
            }
        });
    }

    @RequestMapping("/logout")
    public @ResponseBody Result logout(HttpSession session) {
        return process(new Invoke<Object>() {
            public Object invoke() {
                session.removeAttribute(Const.SESSION_LOGIN_KEY);
                return null;
            }
        });
    }

    @RequestMapping("/islogin")
    public @ResponseBody Result isLogin(HttpSession session) {
        return process(new Invoke<Object>() {
            public Object invoke() {
                Object login = session.getAttribute(Const.SESSION_LOGIN_KEY);
                if (!(login instanceof String) || !Const.LOGIN_VALUE.equals(login)) {
                    throw new CommonException(ResultEnum.NOT_LOGIN);
                }

                return null;
            }
        });
    }

}
