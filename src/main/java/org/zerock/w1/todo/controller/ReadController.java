package org.zerock.w1.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.zerock.w1.todo.dto.TodoDto;
import org.zerock.w1.todo.service.TodoService;
import org.zerock.w1.todo.util.CookieName;
import org.zerock.w1.todo.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@WebServlet(name = "readController", urlPatterns = "/todo/read")
public class ReadController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        log.info("GET " + req.getQueryString() + " HTTP/1.1");
        Long tno = Long.parseLong(req.getParameter("tno"));
        try {
            TodoDto dto = TodoService.INSTANCE.get(tno);
            req.setAttribute("dto", dto);

            Cookie viewTodoListCookie = findViewTodoListCookie(req.getCookies());
            addTnoInCookieIfHaveNotViewed(viewTodoListCookie, tno, resp);

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp")
                    .forward(req, resp);
        } catch (SQLException e) {
            ServletUtil.handleException("get error", e);
        }
    }

    private Cookie findViewTodoListCookie(Cookie[] cookies) {
        Optional<Cookie> cookieOptional
                = ServletUtil.findCookie(cookies, CookieName.VIEW_TODO_LIST);
        log.info("Does view todo list cookie exists?: " + cookieOptional.isPresent());
        return cookieOptional.orElseGet(
                () -> new Cookie(CookieName.VIEW_TODO_LIST.name(), ""));
    }

    private void addTnoInCookieIfHaveNotViewed(Cookie ck, Long tno, HttpServletResponse resp) {
        String viewTodoListStr = ck.getValue();
        String tnoStr = "-" + tno + "-";
        if (!viewTodoListStr.contains(tnoStr)) {
            log.info("Add tno: " + tno);
            ck.setValue(viewTodoListStr + tnoStr);
            ck.setMaxAge(60 * 60 * 24);
            ck.setPath("/");
            resp.addCookie(ck);
        }
    }
}
