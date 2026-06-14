/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

/**
 * WhiskeyDataValidator class
 * 
 * Validation of user input in query scene.
 * Validation of whiskey data search criteria: valid age ranges and region names.  
 * 
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class WhiskeyDataValidator {

    /**
     * Range record for the malts` age range.
     * 
     * Record structure with lower and upper bounds of the age range
     * @param lower lower age bound, left input field
     * @param upper upper age bound, right input field
     */
    public record Range(int lower, int upper) {
    }

    /**
     * RangeValidationResponse record
     * 
     * Structure of the validation response. 
     * @param result boolean validation result
     * @param r range object (upper and lower bounds) or 0 range if validation fails 
     * @param message validation result message
     */
    public record RangeValidationResponse(boolean result, Range r, String message) {
    }

    /**
     * Default constructor for WhiskeyDataValidator class
     */
    public WhiskeyDataValidator() {

    }

    /**
     * Validation of whiskey age range
     * 
     * @param b1 lower range bound
     * @param b2 upper range bound
     * @return validation response object: boolean result, range object (or 0 if invalid range), response message 
     */
    public RangeValidationResponse checkAgeRange(String b1, String b2) {

        int fromAge;
        int toAge;

        //null check 
        if (b1 == null && b2 == null) {
            return new RangeValidationResponse(false, new Range(0, 0), "Bounds cannot be null.");
        } else if (b1 == null) {
            return new RangeValidationResponse(false, new Range(0, 0), "Lower bound cannot be null.");
        } else if (b2 == null) {
            return new RangeValidationResponse(false, new Range(0, 0), "Upper bound cannot be null.");
        }

        //Both fields cannot be empty.        
        if (b1.isEmpty() && b2.isEmpty()) {
            return new RangeValidationResponse(false, new Range(0, 0), "At least one field must be specified.");
        } else if (b1.isEmpty() && !b2.isEmpty()) { //Upper bound is specified, lower bound is set to zero 
            b1 = "0";
        } else if (!b1.isEmpty() && b2.isEmpty()) { //Lower bound is specified, upper bound is set to 100 
            b2 = "100";
        }

        //Verify that the input is a positive whole number  
        if (!b1.matches("\\d+") && !b2.matches("\\d+")) {
            return new RangeValidationResponse(false, new Range(0, 0), "Both bounds must be whole positive numbers.");
        } else if (!b1.matches("\\d+")) {
            return new RangeValidationResponse(false, new Range(0, 0), "Lower bound must be a whole positive number.");
        } else if (!b2.matches("\\d+")) {
            return new RangeValidationResponse(false, new Range(0, 0), "Upper bound must be a whole positive number.");
        }

        //Parse input after passing numeric validation
        fromAge = Integer.parseInt(b1);
        toAge = Integer.parseInt(b2);

        //Range 0-100
        // "\\d+" will not allow negative numbers so check for negative numbers is redundant.
        if (fromAge > 100 && toAge > 100) {
            return new RangeValidationResponse(false, new Range(0, 0), "Both bounds are out of 0-100 range.");
        } else if (fromAge > 100) {            
            return new RangeValidationResponse(false, new Range(0, 0), "Lower bound is out of 0-100 range.");
        } else if (toAge > 100) {
            return new RangeValidationResponse(false, new Range(0, 0), "Upper bound is out of 0-100 range.");
        } 
        
        //Error if left bound larger than right. 
        if (toAge < fromAge) {
            return new RangeValidationResponse(false, new Range(0, 0), "Upper bound must be larger than lower bound.");
        }

        //Valid responce if left bound equal to right. 
        if (fromAge == toAge) {
            return new RangeValidationResponse(true, new Range(fromAge, toAge), "Valid range 0, same age specified.");
        }

        //After passing all validations 
        return new RangeValidationResponse(true, new Range(fromAge, toAge), "Valid range.");
    }

    /**
     * Validates input for the whiskey region field
     * 
     * @param r region name
     * @return validation response object: boolean result and the response message 
     */
    public ValidationResponse checkRegion(String r) {
        //null check   
        if (r == null) {
            return new ValidationResponse(false, "Null in 'Malts from Region' input field detected.");
        }
        
        //Check for empty field   
        if (r.isEmpty()) {
            return new ValidationResponse(false, "Please enter region name.");
        }     

        //Verify that only literals were used in region name          
        if (!r.matches("[a-zA-Z]+")) {
            return new ValidationResponse(false, "Region name must include only alphabetic characters.");
        }
        return new ValidationResponse(true, "Valid region.");
    }
}
