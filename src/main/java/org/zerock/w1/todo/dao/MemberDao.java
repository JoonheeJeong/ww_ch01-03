package org.zerock.w1.todo.dao;

import lombok.Cleanup;
import org.zerock.w1.todo.domain.MemberVo;
import org.zerock.w1.todo.domain.TodoVo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum MemberDao {
    INSTANCE;

    private final ConnectionUtil connectionUtil;

    MemberDao() {
        connectionUtil = ConnectionUtil.INSTANCE;
    }

    public MemberVo selectOne(String id) throws SQLException {
        String sql = "select * from member where id = ?";

        @Cleanup Connection conn = connectionUtil.getConnection();
        @Cleanup PreparedStatement prepStmt = conn.prepareStatement(sql);

        prepStmt.setString(1, id);
        @Cleanup ResultSet resultSet = prepStmt.executeQuery();

        resultSet.next();

        return MemberVo.builder()
                .id(resultSet.getString(1))
                .pw(resultSet.getString(2))
                .name(resultSet.getString(3))
                .build();
    }

    public MemberVo selectOneWithPassword(String id, String pw) throws SQLException {
        String sql = "select * from member where id = ? and pw = ?";
        @Cleanup Connection conn = connectionUtil.getConnection();
        @Cleanup PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setString(1, id);
        prepStmt.setString(2, pw);

        @Cleanup ResultSet resultSet = prepStmt.executeQuery();
        resultSet.next();
        MemberVo vo = MemberVo.builder()
                .id(resultSet.getString(1))
                .pw(resultSet.getString(2))
                .name(resultSet.getString(3))
                .build();
        return vo;
    }

    public void updateUuid(String id, String uuid) throws SQLException {
        String sql = "update member set uuid = ? where id = ?";
        @Cleanup Connection conn = connectionUtil.getConnection();
        @Cleanup PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setString(1, uuid);
        prepStmt.setString(2, id);
        prepStmt.executeUpdate();
    }

    public MemberVo selectOneByUuid(String uuid) throws SQLException {
        String sql = "select id, pw, name from member where uuid = ?";
        @Cleanup Connection conn = connectionUtil.getConnection();
        @Cleanup PreparedStatement prepStmt = conn.prepareStatement(sql);
        prepStmt.setString(1, uuid);

        @Cleanup ResultSet resultSet = prepStmt.executeQuery();
        resultSet.next();
        return MemberVo.builder()
                .id(resultSet.getString(1))
                .pw(resultSet.getString(2))
                .name(resultSet.getString(3))
                .uuid(uuid)
                .build();
    }
}
