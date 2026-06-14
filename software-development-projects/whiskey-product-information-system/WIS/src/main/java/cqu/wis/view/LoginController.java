/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqu.wis.view;

import cqu.wis.data.UserData.UserDetails;
import cqu.wis.roles.SceneCoordinator;
import cqu.wis.roles.UserDataManager;
import cqu.wis.roles.UserDataValidator;
import cqu.wis.roles.ValidationResponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for the Login scene.
 * 
 * Invokes field validation and authentication logic.   
 *
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class LoginController implements Initializable {

    private SceneCoordinator sc;
    private UserDataManager udm;
    private UserDataValidator udv;

    @FXML
    private TextField userNameTextField;
    @FXML
    private Button exitButton;
    @FXML
    private TextArea messagesTextArea;
    @FXML
    private Button clearButton;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button changePasswordButton;

    /**
     * Injects dependencies
     * 
     * @param sc SceneCoordiantor
     * @param udm UserDataManager
     * @param udv UserDataValidator
     */
    public void inject(SceneCoordinator sc, UserDataManager udm, UserDataValidator udv) {
        this.sc = sc;
        this.udm = udm;
        this.udv = udv;
    }

    /**
     * Initializes the controller class.
     * @param url internal FX use
     * @param rb internal FX use
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    }

    /**
     * Exits app
     * 
     * @param event action event for Exit button 
     */
    @FXML
    private void exitButtonClicked(ActionEvent event) {
        System.out.println("Good bye!");
        System.exit(0);
    }

    /**
     * Clears input fields
     * 
     * @param event action event for Clear button 
     */
    @FXML
    private void clearButtonClicked(ActionEvent event) {
        //clear fields
        userNameTextField.setText("");
        passwordTextField.setText("");
        messagesTextArea.setText("");
    }

    /**
     * Logs user in after successful validation and authentication. 
     * 
     * @param event action event for the Login button 
     */
    @FXML
    private void loginButtonClicked(ActionEvent event) {
        String username = userNameTextField.getText().trim();
        String password = passwordTextField.getText();

        //validate fields not empty
        ValidationResponse fieldsNotEmptyVal = udv.checkForFieldsPresent(username, password);
        if (!fieldsNotEmptyVal.result()) {
            messagesTextArea.setText(fieldsNotEmptyVal.message());
            return;
        }

        //Check user details
        UserDetails userDetails = udm.findUser(username);

        //validate if username and plain password found
        ValidationResponse loginResponse = udv.checkCurrentDetails(userDetails, username, password);

        if (!loginResponse.result()) {
            messagesTextArea.setText(loginResponse.message());
            return;
        }

        //Only allow to log in with hashed password
        String message = loginResponse.message();

        if (message.equals("Valid username and password.")) {
            System.out.println("You are in the system.");
            sc.setScene(SceneCoordinator.SceneKey.QUERY);
        } else if (message.equals("Please change your password.")) {
            System.out.println("Please change your password.");
            sc.setScene(SceneCoordinator.SceneKey.PASSWORD);
        } else {
            messagesTextArea.setText("Unexpected response: " + message);//safecheck against unexpected messages
        }
    }

    /**
     * Takes user to the Change Password scene
     * 
     * @param event action event for Change Password button 
     */
    @FXML
    private void changePasswordButtonClicked(ActionEvent event) {
        sc.setScene(SceneCoordinator.SceneKey.PASSWORD);

        //Must match the MINIMUM_PASSWORD_LENGTH in UserDataValidator.
        //Password length is hardcoded for simplicity.   
        System.out.println(
                """
        ********************************************************   
        New password must have at least:
           - 8 characters: letters, numbers, symbols, spaces  
           - 1 symbol (_!@#) except quotes and backslash  
           - 1 digit (0-9) 
           - 1 Uppercase character
           - 1 Lowercase character
        ********************************************************
    """);
    }

}
