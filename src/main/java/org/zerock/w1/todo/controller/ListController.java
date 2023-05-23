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
import java.util.List;

@Slf4j
@WebServlet(name = "listController", urlPatterns = "/todo/list")
public class ListController extends HttpServlet {

    @Override
    public void init() {
        log.info("init listController...");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        log.info("GET /todo/list HTTP/1.1");
        try {
            List<TodoDto> todoDtoList = TodoService.INSTANCE.getList();
            req.setAttribute("todoList", todoDtoList);
            req.getRequestDispatcher("/WEB-INF/todo/list.jsp")
                    .forward(req, resp);
//            log.info("after forward");
        } catch (SQLException e) {
            ServletUtil.handleException("getList error", e);
        }
    }
}
