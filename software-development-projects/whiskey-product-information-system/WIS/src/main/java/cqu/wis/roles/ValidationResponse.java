/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

/**
 * UserDataValidator record 
 * 
 * Defines the validation response structure   
 * 
 * @param result validation outcome - boolean
 * @param message outcome message 
 * 
 * @author Mark Ashkenazi
 * @version Phase6final  
 */
public record ValidationResponse (boolean result, String message) {
    
}
