package com.jackey.controller;

import com.jackey.util.Const;
import com.jackey.util.Invoke;
import com.jackey.util.Result;
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

}
