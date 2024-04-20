package ge.tbcacad.data.dbconnection;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MSSQLConnectoion {
    public static Connection connect() throws SQLException {
        try {
            DriverManager.registerDriver(new SQLServerDriver());

            String jdbcUrl = DBConfig.getURL();
            String password = DBConfig.getPassword();
            String username = DBConfig.getUsername();

            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
