package org.zerock.w1.todo.controller;

import org.zerock.w1.todo.util.CookieName;
import org.zerock.w1.todo.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("loginInfo");
        ServletUtil.findCookie(req.getCookies(), CookieName.REMEMBER_ME)
                        .ifPresent(cookie -> {
                            cookie.setMaxAge(0);
                            resp.addCookie(cookie);
                        });
        session.invalidate();
        resp.sendRedirect("/");
    }
}
