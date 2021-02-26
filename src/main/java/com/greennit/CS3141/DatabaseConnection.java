package com.greennit.CS3141;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This class will be used to search the database by creating a connection and allowing a String input in the form of a SQL select query
 */
public class DatabaseConnection {

    public ResultSet query(String search) {
        Connection connection = null;
        String user = "admin";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://database-1.cf1ohk6cad1q.us-east-2.rds.amazonaws.com:3306";
            connection = DriverManager.getConnection(url,user,password);
            if(connection != null){
                System.out.println("Success");
            }
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(search);
            statement.close();
            return set;
        } catch (SQLException | ClassNotFoundException e) {
            throw new Error("Failed: ", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //Testing the connection to the server
    public static void main(String[] args) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        ResultSet result = connection.query("select * from example;");
        System.out.println(result.first());
    }
}
