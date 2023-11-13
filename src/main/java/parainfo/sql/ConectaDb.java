package parainfo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDb {

    public Connection getConnection() throws SQLException {
        Connection cn = null;

        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e.getMessage());
        }

        return cn;
    }

    private final String url = "jdbc:mysql://localhost:3306/ventas?serverTimezone=UTC"; // MySQL8
    private final String driver = "com.mysql.cj.jdbc.Driver"; // MySQL8
    private final String user = "root";
    private final String password = "admin";
}
