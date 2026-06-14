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
 * WhiskeyData class
 *
 * Database operations for the whiskey database. Retrieves records based on the
 * following criteria: a. All records b. Specified region c. Specified age range
 *
 * Uses JDBC
 *
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class WhiskeyData {

    /**
     * Record of malts details retrieved from whiskey DB
     *
     * @param distillery spirit`s distillery
     * @param age spirit`s age
     * @param region spirit`s region
     * @param price spirit`s price
     */
    public record WhiskeyDetails(String distillery, int age, String region, int price) {

        @Override
        public String toString() {
            return String.format(
                    "Distillery: %s\nAge:  %d years\nRegion: %s\nPrice: %d",
                    distillery, age, region, price
            );
        }
    }

    //DB connection object
    private Connection connection;

    //Prepared Statements    
    //Select all malts
    private final String selectAllMalts = "SELECT * from singlemalts";
    private PreparedStatement psSelectAllMalts = null;

    //Select from specified region
    private final String selectFromRegion = """
                SELECT *
                FROM singlemalts
                WHERE region = ?;
    """;
    private PreparedStatement psSelectFromRegion = null;

    //Select malsts in age range
    private final String selectMaltsInRange = """
                SELECT *
                FROM singlemalts
                WHERE age >= ? AND age <= ?;
    """;
    private PreparedStatement psSelectMaltsInRange = null;

    /**
     * Default Constructor for WhiskeyData class
     */
    public WhiskeyData() {
    }

    /**
     * Connects to whiskey DB. On failure, error is printed and the app exits.
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/whiskey", "root", "mkawebdev");

            //Prepare all statements if connected to DB            
            if (connection != null) {
                psSelectAllMalts = connection.prepareStatement(selectAllMalts);
                psSelectFromRegion = connection.prepareStatement(selectFromRegion);
                psSelectMaltsInRange = connection.prepareStatement(selectMaltsInRange);

            }
        } catch (SQLException e) {
            throw new IllegalStateException(
                    "The Whiskey database could not be reached. Please check that MySQL is running, the whiskey database exists, and the database connection settings are correct.",
                    e
            );
        }
    }

    /**
     * Disconnection of the existing connection to whiskey DB. On failure, error
     * is printed and the app exits.
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
     * Retrieves a list of all malts from whiskey DB
     *
     * @return the list of all malts
     */
    public List<WhiskeyDetails> getAllMalts() {
        List<WhiskeyDetails> whiskeyDetailsList = new ArrayList<>();
        ResultSet result = null;

        try {
            result = psSelectAllMalts.executeQuery();
            while (result.next()) {
                //Get each column and map it to the list
                String distillery = result.getString("distillery");
                int age = result.getInt("age");
                String region = result.getString("region");
                int price = result.getInt("price");

                //create WhiskeyDetails object 
                WhiskeyDetails whiskeyDetails = new WhiskeyDetails(distillery, age, region, price);
                whiskeyDetailsList.add(whiskeyDetails);
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
        return whiskeyDetailsList;
    }

    /**
     * Retrieves a list of malts from whiskey DB in a specified region
     *
     * @param r specified region
     * @return the list of malts in specified region
     */
    public List<WhiskeyDetails> getMaltsFromRegion(String r) {
        List<WhiskeyDetails> maltsFromRegionList = new ArrayList<>();
        ResultSet result = null;

        try {
            psSelectFromRegion.setString(1, r);
            result = psSelectFromRegion.executeQuery();
            while (result.next()) {
                //Get each column and map it to the list
                String distillery = result.getString("distillery");
                int age = result.getInt("age");
                String region = result.getString("region");
                int price = result.getInt("price");

                //create WhiskeyDetails object 
                WhiskeyDetails whiskeyDetails = new WhiskeyDetails(distillery, age, region, price);
                maltsFromRegionList.add(whiskeyDetails);
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
        return maltsFromRegionList;
    }

    /**
     * Retrieves a list of malts from whiskey DB in a specified age range
     *
     * @param r1 minimum age
     * @param r2 maximum age
     * @return the list of malts in a specified age range between r1 and r2
     */
    public List<WhiskeyDetails> getMaltsInAgeRange(int r1, int r2) {
        List<WhiskeyDetails> maltsInRangeList = new ArrayList<>();
        ResultSet result = null;

        try {

            psSelectMaltsInRange.setInt(1, r1);
            psSelectMaltsInRange.setInt(2, r2);
            result = psSelectMaltsInRange.executeQuery();

            while (result.next()) {
                //Get each column and map it to the list
                String distillery = result.getString("distillery");
                int age = result.getInt("age");
                String region = result.getString("region");
                int price = result.getInt("price");

                //create WhiskeyDetails object 
                WhiskeyDetails whiskeyDetails = new WhiskeyDetails(distillery, age, region, price);
                maltsInRangeList.add(whiskeyDetails);
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

        return maltsInRangeList;
    }

}//end WhiskeyData class
