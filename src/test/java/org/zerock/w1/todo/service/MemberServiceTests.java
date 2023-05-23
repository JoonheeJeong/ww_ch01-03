package org.zerock.w1.todo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w1.todo.dto.MemberDto;

import java.sql.SQLException;

public class MemberServiceTests {

    private MemberService service;

    @BeforeEach
    public void ready() {
        service = MemberService.INSTANCE;
    }

    @Test
    public void testLogin() throws SQLException {
        String id = "user00";
        String pw = "user00";
        MemberDto dto = service.login(id, pw);
        Assertions.assertEquals(id, dto.getId());
        Assertions.assertEquals(pw, dto.getPw());
    }
}
