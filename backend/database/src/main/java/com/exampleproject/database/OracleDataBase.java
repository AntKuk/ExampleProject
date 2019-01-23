package com.exampleproject.database;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Map;
import java.util.Queue;


@Component
public class OracleDataBase implements DataBase<Map<String,? super Object>> {

    protected void checkOracleConnection() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }

    protected PreparedStatement prepareStatement(final String query) throws SQLException {
            Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.112.252.135:1524:DBG129",
                "U234_DEV_6300",
                "U234_DEV_6300");
            return connection.prepareStatement(query);
    }

    public Map<String, ? super Object> select(String query, Queue<? super Object> parameters) {
        try {
            checkOracleConnection();
            PreparedStatement preparedStatement = prepareStatement(query);

            for(int i=0; i<= parameters.size(); i++) {
                preparedStatement.setObject(i,parameters.poll());
            }
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public boolean execute(String query, Queue<? super Object> parameters) {
        return false;
    }
}
