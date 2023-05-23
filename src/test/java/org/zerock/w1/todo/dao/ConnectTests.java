package org.zerock.w1.todo.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectTests {

    @Test
    public void testConnection() throws Exception {
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

        Assertions.assertNotNull(connection);

        connection.close();
    }
}
