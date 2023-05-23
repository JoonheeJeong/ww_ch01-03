package org.zerock.w1.todo.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w1.todo.domain.MemberVo;

import java.sql.SQLException;

@Slf4j
public class MemberDaoTests {

    MemberDao dao;

    @BeforeEach
    public void ready() {
        dao = MemberDao.INSTANCE;
    }

    @Test
    public void testSelectOne() throws SQLException {
        log.info("MemberDao testSelectOne()");
        String id = "user00";
        MemberVo vo = dao.selectOne(id);
        Assertions.assertEquals(id, vo.getId());
    }
}
