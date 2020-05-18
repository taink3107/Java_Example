package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    public static final String username = "root";
    public static final String password = "123456789";
    public static final String db = "db_taink";
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/"+db+"?useSSL=false"; //get local timezone
        try
        {
            return DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(JDBC.getConnection());
    }
}
