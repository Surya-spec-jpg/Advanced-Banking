import java.sql.Connection;
import java.sql.DriverManager;

public class getConnection {
    public static Connection getconnection() {
        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bank", "root", "surya@2003");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
