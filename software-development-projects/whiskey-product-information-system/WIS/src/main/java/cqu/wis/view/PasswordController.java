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
 * Controller for the Change Password scene.
 * 
 * Invokes field validation and authentication logic.   
 *
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class PasswordController implements Initializable {

    private SceneCoordinator sc;
    private UserDataManager udm;
    private UserDataValidator udv;

    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField oldPasswordTextField;
    @FXML
    private TextArea messagesTextArea;
    @FXML
    private Button submitButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button exitButton;
    @FXML
    private PasswordField newPasswordTextField;

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
     * Authentication and validation logic
     * 
     * @param event action event for the Submit button  
     */
    @FXML
    private void submitButtonClicked(ActionEvent event) {
        String username = userNameTextField.getText().trim();
        String oldPassword = oldPasswordTextField.getText();
        String newPassword = newPasswordTextField.getText();
        messagesTextArea.clear();

        //Locate user
        UserDetails user = udm.findUser(username);
        if (user == null) {
            messagesTextArea.appendText("User not found in DB.");
            return; //abort lookup
        }

        //Validate input
        ValidationResponse vr = udv.checkNewDetails(user, username, oldPassword, newPassword);
        if (!vr.result()) {
            messagesTextArea.appendText(vr.message() + "\n");
            return;//abort update
        }

        //Hash password after passing input validation
        String hashedPassword = UserDataValidator.generateSHA1(newPassword);

        //update password in DB
        try {
            int rows = udm.updatePassword(username, hashedPassword);
            if (rows > 0) {
                System.out.println("Password was updated.");
                sc.setScene(SceneCoordinator.SceneKey.LOGIN); //return to login scene
            } else {
                messagesTextArea.appendText("Password update failed.");
            }
        } catch (Exception e) {
            messagesTextArea.appendText("Error: " + e.getMessage() + "\n");
        }
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
        oldPasswordTextField.setText("");
        newPasswordTextField.setText("");
        messagesTextArea.setText("");
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

}
