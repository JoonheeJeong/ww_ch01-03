package org.zerock.w1.todo.controller;

import lombok.extern.slf4j.Slf4j;
import org.zerock.w1.todo.service.TodoService;
import org.zerock.w1.todo.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Slf4j
@WebServlet(name = "removeController", urlPatterns = "/todo/remove")
public class RemoveController extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        log.info("POST /todo/remove HTTP/1.1");
        Long tno = Long.parseLong(req.getParameter("tno"));
        try {
            TodoService.INSTANCE.remove(tno);
            resp.sendRedirect("/todo/list");
        } catch (SQLException e) {
            ServletUtil.handleException("remove error", e);
        }
    }

}
