package org.zerock.w1.todo.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w1.todo.domain.TodoVo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
public class TodoDaoTests {

    private TodoDao dao;

    @BeforeEach
    public void ready() {
        dao = new TodoDao();
    }

    @Test
    public void testGetTime() throws SQLException {
        String now = dao.getTime();
        Assertions.assertNotNull(now);
        log.info("dao testGetTime(): " + now);
    }

    @Test
    public void testGetTimeWithCleanup() throws SQLException {
        String now = dao.getTimeWithCleanup();
        Assertions.assertNotNull(now);
        log.info("dao testGetTimeWithCleanup()" + now);
    }

    @Test
    public void testInsert() throws SQLException {
        TodoVo vo = TodoVo.builder()
                .title("Sample Title...")
                .dueDate(LocalDate.of(2023, 12, 31))
                .build();
        dao.insert(vo);
        log.info("dao testInsert(): " + vo);
    }

    @Test
    public void testSelectAll() throws SQLException {
        log.info("dao testSelectAll()");
        List<TodoVo> list = dao.selectAll();
        list.forEach(vo -> log.info(vo.toString()));
    }

    @Test
    public void testSelectOne() throws SQLException {
        log.info("dao testSelectOne()");
        Long tno = 1L;
        TodoVo vo = dao.selectOne(tno);
        Assertions.assertEquals(vo.getTno(), tno);
    }

    @Test
    public void testDeleteOne() throws SQLException {
        log.info("dao testDeleteOne()");
        List<TodoVo> list = dao.selectAll();
        TodoVo last = list.get(list.size() - 1);

        Long tno = last.getTno();
        dao.deleteOne(tno);

        dao.insert(last);
    }

    @Test
    public void testUpdateOne() throws SQLException {
        log.info("dao testUpdateOne()");
        TodoVo vo = TodoVo.builder()
                        .tno(1L)
                        .title("Sample Title...")
                        .dueDate(LocalDate.of(2023, 11, 30))
                        .finished(true)
                        .build();
        dao.updateOne(vo);
    }
}
