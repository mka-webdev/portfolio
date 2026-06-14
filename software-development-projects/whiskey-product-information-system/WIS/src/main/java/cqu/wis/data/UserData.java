/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserData class
 *
 * Database operations for the users database. Uses JDBC
 *
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class UserData {

    /**
     * Record of user details retrieved from users DB
     *
     * @param user username
     * @param password password (plain or hashed)
     */
    public record UserDetails(String user, String password) {

    }

    //DB connection object
    private Connection connection;

    //Prepared Statements  
    //Locate user in users DB
    private final String selectUserDeatails = """
                SELECT username, password
                FROM passwords
                WHERE username = ?;
    """;
    private PreparedStatement psSelectUserDeatails = null;

    //Update password in databse
    private final String updatePassword = """
                UPDATE passwords
                SET password = ? 
                WHERE username = ?
    """;
    private PreparedStatement psUpdatePassword = null;

    /**
     * Default constructor for UserData class
     */
    public UserData() {
    }

    /**
     * Connection to users DB. On failure, error is printed and the app exits.
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "mkawebdev");

            //Prepare all statements if connected to DB            
            if (connection != null) {
                psSelectUserDeatails = connection.prepareStatement(selectUserDeatails);
                psUpdatePassword = connection.prepareStatement(updatePassword);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "The users database could not be reached. Please check that MySQL is running, the users database exists, and the database connection settings are correct.",
                    e
            );
        }
    }

    /**
     * Disconnection of the existing connection to usersDB. On failure, error is
     * printed and the app exits.
     */
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Could`n disconnect properly from DB" + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Retrieves a list of user details for specified username
     *
     * @param n the username
     * @return the list of UserDetails objects. Empty list is returned if a user
     * not found.
     */
    public List<UserDetails> getUser(String n) {
        List<UserDetails> userDeatailsList = new ArrayList<>();
        ResultSet result = null;

        try {
            psSelectUserDeatails.setString(1, n);
            result = psSelectUserDeatails.executeQuery();

            while (result.next()) {
                //Get each column and map it to the list
                String username = result.getString("username");
                String password = result.getString("password");
                userDeatailsList.add(new UserDetails(username, password));
            }
        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            System.exit(1);

        } finally {

            //close ResultSet
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    System.err.println("Error closing result: " + e.getMessage());
                    System.exit(1);
                }
            }
        }
        return userDeatailsList;
    }

    /**
     * Updates password for the specified user in users DB
     *
     * @param n specified username
     * @param p new password to set
     * @return number of affected rows: 1 if update is OK, 0 if record not
     * found.
     */
    public int updatePassword(String n, String p) {
        try {
            //following the query logic, update password where username...
            psUpdatePassword.setString(1, p);
            psUpdatePassword.setString(2, n);
            return psUpdatePassword.executeUpdate();//returns number of affected rows
        } catch (SQLException e) {
            System.err.println("Password update failed: " + e.getMessage());
            return 0;
        }
    }

}//end UserData class
