package ge.tbcacad.data.dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ge.tbcacad.data.dbconnection.MSSQLConnectoion.connect;

public class DatabaseSteps {
    public static String getLogin(int ID) throws SQLException {
        String selectSQL = """
                SELECT Login FROM SaucedemoCredentials WHERE ID = ?
                """;
        try (Connection conn = connect()){
            PreparedStatement selectStmt = conn.prepareStatement(selectSQL);
            selectStmt.setInt(1, ID);
            try (ResultSet resultSet = selectStmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("Login");
                } else {
                    throw new IllegalArgumentException("No Login found with ID: " + ID);
                }
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPassword(int ID) throws SQLException {
        String selectSQL = """
                SELECT Password FROM SaucedemoCredentials WHERE ID = ?
                """;
        try (Connection conn = connect()){
            PreparedStatement selectStmt = conn.prepareStatement(selectSQL);
            selectStmt.setInt(1, ID);
            try (ResultSet resultSet = selectStmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("Password");
                } else {
                    throw new IllegalArgumentException("No Password found with ID: " + ID);
                }
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
