package org.zerock.w1.todo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w1.todo.dto.TodoDto;

import java.sql.SQLException;
import java.time.LocalDate;

public class TodoServiceTests {

    private TodoService service;

    @BeforeEach
    public void ready() {
        service = TodoService.INSTANCE;
    }

    @Test
    public void testRegister() throws SQLException {
        TodoDto dto = TodoDto.builder()
                        .title("JDBC Test Title")
                        .dueDate(LocalDate.now())
                        .build();

        service.register(dto);
    }
}
