package org.zerock.w1.todo.dao;

import lombok.Cleanup;
import org.zerock.w1.todo.domain.TodoVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {

    public String getTime() throws SQLException {
        String now = null;
        try (Connection conn = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement prepStmt = conn.prepareStatement("select now()");
             ResultSet resultSet = prepStmt.executeQuery()
        ) {
            resultSet.next();
            now = resultSet.getString(1);
        }
        return now;
    }

    public String getTimeWithCleanup() throws SQLException {
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement prepStmt = conn.prepareStatement("select now()");
        @Cleanup ResultSet resultSet = prepStmt.executeQuery();

        resultSet.next();
        String now = resultSet.getString(1);

        return now;
    }

    public void insert(TodoVo vo) throws SQLException {
        String sql = "insert into tbl_todo (title, dueDate, finished) values (?, ?, ?)";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement prepStmt = conn.prepareStatement(sql);

        prepStmt.setString(1, vo.getTitle());
        prepStmt.setDate(2, Date.valueOf(vo.getDueDate()));
        prepStmt.setBoolean(3, vo.isFinished());

        prepStmt.executeUpdate();
    }

    public List<TodoVo> selectAll() throws SQLException {
        String sql = "select * from tbl_todo";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement prepStmt = conn.prepareStatement(sql);
        @Cleanup ResultSet resultSet = prepStmt.executeQuery();

        List<TodoVo> list = new ArrayList<>();
        while (resultSet.next() ) {
            TodoVo vo = TodoVo.builder()
                    .tno(resultSet.getLong(1))
                    .title(resultSet.getString(2))
                    .dueDate(resultSet.getDate(3).toLocalDate())
                    .finished(resultSet.getBoolean(4))
                    .build();
            list.add(vo);
        }
        return list;
    }

    public TodoVo selectOne(Long tno) throws SQLException {
        String sql = "select * from tbl_todo where tno = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setLong(1, tno);

        @Cleanup ResultSet resultSet = prepStmt.executeQuery();

        resultSet.next();
        return TodoVo.builder()
                    .tno(resultSet.getLong(1))
                    .title(resultSet.getString(2))
                    .dueDate(resultSet.getDate(3).toLocalDate())
                    .finished(resultSet.getBoolean(4))
                    .build();
    }

    public void deleteOne(Long tno) throws SQLException {
        String sql = "delete from tbl_todo where tno = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setLong(1, tno);

        prepStmt.executeUpdate();
    }

    public void updateOne(TodoVo vo) throws SQLException {
        String sql = "update tbl_todo set title = ?, dueDate = ?, finished = ? where tno = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setString(1, vo.getTitle());
        prepStmt.setDate(2, Date.valueOf(vo.getDueDate()));
        prepStmt.setBoolean(3, vo.isFinished());
        prepStmt.setLong(4, vo.getTno());

        prepStmt.executeUpdate();
    }
}
