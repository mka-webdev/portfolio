/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.roles.WhiskeyDataValidator.RangeValidationResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * WhiskeyDataValidatorTest class JUnit5 tests of: 
 * a. checkAgeRange(String b1, String b2)
 * b. checkRegion(String r). 
 *
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class WhiskeyDataValidatorTest {

    public WhiskeyDataValidatorTest() {
    }

    //Testing checkAgeRange(String b1, String b2)
   
    //Focus on testing a valid range     
    @Test
    public void checkAgeRangeValidRangeTest() {
        System.out.println("Both bounds are specified within valid range.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response using valid data in both bounds
        RangeValidationResponse response = vld.checkAgeRange("45", "78");

        //Both bounds specified
        assertEquals(45, response.r().lower());
        assertEquals(78, response.r().upper());

        //Expecting true result from validation
        assertTrue(response.result());
    }

    //null test in both bounds
    @Test
    public void checkAgeRangeBothBoundsNullTest() {
        System.out.println("Both bounds are null.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with null in both bounds
        RangeValidationResponse response = vld.checkAgeRange(null, null);

        //Expect relevant error message is triggered 
        assertEquals("Bounds cannot be null.", response.message());

        //Expecting zero range due to null in both bounds
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //null test in lower bound
    @Test
    public void checkAgeRangeLowerBoundNullTest() {
        System.out.println("Lower bound is null.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with null in lower bound
        RangeValidationResponse response = vld.checkAgeRange(null, "5");

        //Expect relevant error message is triggered 
        assertEquals("Lower bound cannot be null.", response.message());

        //Expecting zero range in both bounds due to null in the lower bound
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //null test in upper bound
    @Test
    public void checkAgeRangeUpperBoundNullTest() {
        System.out.println("Upper bound is null.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with null in upper bound
        RangeValidationResponse response = vld.checkAgeRange("45", null);

        //Expect relevant error message is triggered 
        assertEquals("Upper bound cannot be null.", response.message());

        //Expecting zero range in both bounds due to null in the upper bound 
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //Case when both bounds are empty 
    @Test
    public void checkAgeRangeBothBoundsEmptyTest() {
        System.out.println("Both bounds empty.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with both bounds empty
        RangeValidationResponse response = vld.checkAgeRange("", "");

        //Expect relevant error message is triggered 
        assertEquals("At least one field must be specified.", response.message());

        //Both input fields are empty, so expecting zero range  
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //Verify that bounds are set correctly when lower bound is empty.
    @Test
    public void checkAgeRangeLowerBoundEmptyTest() {
        System.out.println("Upper bound is specified, lower bound is set to 0.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with lower bound empty
        RangeValidationResponse response = vld.checkAgeRange("", "45");

        //Upper bound is specified, lower bound is set to zero 
        assertEquals(0, response.r().lower());
        assertEquals(45, response.r().upper());

        //Expecting true result from validation
        assertTrue(response.result());
    }

    //Verify that bounds are set correctly when upper bound is empty.
    @Test
    public void checkAgeRangeUpperBoundEmptyTest() {
        System.out.println("Lower bound is specified, upper bound is set to 0.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with upper bound empty
        RangeValidationResponse response = vld.checkAgeRange("85", "");

        //lower bound is specified, upper bound is set to 100 
        assertEquals(85, response.r().lower());
        assertEquals(100, response.r().upper());

        //Expecting true result from validation
        assertTrue(response.result());
    }

    //Verify that non-numeric input in both bounds triggers an error.     
    @Test
    public void checkAgeRangeBothBoundsNotNumericTest() {
        System.out.println("Bounds must be whole positive numbers.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with both bounds invalid         
        RangeValidationResponse response = vld.checkAgeRange("8fg5", "ab2");

        //Expect error message is triggered 
        assertEquals("Both bounds must be whole positive numbers.", response.message());

        //Both bounds are not numeric, so expecting zero range  
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting null result from validation
        assertFalse(response.result());
    }

    //Verify that non-numeric input in lower bound triggers an error.     
    @Test
    public void checkAgeRangeLowerBoundNotNumericTest() {
        System.out.println("Lower bound is non-numeric.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with invalid lower bound          
        RangeValidationResponse response = vld.checkAgeRange("8fg5", "22");

        //Expect error message is triggered 
        assertEquals("Lower bound must be a whole positive number.", response.message());

        //Lower bound is not numeric, so expecting zero range  
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting null result from validation
        assertFalse(response.result());
    }

    //Verify that non-numeric input in upper bound triggers an error.     
    @Test
    public void checkAgeRangeUpperBoundNotNumericTest() {
        System.out.println("Upper bound is non-numeric.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with invalid upper bound        
        RangeValidationResponse response = vld.checkAgeRange("45", "2a2");

        //Expect error message is triggered 
        assertEquals("Upper bound must be a whole positive number.", response.message());

        //Upper bound is not numeric, so expecting zero range  
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting null result from validation
        assertFalse(response.result());
    }

    //Verify that numeric negative values in both bounds trigger an error.      
    @Test
    public void checkAgeRangeBothBoundsNegativeTest() {
        System.out.println("Negative values in both bounds");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with both bounds negative        
        RangeValidationResponse response = vld.checkAgeRange("-45", "-22");

        //Expect error message is triggered 
        assertEquals("Both bounds must be whole positive numbers.", response.message());

        //Both bounds are negative, so expecting zero range  
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting null result from validation
        assertFalse(response.result());
    }

    //Verify that numeric negative values in lower bound trigger an error.      
    @Test
    public void checkAgeRangeLowerBoundNegativeTest() {
        System.out.println("Negative value in lower bound");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with negative lower bound        
        RangeValidationResponse response = vld.checkAgeRange("-45", "22");

        //Expect error message is triggered 
        assertEquals("Lower bound must be a whole positive number.", response.message());

        //Lower bound is negative, so expecting zero range  
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting null result from validation
        assertFalse(response.result());
    }
    
    //Verify that numeric negative values in upper bound trigger an error.      
    @Test
    public void checkAgeRangeUpperBoundNegativeTest() {
        System.out.println("Negative value in upper bound");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response with negative upper bound        
        RangeValidationResponse response = vld.checkAgeRange("45", "-22");

        //Expect error message is triggered 
        assertEquals("Upper bound must be a whole positive number.", response.message());

        //Upper bound is negative, so expecting zero range  
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting null result from validation
        assertFalse(response.result());
    }

 //Both bounds are out of range i.e., larger than 100 (due to regex).     
    @Test
    public void checkAgeRangeBothBoundsOutOfRangeTest() {
        System.out.println("Both bounds are out of range.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response using invalid data in lower bound
        RangeValidationResponse response = vld.checkAgeRange("105", "110");

        //Expect error message is triggered 
        assertEquals("Both bounds are out of 0-100 range.", response.message());

        //Upper bound is out of range, so expecting zero range  
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting null result from validation
        assertFalse(response.result());
    }

//Lower bound is out of range i.e., larger than 100 (due to regex). 
    @Test
    public void checkAgeRangeLowerBoundOutOfRangeTest() {
        System.out.println("Lower bound is out of range.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response using invalid data in lower bound
        RangeValidationResponse response = vld.checkAgeRange("108", "78");

        //Expect error message is triggered 
        assertEquals("Lower bound is out of 0-100 range.", response.message());

        //Lower bound is out of range, so expecting zero range  
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting null result from validation
        assertFalse(response.result());
    }

    //Upper bound is out of range i.e., larger than 100 (due to regex). 
    @Test
    public void checkAgeRangeUpperBoundOutOfRangeTest() {
        System.out.println("Upper bound is out of range.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response using invalid data in lower bound
        RangeValidationResponse response = vld.checkAgeRange("10", "109");

        //Expect error message is triggered 
        assertEquals("Upper bound is out of 0-100 range.", response.message());

        //Upper bound is out of range, so expecting zero range  
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting null result from validation
        assertFalse(response.result());
    }
    
    //Test boundaries of the range 
    @Test
    public void checkAgeRangeBoundariesTest() {
        System.out.println("Valid boundaries.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response using boundary values 
        RangeValidationResponse response = vld.checkAgeRange("0", "100");        

       //Valid bounds specified
        assertEquals(0, response.r().lower());
        assertEquals(100, response.r().upper());

        //Expecting true result from validation
        assertTrue(response.result());
    }   
    
    //Upper bound is less than lower bound.
    @Test
    public void checkAgeRangeUpperBoundLessThanLowerBoundTest() {
        System.out.println("Upper bound is less than lower bound.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();

        //invoke response using invalid data in lower bound
        RangeValidationResponse response = vld.checkAgeRange("95", "14");

        //Expect error message is triggered 
        assertEquals("Upper bound must be larger than lower bound.", response.message());

        //Upper bound is less than lower bound, so expecting zero range  
        assertEquals(0, response.r().lower());
        assertEquals(0, response.r().upper());

        //Expecting null result from validation
        assertFalse(response.result());
    }
   
    //Testing checkRegion(String r)  
    
    //Valid region name
    @Test
    public void checkRegionValidRegionNameTest() {
        System.out.println("Valid region name.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();
        
        //invoke response using valid region name  
        ValidationResponse response = vld.checkRegion("London");
        
        //Expecting true result from validation
        assertTrue(response.result());
    }

    //Triggering null check error 
    @Test
    public void checkRegionNullTest() {
        System.out.println("null in region input detected.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();
        
        //invoke response using null value  
        ValidationResponse response = vld.checkRegion(null);
        
        //Expect error message is triggered 
        assertEquals("Null in 'Malts from Region' input field detected.", response.message());
        
        //Expecting null result from validation
        assertFalse(response.result());
    }
    
    //Triggering empty region input field check error 
    @Test
    public void checkRegionEmptyInputFieldTest() {
        System.out.println("Empty region input field detected.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();
        
        //invoke response using empty string   
        ValidationResponse response = vld.checkRegion("");
        
        //Expect error message is triggered 
        assertEquals("Please enter region name.", response.message());
        
        //Expecting null result from validation
        assertFalse(response.result());
    }    
    
    //Verify that only literals were used in region name  
    @Test
    public void checkRegionNonLiteralsTest() {
        System.out.println("non alphabetic characters in 'Malts from Region' input field detected.");

        //create validator instance
        WhiskeyDataValidator vld = new WhiskeyDataValidator();
        
        //invoke response using invalid value  
        ValidationResponse response = vld.checkRegion("London56");
        
        //Expect error message is triggered 
        assertEquals("Region name must include only alphabetic characters.", response.message());
        
        //Expecting null result from validation
        assertFalse(response.result());
    } 
   
}//end WhiskeyDataValidatorTest
