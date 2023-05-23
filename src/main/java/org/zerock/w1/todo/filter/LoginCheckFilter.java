package org.zerock.w1.todo.filter;

import lombok.extern.slf4j.Slf4j;
import org.zerock.w1.todo.dto.MemberDto;
import org.zerock.w1.todo.service.MemberService;
import org.zerock.w1.todo.util.CookieName;
import org.zerock.w1.todo.util.ServletUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebFilter(urlPatterns = { "/todo/*" })
@Slf4j
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.info("Login Check Filter...");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        boolean loginInfoExists = session.getAttribute("loginInfo") != null;
        if (loginInfoExists) {
            log.info("loginInfo exists.");
            chain.doFilter(request, response);
            return;
        }

        Optional<Cookie> cookieOptional = ServletUtil.findCookie(req.getCookies(), CookieName.REMEMBER_ME);
        if (cookieOptional.isEmpty()) {
            log.info("'remember-me' cookie is empty. Required to login.");
            resp.sendRedirect("/login");
            return;
        }

        Cookie ck = cookieOptional.get();
        String uuid = ck.getValue();
        try {
            log.info("'remember-me' cookie was found.");
            MemberDto dto = MemberService.INSTANCE.getByUuid(uuid);
            session.setAttribute("loginInfo", dto);
            chain.doFilter(request, response);
        } catch (SQLException e) {
            ServletUtil.handleException("invalid uuid", e);
        }
    }
}
