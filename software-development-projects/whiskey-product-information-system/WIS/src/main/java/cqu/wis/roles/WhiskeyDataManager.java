/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.WhiskeyData;
import cqu.wis.data.WhiskeyData.WhiskeyDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * WhiskeyDataManager class
 * 
 * Links between the app and the WhiskeyData class. 
 * Business logic related to malts search: 
 * a. all malts
 * b. by age range
 * c. by malt region * 
 * 
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class WhiskeyDataManager {

    private WhiskeyData wd;
    private List<WhiskeyDetails> records;
    private int numberOfRecords;
    private int currentIndex;
    private WhiskeyDetails currentRecord;

    /**
     * Overridden constructor for the WhiskeyDataManager class. 
     * Constructs WhiskeyDataManager using specified whiskeyData instance.   
     * 
     * @param wd specified WhiskeyData instance.
     * 
     */
    public WhiskeyDataManager(WhiskeyData wd) {
        this.wd = wd; //asign WhiskeyData object 
        this.records = new ArrayList<>();  //init. list to store records       
    }

    /**
     * Retrieves all malt records (internal non DB storage)
     * 
     * @return number of found records
     */
    public int findAllMalts() {
        this.records = wd.getAllMalts();//gets whiskeyDetailsList
        this.numberOfRecords = records.size();

        if (numberOfRecords > 0) {
            this.currentIndex = 0; //index of the first record
            this.currentRecord = records.get(currentIndex);//sets current record to the 1st            
        } else {
            this.currentIndex = -1; //if no records
            this.currentRecord = null;//No current record
        }
        return numberOfRecords;
    }

    /**
     * Retrieves records of malts from the specified region
     * 
     * @param r specified region 
     * @return number of records found
     */
    public int findMaltsFromRegion(String r) {       
                
        this.records = wd.getMaltsFromRegion(r);//gets maltsFromRegionList
        this.numberOfRecords = records.size();

        if (numberOfRecords > 0) {
            this.currentIndex = 0; //index of the first record
            this.currentRecord = records.get(currentIndex);//sets current record to the 1st            
        } else {            
            this.currentIndex = -1; //if no records
            this.currentRecord = null;//No current record
        }
        return numberOfRecords;
    }

    /**
     * Retrieves records of malts from the specified age range
     * 
     * @param r1 minimum age
     * @param r2 maximum age      
     * @return number of records found
     */
    public int findMaltsInAgeRange(int r1, int r2) {
        this.records = wd.getMaltsInAgeRange(r1, r2);//gets maltsInRangeList
        this.numberOfRecords = records.size();

        if (numberOfRecords > 0) {
            this.currentIndex = 0; //index of the first record
            this.currentRecord = records.get(currentIndex);//sets current record to the 1st            
        } else {
            this.currentIndex = -1; //if no records
            this.currentRecord = null;//No current record
        }
        return numberOfRecords;
    }

    /**
     * Retrieves the first WhiskeyDetails record in the current list
     * 
     * @return this record as object 
     */
    public WhiskeyDetails first() {
        if (!records.isEmpty()) {
            currentIndex = 0;
            currentRecord = records.get(currentIndex);
        } else {
            currentRecord = null;// if no records
        }
        return currentRecord;
    }

    /**
     * Retrieves the next WhiskeyDetails record in the current list,
     * wrapping around from last to first
     * 
     * @return this record as object 
     * @throws IllegalArgumentException if records list not initiated 
     */
    public WhiskeyDetails next() {
        //prevent errors if the list is empty or null
        if (records == null) {
            throw new IllegalArgumentException("Details array is not set.");
        }

        if (records.isEmpty()) {
            return null;
        }

        //next record position in the list
        currentIndex++;

        if (currentIndex >= numberOfRecords) {
            currentIndex = 0;
        }
        currentRecord = records.get(currentIndex);
        return currentRecord;
    }

    /**
     * Retrieves the previous WhiskeyDetails record in the current list,
     * wrapping around from first to last
     * 
     * @return this record as object 
     * @throws IllegalArgumentException if records list not initiated 
     */
    public WhiskeyDetails previous() {
        //prevent errors if the list is empty or null
        if (records == null) {
            throw new IllegalArgumentException("Details array is not set.");
        }

        if (records.isEmpty()) {
            return null;
        }

        //previous record position in the list
        currentIndex--;

        if (currentIndex < 0) {
            currentIndex = numberOfRecords - 1;//cycling effect, record n to record n-1, and first record to last  
        }
        currentRecord = records.get(currentIndex);
        return currentRecord;
    }

    /**
     * Connects with WhiskeyData source
     */
    public void connect() {
        wd.connect();
    }

    /**
     * Disconnects from the WhiskeyData source
     */
    public void disconnect() {
        wd.disconnect();
    }

    /**
     * Method used in JUnit5 testing of the WhiskeyDataManager class.
     * 
     * @param details Details object constructed using sample data in tests  
     */
    public void setDetails(WhiskeyDetails[] details) {
        List <WhiskeyDetails> list = Arrays.asList(details);
        records = new ArrayList<>(list);
        numberOfRecords = records.size();
        currentIndex = (numberOfRecords == 0) ? -1 : 0;
        currentRecord = (numberOfRecords == 0) ? null : records.get(0);
    }

}//end WhiskeyDataManager class


