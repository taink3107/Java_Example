package demo_JDBC;

import java.sql.*;

public class ResultMetaDataSetMetaData {
    public static void main(String args[]) throws Exception {
        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection
        String mysqlUrl = "jdbc:mysql://localhost/TestDB";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "password");
        System.out.println("Connection established......");
        //Creating a Statement object
        Statement stmt = con.createStatement();
        //Retrieving the data
        ResultSet rs = stmt.executeQuery("select * from Dataset");
        ResultSetMetaData rsMetaData = rs.getMetaData();
        //Number of columns
        System.out.println("Number of columns: "+rsMetaData.getColumnCount());
        //Column label
        System.out.println("Column Label: "+rsMetaData.getColumnLabel(1));
        //Column name
        System.out.println("Column Name: "+rsMetaData.getColumnName(1));
        //Number of columns
        System.out.println("Table Name: "+rsMetaData.getTableName(1));
    }
}
