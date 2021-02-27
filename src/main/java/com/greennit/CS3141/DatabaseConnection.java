package com.greennit.CS3141;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * This class will be used to search the database by creating a connection and allowing a String input in the form of a SQL select query
 */
public class DatabaseConnection {

    /**
     * This method will create a connection to the database and execute queries on it
     * @param input - The line to be executed on the database
     * @param output - tracks if a return statement is needed
     * @param column - the column the data is in or null if not needing an output
     * @return - The output of the query if there is one, or null if an output wasn't need
     */
    public String query(String input, boolean output, String column) {
        Connection connection = null;
        String user = "admin";
        try {
            String url = "jdbc:mysql://database-1.cf1ohk6cad1q.us-east-2.rds.amazonaws.com:3306/greennit";
            connection = DriverManager.getConnection(url,user,getPass());
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(input);
            if(output) { //Checks if an output needs to be returned
                set.next(); //Goes to the first row in the table
                String result = set.getString(column); //Gets the string at the desired column
                statement.close(); //Closes the statement
                return result;
            }else{ //Returns null if not
                return null;
            }
        } catch (SQLException | FileNotFoundException e) {
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

    /**
     * Used to retrieve the password from your local machine
     * @return password for the database
     * @throws FileNotFoundException - file not at current pathname
     */
    private String getPass() throws FileNotFoundException {
        File file = new File(""); //Put location of txt file here that stores the password
        Scanner scan = new Scanner(file);
        String password = scan.nextLine();
        scan.close();
        return password;
    }

}
