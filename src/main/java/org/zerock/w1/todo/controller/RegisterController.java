package org.zerock.w1.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.zerock.w1.todo.dto.TodoDto;
import org.zerock.w1.todo.service.TodoService;
import org.zerock.w1.todo.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@Slf4j
@WebServlet(name = "registerController", urlPatterns = "/todo/register")
public class RegisterController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        log.info("GET /todo/register HTTP/1.1");
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp")
                .forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        log.info("POST /todo/register HTTP/1.1");
        try {
            TodoDto dto = TodoDto.builder()
                    .title(req.getParameter("title"))
                    .dueDate(LocalDate.parse(req.getParameter("dueDate")))
                    .finished(req.getParameter("finished") != null)
                    .build();
            TodoService.INSTANCE.register(dto);
            resp.sendRedirect("/todo/list");
        } catch (SQLException e) {
            ServletUtil.handleException("register error", e);
        }
    }
}
