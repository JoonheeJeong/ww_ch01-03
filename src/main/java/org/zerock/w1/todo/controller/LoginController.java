package org.zerock.w1.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.zerock.w1.todo.dto.MemberDto;
import org.zerock.w1.todo.dto.TodoDto;
import org.zerock.w1.todo.service.MemberService;
import org.zerock.w1.todo.service.TodoService;
import org.zerock.w1.todo.util.CookieName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Slf4j
@WebServlet(name = "loginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        log.info("GET /login HTTP/1.1");
        req.getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("POST /login HTTP/1.1");

        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        boolean rememberMe = req.getParameter("auto") != null;
        try {
            MemberDto dto = MemberService.INSTANCE.login(id, pw);
            if (rememberMe) {
                String uuid = UUID.randomUUID().toString();

                MemberService.INSTANCE.updateUuid(id, uuid);
                dto.setUuid(uuid);

                Cookie ck = makeRememberMeCookie(uuid);
                resp.addCookie(ck);
            }
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", dto);
            resp.sendRedirect("/todo/list");
//            log.info("after sendRedirect");
        } catch (SQLException e) {
            resp.sendRedirect("/login?result=error");
        }
    }

    private Cookie makeRememberMeCookie(String uuid) {
        String name = CookieName.REMEMBER_ME.name();
        Cookie ck = new Cookie(name, uuid);
        ck.setMaxAge(60*60*24*7);
        ck.setPath("/");
        return ck;
    }

}
