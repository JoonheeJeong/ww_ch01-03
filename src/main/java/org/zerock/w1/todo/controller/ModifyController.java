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
@WebServlet(name = "modifyController", urlPatterns = "/todo/modify")
public class ModifyController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        log.info("GET " + req.getQueryString() + " HTTP/1.1");
        Long tno = Long.parseLong(req.getParameter("tno"));
        try {
            TodoDto dto = TodoService.INSTANCE.get(tno);
            req.setAttribute("dto", dto);
            req.getRequestDispatcher("/WEB-INF/todo/modify.jsp")
                    .forward(req, resp);
        } catch (SQLException e) {
            log.error(e.getMessage());
            log.error(e.getSQLState());
            e.printStackTrace();
            throw new ServletException("get error", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        log.info("POST /todo/modify HTTP/1.1");
        TodoDto dto = TodoDto.builder()
                        .tno(Long.parseLong(req.getParameter("tno")))
                        .title(req.getParameter("title"))
                        .dueDate(LocalDate.parse(req.getParameter("dueDate")))
                        .finished(req.getParameter("finished") != null)
                        .build();
        try {
            TodoService.INSTANCE.modify(dto);
            resp.sendRedirect("/todo/list");
        } catch (SQLException e) {
            ServletUtil.handleException("modify error", e);
        }
    }
}
