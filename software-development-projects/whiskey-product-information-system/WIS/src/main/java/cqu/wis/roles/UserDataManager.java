/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.UserData;
import cqu.wis.data.UserData.UserDetails;
import java.util.List;

/**
 * UserDataManager class
 *
 * Business logic related to user management:
 * a. links between the app and the UserData class. 
 * b. users lookup
 * c. updating passwords 
 * 
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class UserDataManager {

    //UserData access object 
    private UserData ud;

    /**
     * Overridden constructor for the UserDataManager class. 
     * Constructs UserDataManager using specified UserData instance.   
     * 
     * @param ud specified UserData instance.
     */
    public UserDataManager(UserData ud) {
        this.ud = ud;
    }

    /**
     * User lookup by specified username
     * 
     * @param name specified username
     * @return UserDetails object, or null if user not found/not initialised  
     */
    public UserDetails findUser(String name) {

        if (ud == null) {
            System.out.println("null detected, UserData not initialised.");
            return null;
        }

        List<UserDetails> userList = ud.getUser(name);
        if (!userList.isEmpty()) {
            return userList.get(0); //unique username
        }
        return null;
    }

    /**
     * Updates user`s password
     * 
     * @param user given user
     * @param password new password
     * 
     * @return number of affected rows: 1 if update is OK, 0 if update is failed.
     * @throws IllegalArgumentException when user or password are null
     */
    public int updatePassword(String user, String password) {
        //safe check before modifying record
        if (user == null || password == null) {
            throw new IllegalArgumentException("User Name and Password cannot be null.");
        }        
        return ud.updatePassword(user, password);
    }
}
