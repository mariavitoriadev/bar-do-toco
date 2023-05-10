package Dao;

import java.sql.*;

public class ConnectionFactory implements AutoCloseable {
    private static PreparedStatement stmt = null;
    private static Connection conn = null; 
    
    public static Connection createConnection() {
        try {
            if (conn == null){
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:dados.db");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return conn;
    }

    public static PreparedStatement createStatement(String sql) {
        try {
            stmt = createConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    @Override
    public void close() throws Exception {
        if (conn != null)
            conn.close();
        if (stmt != null)
            stmt.close();
    }

}
