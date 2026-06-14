/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.UserData.UserDetails;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * UserDataValidator class
 *
 * Validation logic for user credentials and password updates.
 * Validation that existing inputs are not null or empty
 * Validation of password strength rules 
 *
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class UserDataValidator {

    //Password length can be adjusted here. 
    private static final int MINIMUM_PASSWORD_LENGTH = 8;

    /**
     * Default Constructor for UserDataValidator class
     */
    public UserDataValidator() {
    }

    /**
     * Generates SHA1 hash of the password specified by the user. 
     * 
     * @param s input string
     * @return 40 characters hexadecimal SHA1 hash string of the input 
     */
    public static String generateSHA1(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            /* digest returns the hash value of the password as a 20-byte array.
            The “encrypted/hash value” is always positive, so its signum is 1.*/
            byte[] hashValue = md.digest(s.getBytes());
            int signum = 1;
            BigInteger value = new BigInteger(signum, hashValue);
            String hexDigits = value.toString(16);
            return hexDigits;
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return null;// never returned
    }

    /**
     * Checks if username and the old password in users DB match. 
     * 
     * @param ud user object
     * @param n username
     * @param oldp stored plain password
     * @return validation result
     */
    public ValidationResponse checkCurrentDetails(UserDetails ud, String n, String oldp) {

        //null check for the User Name input field
        if (n == null) {
            return new ValidationResponse(false, "User Name cannot be null.");
        }

        //Verify that only literals were used in user name          
        if (!n.matches("[a-zA-Z]+")) {
            return new ValidationResponse(false, "User Name must include only alphabetic characters.");
        }

        //check for corrupted username data 
        if (ud.user() == null) {
            return new ValidationResponse(false, "Corrupted username data.");// corrupted record
        }

        //check if user matches
        if (!ud.user().equals(n)) {
            return new ValidationResponse(false, "User not found.");
        }

        //check for corrupted password data 
        if (ud.password() == null) {
            return new ValidationResponse(false, "Corrupted password data.");//corrupted record
        }

        //check if password is hashed
        boolean isHashed = ud.password().length() == 40;

        //Hash provided password to compared with the stored hash
        if (isHashed) {
            String hashedInputPassword = generateSHA1(oldp);
            if (ud.password().equals(hashedInputPassword)) {
                return new ValidationResponse(true, "Valid username and password.");
            } else {
                return new ValidationResponse(false, "Wrong passowrd!");
            }

        } else {
            //Stored password is plain
            if (ud.password().equals(oldp)) {
                return new ValidationResponse(true, "Please change your password.");
            } else {
                return new ValidationResponse(false, "Wrong passowrd!");
            }
        }
    }

    /**
     * Validation of password change and its strength rules 
     * 
     * @param ud user object
     * @param n username
     * @param oldp old password
     * @param newp new password
     * @return validation response
     */
    public ValidationResponse checkNewDetails(UserDetails ud, String n, String oldp, String newp) {
        //Check that all input fields are present
        ValidationResponse fieldsPresentCheck = checkForFieldsPresent(n, oldp, newp);
        if (!fieldsPresentCheck.result()) {
            return fieldsPresentCheck;
        }

        //validate current details
        ValidationResponse currentDetailsResponse = checkCurrentDetails(ud, n, oldp);
        if (!currentDetailsResponse.result()) {
            return currentDetailsResponse;
        }

        //Check new password is different from the old
        if (newp.equals(oldp)) {
            return new ValidationResponse(false, "New password must be different than old password.");
        }

        /*Password strength logic
        Minimum strength requirements are printed in the output window when the change password scene loads. */
        
        //Checking for backslashes and quotes using negation
        if (!newp.matches("^[\\x20-\\x7E&&[^\\\\'\"]]+$")) {
            return new ValidationResponse(false, "Password contains invalid characters.");
        }

        //At least 8 printable (visible) characters
        if (newp.length() < MINIMUM_PASSWORD_LENGTH) {
            return new ValidationResponse(false, "Pasword must contain at least " + MINIMUM_PASSWORD_LENGTH + " characters.");
        }

        //At least one digit
        if (!newp.matches(".*\\d.*")) {
            return new ValidationResponse(false, "Password must have at least one digit.");
        }

        //At least one uppercase letter
        if (!newp.matches(".*[A-Z].*")) {
            return new ValidationResponse(false, "Password must have at least one uppercase letter.");
        }

        //At least one lowercase letter
        if (!newp.matches(".*[a-z].*")) {
            return new ValidationResponse(false, "Password must have at least one lowercase letter.");
        }

        //At least one special character (including spaces) 
        if (!newp.matches(".*[^a-zA-Z0-9\\s].*")) {
            return new ValidationResponse(false, "Password must have at least one special character.");
        }

        //Pssword is passed validation 
        return new ValidationResponse(true, "Your password was updated.");
    }

    /**
     * Checks fields are not empty and not null in the Login scene
     * 
     * @param n input from User Name field
     * @param p input from Password field
     * 
     * @return validation response
     */
    public ValidationResponse checkForFieldsPresent(String n, String p) {
        //null checks 
        if (n == null && p == null) {
            return new ValidationResponse(false, "Input fields cannot be null.");
        } else if (n == null) {
            return new ValidationResponse(false, "User Name field cannot be null.");
        } else if (p == null) {
            return new ValidationResponse(false, "Password field cannot be null.");
        }

        //Empty fields checks.        
        if (n.isEmpty() && p.isEmpty()) {
            return new ValidationResponse(false, "Input fields cannot be empty.");
        } else if (n.isEmpty()) {
            return new ValidationResponse(false, "User Name field cannot be empty.");
        } else if (p.isEmpty()) {
            return new ValidationResponse(false, "Password field cannot be empty.");
        }

        //Passed all validation checks 
        return new ValidationResponse(true, "Both fields present.");
    }

    /**
     * Checks fields are not empty and not null in the Change Password scene
     * 
     * @param n input from User Name field
     * @param oldp input from Old Password field
     * @param newp input from New Password field
     * 
     * @return validation response
     */
    public ValidationResponse checkForFieldsPresent(String n, String oldp, String newp) {
        //null checks 
        if (n == null && oldp == null && newp == null) {
            return new ValidationResponse(false, "Input fields cannot be null.");
        } else if (n == null) {
            return new ValidationResponse(false, "User Name field cannot be null.");
        } else if (oldp == null) {
            return new ValidationResponse(false, "Old Password field cannot be null.");
        } else if (newp == null) {
            return new ValidationResponse(false, "New Password field cannot be null.");
        }

        //Empty fields checks.        
        if (n.isEmpty() && oldp.isEmpty() && newp.isEmpty()) {
            return new ValidationResponse(false, "Input fields cannot be empty.");
        } else if (n.isEmpty()) {
            return new ValidationResponse(false, "User Name field cannot be empty.");
        } else if (oldp.isEmpty()) {
            return new ValidationResponse(false, "Old Password field cannot be empty.");
        } else if (newp.isEmpty()) {
            return new ValidationResponse(false, "New Password field cannot be empty.");
        }

        //Passed all validation checks 
        return new ValidationResponse(true, "All fields present.");
    }

}
