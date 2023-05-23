package org.zerock.w1.todo.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = { "/*" })
@Slf4j
public class UTF8Filter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.info("UTF-8 Filter...");
        request.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }
}
