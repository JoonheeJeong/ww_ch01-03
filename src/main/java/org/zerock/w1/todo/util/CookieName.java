package org.zerock.w1.todo.util;

public enum CookieName {
    REMEMBER_ME("remember-me"),
    VIEW_TODO_LIST("view-todo-list");

    private final String name;

    CookieName(String name) {
        this.name = name;
    }
}
