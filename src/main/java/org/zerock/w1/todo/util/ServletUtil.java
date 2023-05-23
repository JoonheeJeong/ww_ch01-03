package org.zerock.w1.todo.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class ServletUtil {

    public static Optional<Cookie> findCookie(Cookie[] cookies, CookieName cookieName) {
        String key = cookieName.name();
        return Arrays.stream(cookies)
                .filter(ck -> ck.getName().equals(key))
                .findFirst();
    }

    public static void handleException(String msg, SQLException e) throws ServletException {
        log.error(e.getMessage());
        log.error(e.getSQLState());
        e.printStackTrace();
        throw new ServletException(msg, e);
    }
}
