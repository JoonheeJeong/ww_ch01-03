package org.zerock.w1.todo.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
@Slf4j
public class LoginListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String name = event.getName();
        if (name.equals("loginInfo")) {
            Object loginInfo = event.getValue();
            log.info(String.format("User{%s} logged in!", loginInfo));
        }
    }
}
