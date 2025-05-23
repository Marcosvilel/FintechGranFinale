package br.com.fiap.fintech.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionManager {

    private static OracleConnectionManager connectionManager;

//    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
//    private static final String USER = "RM560368";
//    private static final String PASSWORD = "250196";
//
//    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";



    public static OracleConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new OracleConnectionManager();
        }
        return connectionManager;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "RM560645", "220406");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
