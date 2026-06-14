/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.UserData.UserDetails;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * UserDataValidatorTest class JUnit5 tests of:
 * a. checkForFieldsPresent(String n, String p) - login scene 
 * b. checkForFieldsPresent(String n, String oldp, String newp) - change password scene 
 * c. checkCurrentDetails(UserDetails ud, String n, String oldp)
 * d. checkNewDetails(UserDetails ud, String n, String oldp, String newp)
 *
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class UserDataValidatorTest {

    public UserDataValidatorTest() {
    }

    //Testing checkForFieldsPresent(String n, String p) - Login scene(LS)
    
    //Both input fields present (not empty)     
    @Test
    public void checkForFieldsPresentLSvalidInputTest() {
        System.out.println("Both input fields present");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response using valid data in both fields
        ValidationResponse response = udv.checkForFieldsPresent("admin", "password");

        //Both inputs specified message
        assertEquals("Both fields present.", response.message());

        //Expecting true result from validation
        assertTrue(response.result());
    }

    //Both input fields are null     
    @Test
    public void checkForFieldsPresentLSbothFieldsNullTest() {
        System.out.println("Both input fields are null");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with null in both fields
        ValidationResponse response = udv.checkForFieldsPresent(null, null);

        //Expect relevant error message is triggered 
        assertEquals("Input fields cannot be null.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //The User Name field is null     
    @Test
    public void checkForFieldsPresentLSuserNameFieldNullTest() {
        System.out.println("The User Name field is null");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with null in User Name field
        ValidationResponse response = udv.checkForFieldsPresent(null, "password");

        //Expect relevant error message is triggered 
        assertEquals("User Name field cannot be null.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //The Password field is null     
    @Test
    public void checkForFieldsPresentLSpasswordFieldNullTest() {
        System.out.println("The Password field is null");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with null in Password field
        ValidationResponse response = udv.checkForFieldsPresent("admin", null);

        //Expect relevant error message is triggered 
        assertEquals("Password field cannot be null.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //Both input fields are empty     
    @Test
    public void checkForFieldsPresentLSbothFieldsEmptyTest() {
        System.out.println("Both input fields are empty");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with empty fields
        ValidationResponse response = udv.checkForFieldsPresent("", "");

        //Expect relevant error message is triggered 
        assertEquals("Input fields cannot be empty.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //THE User Name field is empty     
    @Test
    public void checkForFieldsPresentLSuserNameFieldEmptyTest() {
        System.out.println("The User Name field is empty");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with empty User Name field
        ValidationResponse response = udv.checkForFieldsPresent("", "password");

        //Expect relevant error message is triggered 
        assertEquals("User Name field cannot be empty.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //The Password field is empty     
    @Test
    public void checkForFieldsPresentLSpasswordFieldEmptyTest() {
        System.out.println("The Password field is empty");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with empty Password field
        ValidationResponse response = udv.checkForFieldsPresent("admin", "");

        //Expect relevant error message is triggered 
        assertEquals("Password field cannot be empty.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
   /*Testing checkForFieldsPresent(String n, String oldp, String newp)
   - Change Password scene(CP)*/
    
    //Three input fields present (not empty)     
    @Test
    public void checkForFieldsPresentCPvalidInputTest() {
        System.out.println("All inputs present");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response using valid data in both fields
        ValidationResponse response = udv.checkForFieldsPresent("admin", "password", "newPassword");

        //All inputs specified message
        assertEquals("All fields present.", response.message());

        //Expecting true result from validation
        assertTrue(response.result());
    }

    //All input fields are null     
    @Test
    public void checkForFieldsPresentCPallInputFieldsNullTest() {
        System.out.println("All input fields are null");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with null in both fields
        ValidationResponse response = udv.checkForFieldsPresent(null, null, null);

        //Expect relevant error message is triggered 
        assertEquals("Input fields cannot be null.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //User Name field null     
    @Test
    public void checkForFieldsPresentCPuserNameFieldNullTest() {
        System.out.println("User Name field is null");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with null in User Name field
        ValidationResponse response = udv.checkForFieldsPresent(null, "password", "newPassword");

        //Expect relevant error message is triggered 
        assertEquals("User Name field cannot be null.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //Old Password field is null     
    @Test
    public void checkForFieldsPresentCPoldPasswordFieldNullTest() {
        System.out.println("Old Password field is null");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with null in Old Password field
        ValidationResponse response = udv.checkForFieldsPresent("admin", null, "newPassword");

        //Expect relevant error message is triggered 
        assertEquals("Old Password field cannot be null.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    //New Password field is null     
    @Test
    public void checkForFieldsPresentCPnewPasswordFieldNullTest() {
        System.out.println("New Password field is null");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with null in New Password field
        ValidationResponse response = udv.checkForFieldsPresent("admin", "password", null);

        //Expect relevant error message is triggered 
        assertEquals("New Password field cannot be null.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    //All input fields are empty     
    @Test
    public void checkForFieldsPresentCPallInputFieldsEmptyTest() {
        System.out.println("All input fields are empty");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with empty fields
        ValidationResponse response = udv.checkForFieldsPresent("", "", "");

        //Expect relevant error message is triggered 
        assertEquals("Input fields cannot be empty.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //User Name field is empty     
    @Test
    public void checkForFieldsPresentCPuserNameFieldEmptyTest() {
        System.out.println("User Name field is empty");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with empty User Name field
        ValidationResponse response = udv.checkForFieldsPresent("", "password", "newPassword" );

        //Expect relevant error message is triggered 
        assertEquals("User Name field cannot be empty.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //Old Password field is empty     
    @Test
    public void checkForFieldsPresentCPoldPasswordFieldEmptyTest() {
        System.out.println("Old Password field is empty");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with empty Old Password field
        ValidationResponse response = udv.checkForFieldsPresent("admin", "", "newPassword");

        //Expect relevant error message is triggered 
        assertEquals("Old Password field cannot be empty.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    //New Password field is empty     
    @Test
    public void checkForFieldsPresentCPnewPasswordFieldEmptyTest() {
        System.out.println("New Password field is empty");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //invoke response with empty New Password field
        ValidationResponse response = udv.checkForFieldsPresent("admin", "password", "");

        //Expect relevant error message is triggered 
        assertEquals("New Password field cannot be empty.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //Testing checkCurrentDetails(UserDetails ud, String n, String oldp)
    
    //Testing valid username and password     
    @Test
    public void checkCurrentDetailsValidInputTest() {
        System.out.println("User and plain password were located in DB.");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //Mimic DB record for comparison
        UserDetails ud = new UserDetails("admin", "password");

        //invoke response using valid data in both fields
        ValidationResponse response = udv.checkCurrentDetails(ud, "admin", "password");

        //Both inputs specified message
        assertEquals("Please change your password.", response.message());

        //Expecting true result from validation
        assertTrue(response.result());
    }

    //Non alphabetic input in User Name field.   
    @Test
    public void checkCurrentDetailsUserNameFieldNonLiteralTest() {
        System.out.println("Non alphabetic input in User Name field.");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //Mimic DB record for comparison
        UserDetails ud = new UserDetails("admin", "password");

        //invoke response with invalid User Name format
        ValidationResponse response = udv.checkCurrentDetails(ud, "admin5", "password");

        //Expect relevant error message is triggered 
        assertEquals("User Name must include only alphabetic characters.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    //Corrupted (null) username data
    @Test
    public void checkCurrentDetailsCorruptedUserNameDataInDbTest() {
        System.out.println("Corrupted username data in DB");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //Mimic corrupted username data in DB record for comparison
        UserDetails ud = new UserDetails(null, "password");

        //mimic broken connection with DB
        ValidationResponse response = udv.checkCurrentDetails(ud, "admin", "password");

        //Expect relevant error message is triggered 
        assertEquals("Corrupted username data.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    //Corrupted (null) password data
    @Test
    public void checkCurrentDetailsCorruptedPasswordDataInDbTest() {
        System.out.println("Corrupted password data in DB");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //Mimic corrupted password data in DB record for comparison
        UserDetails ud = new UserDetails("admin", null);

        //mimic broken connection with DB
        ValidationResponse response = udv.checkCurrentDetails(ud, "admin", "password");

        //Expect relevant error message is triggered 
        assertEquals("Corrupted password data.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //Non-existent user in DB
    @Test
    public void checkCurrentDetailsNonExistingUsernameTest() {
        System.out.println("Non-existent user");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //Mimic DB record for comparison
        UserDetails ud = new UserDetails("admin", "password");

        //invoke response with wrong username
        ValidationResponse response = udv.checkCurrentDetails(ud, "admins", "password");

        //Expect relevant error message is triggered 
        assertEquals("User not found.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }

    //Existing user, wrong password entered
    @Test
    public void checkCurrentDetailsExistingUserWrongPasswordTest() {
        System.out.println("Existing user, wrong password entered.");
        //create validator instance
        UserDataValidator udv = new UserDataValidator();

        //Mimic DB record for comparison
        UserDetails ud = new UserDetails("admin", "password");

        //invoke response with wrong password
        ValidationResponse response = udv.checkCurrentDetails(ud, "admin", "wrong_password");

        //Expect relevant error message is triggered 
        assertEquals("Wrong passowrd!", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    /*checkNewDetails(UserDetails ud, String n, String oldp, String newp) 
    checkCurrentDetails() method is used here implicitly to check for null and empty fields */    
    
    //Tests that valid data passes all validations.    
    @Test
    public void checkNewDetailsValidInputTest() {
        System.out.println("Password was updated.");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();
        
        String username = "admin";
        String oldPassword = "password";
        String newPassword = "NewPass@4";
        
        UserDetails ud = new UserDetails(username, UserDataValidator.generateSHA1(oldPassword));

        //invoke response using valid data 
        ValidationResponse response = udv.checkNewDetails(ud, username, oldPassword, newPassword);

        //Succsessful password update is expected 
        assertEquals("Your password was updated.", response.message());

        //Expecting true result from validation
        assertTrue(response.result());
    }
    
    //New password is the same as the old password
    @Test
    public void checkNewDetailsComparePasswordsTest() {
        System.out.println("New password cannot be same as old password.");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();
        
        String username = "admin";
        String oldPassword = "NewPass@4";  
        String newPassword = "NewPass@4";
        
        UserDetails ud = new UserDetails(username, UserDataValidator.generateSHA1(oldPassword));

        //invoke error message using same passwords 
        ValidationResponse response = udv.checkNewDetails(ud, username, oldPassword, newPassword);

         //Expect relevant error message is triggered 
        assertEquals("New password must be different than old password.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    //Weak new password - less than 8 characters
    @Test
    public void checkNewDetailsWeakPasswordNumberOfCharsTest() {
        System.out.println("Weak new password - less than 8 characters.");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();
        
        String username = "admin";
        String oldPassword = "password";  
        String newPassword = "Nest@4";
        
        UserDetails ud = new UserDetails(username, UserDataValidator.generateSHA1(oldPassword));

        //invoke error message by specifying less than 8 characters 
        ValidationResponse response = udv.checkNewDetails(ud, username, oldPassword, newPassword);

        //Expect relevant error message is triggered 
        assertEquals("Pasword must contain at least 8 characters.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
   
    //Weak new password - no digits
    @Test
    public void checkNewDetailsWeakPasswordNoDigitsTest() {
        System.out.println("Weak new password - no digits.");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();
        
        String username = "admin";
        String oldPassword = "password";  
        String newPassword = "Nestle@a";
        
        UserDetails ud = new UserDetails(username, UserDataValidator.generateSHA1(oldPassword));

        //invoke error message by omitting a digit in the new password 
        ValidationResponse response = udv.checkNewDetails(ud, username, oldPassword, newPassword);

        //Expect relevant error message is triggered 
        assertEquals("Password must have at least one digit.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
     
    //Backslash character in new password
    @Test
    public void checkNewDetailsInvalidCharacterBackslashTest() {
        System.out.println("Password contains invalid characters.");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();
        
        String username = "admin";
        String oldPassword = "password";  
        String newPassword = "Nes\\raf6g";//backslash
        
        UserDetails ud = new UserDetails(username, UserDataValidator.generateSHA1(oldPassword));

        //invoke error message by using invalid character in the new password 
        ValidationResponse response = udv.checkNewDetails(ud, username, oldPassword, newPassword);

        //Expect relevant error message is triggered 
        assertEquals("Password contains invalid characters.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    //Single quote character in new password
    @Test
    public void checkNewDetailsInvalidCharacterSingleQuoteTest() {
        System.out.println("Password cannot contain single quote.");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();
        
        String username = "admin";
        String oldPassword = "password";  
        String newPassword = "Nes'%raf6g";
        
        UserDetails ud = new UserDetails(username, UserDataValidator.generateSHA1(oldPassword));

        //invoke error message by using single quote in the new password 
        ValidationResponse response = udv.checkNewDetails(ud, username, oldPassword, newPassword);

        //Expect relevant error message is triggered 
        assertEquals("Password contains invalid characters.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    //Double quote character in new password
    @Test
    public void checkNewDetailsInvalidCharacterDoubleQuotesTest() {
        System.out.println("Password cannot contain double quote.");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();
        
        String username = "admin";
        String oldPassword = "password";  
        String newPassword = "Nes\"%raf6g";//double quote
        
        UserDetails ud = new UserDetails(username, UserDataValidator.generateSHA1(oldPassword));

        //invoke error message by using double quote in the new password 
        ValidationResponse response = udv.checkNewDetails(ud, username, oldPassword, newPassword);

        //Expect relevant error message is triggered 
        assertEquals("Password contains invalid characters.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    //Weak new password - no uppercase letter
    @Test
    public void checkNewDetailsWeakPasswordNoUppercaseTest() {
        System.out.println("Weak new password - no uppercases.");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();
        
        String username = "admin";
        String oldPassword = "password";  
        String newPassword = "nesr af6g";
        
        UserDetails ud = new UserDetails(username, UserDataValidator.generateSHA1(oldPassword));

        //invoke error message by omitting uppercase letter in the new password 
        ValidationResponse response = udv.checkNewDetails(ud, username, oldPassword, newPassword);

        //Expect relevant error message is triggered 
        assertEquals("Password must have at least one uppercase letter.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    //Weak new password - no lowercase letter
    @Test
    public void checkNewDetailsWeakPasswordNoLowercaseTest() {
        System.out.println("Weak new password - no lowercases.");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();
        
        String username = "admin";
        String oldPassword = "password";  
        String newPassword = "NG HY6&-";
        
        UserDetails ud = new UserDetails(username, UserDataValidator.generateSHA1(oldPassword));

        //invoke error message by omitting lowercase letter in the new password 
        ValidationResponse response = udv.checkNewDetails(ud, username, oldPassword, newPassword);

        //Expect relevant error message is triggered 
        assertEquals("Password must have at least one lowercase letter.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }
    
    //Weak new password - no special character 
    @Test
    public void checkNewDetailsWeakPasswordNoSpecialCharacterTest() {
        System.out.println("Weak new password - no special character.");

        //create validator instance
        UserDataValidator udv = new UserDataValidator();
        
        String username = "admin";
        String oldPassword = "password";  
        String newPassword = "NfHY 6rtr";
        
        UserDetails ud = new UserDetails(username, UserDataValidator.generateSHA1(oldPassword));

        //invoke error message by omitting special character in the new password 
        ValidationResponse response = udv.checkNewDetails(ud, username, oldPassword, newPassword);

        //Expect relevant error message is triggered 
        assertEquals("Password must have at least one special character.", response.message());

        //Expecting false result from validation
        assertFalse(response.result());
    }    
     
}//end UserDataValidatorTest
