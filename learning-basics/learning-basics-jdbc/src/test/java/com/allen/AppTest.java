package com.allen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
    /**
     * Rigorous Test :-)
     */
    @Test
    @SneakyThrows
    public void test() {
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/facebook", "root", "root");
        Statement statement = connection.createStatement();
        statement.setFetchSize(2);
        statement.setMaxRows(3);
        ResultSet resultSet = statement.executeQuery("SELECT * from user");
        while (resultSet.next()) {
            System.out.println(resultSet.getObject("name"));
        }
    }
}
