/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.WhiskeyData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * WhiskeyDataManagerTest class 
 * JUnit5 tests of next(), previous() methods
 * 
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class WhiskeyDataManagerTest {
    
    public WhiskeyDataManagerTest() {
    }
    
    //Tesing next() method
    
    //No records - empty array when invoking next()
    @Test
    public void nextNoRecordsTest() {
        System.out.println("Next - No records found");
        
        //database intependent test so passing null   
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        
        //Setting empty array
        WhiskeyData.WhiskeyDetails[] details = {};
        wdm.setDetails(details);
        
        //Expect null returned when no records found.
        assertEquals(null,wdm.next());        
        
        //Confirms array length is zero
        assertEquals(0, details.length); 
    }

    //1 record only - same record displayed when invoking next()
    @Test
    public void nextOneRecordTest() {
        System.out.println("Next - Same 1 record displayed.");
        
        //sample record
        WhiskeyData.WhiskeyDetails sampleRecord = new WhiskeyData.WhiskeyDetails("distilery_x", 17, "region_y", 500);
        
        //database intependent test so passing null   
        WhiskeyDataManager wdm = new WhiskeyDataManager(null); //database intependent test so passing null   
        
        //Setting the record
        WhiskeyData.WhiskeyDetails[] details = {sampleRecord};        
        wdm.setDetails(details);
        
        //1st invokation of next() returns the record
        WhiskeyData.WhiskeyDetails firstInvNext = wdm.next();        
        assertEquals(sampleRecord, firstInvNext);     
        
        //2nd invokation of next() returns the same record (cycles forwards)
        WhiskeyData.WhiskeyDetails secondInvNext = wdm.next();        
        assertEquals(sampleRecord, secondInvNext);     
    }
    
    //2 records - cycling 2 records when invoking next()
    @Test
    public void nextTwoRecordsTest() {
        System.out.println("Next - Cycling 2 records.");
        
        //sample records
        WhiskeyData.WhiskeyDetails sampleRecord1 = new WhiskeyData.WhiskeyDetails("distilery_x", 17, "region_y", 500);
        WhiskeyData.WhiskeyDetails sampleRecord2 = new WhiskeyData.WhiskeyDetails("distilery_z", 10, "region_m", 255);
        
        //database intependent test so passing null   
        WhiskeyDataManager wdm = new WhiskeyDataManager(null); //database intependent test so passing null   
        
        //Setting the records
        WhiskeyData.WhiskeyDetails[] details = {sampleRecord1, sampleRecord2};        
        wdm.setDetails(details);        
        
        //confirm the current record is 1st indeed
        WhiskeyData.WhiskeyDetails currentRecord = wdm.first();        
        assertEquals(sampleRecord1, currentRecord);     

        //1st invokation of next() returns 2nd record 
        WhiskeyData.WhiskeyDetails firstInvNext = wdm.next();        
        assertEquals(sampleRecord2, firstInvNext);     
        
        //2nd invokation of next() returns 1st record, cycles back  
        WhiskeyData.WhiskeyDetails secondInvNext = wdm.next();        
        assertEquals(sampleRecord1, secondInvNext);    
    }
    
    //cycling multiple records when invoking next()
    @Test
    public void nextMultipleCyclesTest() {
        System.out.println("Next - Multiple cycling.");
        
        //sample records
        WhiskeyData.WhiskeyDetails sampleRecord1 = new WhiskeyData.WhiskeyDetails("distilery_x", 17, "region_y", 500);
        WhiskeyData.WhiskeyDetails sampleRecord2 = new WhiskeyData.WhiskeyDetails("distilery_z", 10, "region_m", 255);
        
        //database intependent test so passing null   
        WhiskeyDataManager wdm = new WhiskeyDataManager(null); //database intependent test so passing null   
        
        //Setting the records
        WhiskeyData.WhiskeyDetails[] details = {sampleRecord1, sampleRecord2};        
        wdm.setDetails(details);        
        
        //cycle 3 times - multiple cycling
        for (int i = 0; i < 3; i++) {
             assertEquals(sampleRecord2, wdm.next()); 
             assertEquals(sampleRecord1, wdm.next());
        }
    }
    
   //null or empty array when invoking next()
    @Test
    public void nextNullArrayTest() {      
        
        //database intependent test so passing null   
        WhiskeyDataManager wdm = new WhiskeyDataManager(null); //database intependent test so passing null   
        
        //invoking next() 
        WhiskeyData.WhiskeyDetails result = wdm.next();
        
        //Expecting null when details array not set
        assertNull(result, "Expect null when details array not set");        
    } 
    
    //Tesing previous() method
    
    //No records - empty array when invoking previous()
    @Test
    public void previousNoRecordsTest() {
        System.out.println("Previous - No records found");
        
        //database intependent test so passing null   
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        
        //Setting empty array
        WhiskeyData.WhiskeyDetails[] details = {};
        wdm.setDetails(details);
        
        //Expect null returned when no records found.
        assertEquals(null,wdm.previous());        
        
        //Confirms array length is zero
        assertEquals(0, details.length); 
    }

    //1 record only - same record displayed when invoking previous()
    @Test
    public void previousOneRecordTest() {
        System.out.println("Previous - Same 1 record displayed.");
        
        //sample record
        WhiskeyData.WhiskeyDetails sampleRecord = new WhiskeyData.WhiskeyDetails("distilery_x", 17, "region_y", 500);
        
        //database intependent test so passing null   
        WhiskeyDataManager wdm = new WhiskeyDataManager(null); //database intependent test so passing null   
        
        //Setting the record
        WhiskeyData.WhiskeyDetails[] details = {sampleRecord};        
        wdm.setDetails(details);
        
        //1st invokation of previous() returns same record
        WhiskeyData.WhiskeyDetails firstInvPrevious = wdm.previous();        
        assertEquals(sampleRecord, firstInvPrevious);     
        
        //2nd invokation of previous() returns the same record (cycles back)
        WhiskeyData.WhiskeyDetails secondInvPrevious = wdm.previous();        
        assertEquals(sampleRecord, secondInvPrevious);     
    }
    
    //2 records - cycling 2 records when invoking previous()
    @Test
    public void previousTwoRecordsTest() {
        System.out.println("Previous - Cycling 2 records.");
        
        //sample records
        WhiskeyData.WhiskeyDetails sampleRecord1 = new WhiskeyData.WhiskeyDetails("distilery_x", 17, "region_y", 500);
        WhiskeyData.WhiskeyDetails sampleRecord2 = new WhiskeyData.WhiskeyDetails("distilery_z", 10, "region_m", 255);
        
        //database intependent test so passing null   
        WhiskeyDataManager wdm = new WhiskeyDataManager(null); //database intependent test so passing null   
        
        //Setting the records
        WhiskeyData.WhiskeyDetails[] details = {sampleRecord1, sampleRecord2};        
        wdm.setDetails(details);        
        
        //confirm the current record is 1st indeed
        WhiskeyData.WhiskeyDetails currentRecord = wdm.first();        
        assertEquals(sampleRecord1, currentRecord);     

        //1st invokation of previous() returns last record in the details array 
        WhiskeyData.WhiskeyDetails firstInvPrevious = wdm.previous();        
        assertEquals(sampleRecord2, firstInvPrevious);     
        
        //2nd invokation of previous() returns 1st record, cycles back  
        WhiskeyData.WhiskeyDetails secondInvPrevious = wdm.previous();        
        assertEquals(sampleRecord1, secondInvPrevious);    
    }
   
    
    //cycling multiple records when invoking previous().
    @Test
    public void previousMultipleCyclesTest() {
        System.out.println("Previous - Multiple cycling.");
        
        //sample records
        WhiskeyData.WhiskeyDetails sampleRecord1 = new WhiskeyData.WhiskeyDetails("distilery_x", 17, "region_y", 500);
        WhiskeyData.WhiskeyDetails sampleRecord2 = new WhiskeyData.WhiskeyDetails("distilery_z", 10, "region_m", 255);
        WhiskeyData.WhiskeyDetails sampleRecord3 = new WhiskeyData.WhiskeyDetails("distilery_b", 22, "region_q", 346);
        
        //database intependent test so passing null   
        WhiskeyDataManager wdm = new WhiskeyDataManager(null); //database intependent test so passing null   
        
        //Setting the records
        WhiskeyData.WhiskeyDetails[] details = {sampleRecord1, sampleRecord2, sampleRecord3};        
        wdm.setDetails(details);        
        
        //cycle 3 times - multiple cycling
        for (int i = 0; i < 3; i++) {
            assertEquals(sampleRecord3, wdm.previous());//1st invokation, from 1st to last record in array
            assertEquals(sampleRecord2, wdm.previous());//2nd - previous record 
            assertEquals(sampleRecord1, wdm.previous());//3rd - previous record 
        }
    }
     
   //null or empty array
    @Test
    public void previousNullArrayTest() {      
        
        //database intependent test so passing null   
        WhiskeyDataManager wdm = new WhiskeyDataManager(null); //database intependent test so passing null   
        
        //invoking previous() 
        WhiskeyData.WhiskeyDetails result = wdm.previous();
        
        //Expecting null when details array not set
        assertNull(result, "Expect null when details array not set");        
    }
    
}//end WhiskeyDataManagerTest
