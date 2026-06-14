/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cqu.wis.view;

import cqu.wis.data.WhiskeyData;
import cqu.wis.data.WhiskeyData.WhiskeyDetails;
import cqu.wis.roles.SceneCoordinator;
import cqu.wis.roles.ValidationResponse;
import cqu.wis.roles.WhiskeyDataManager;
import cqu.wis.roles.WhiskeyDataValidator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for the Query scene.
 * 
 * Allows querying whiskey database via GUI controls and inputs. 
 *
 * @author Mark Ashkenazi
 * @version Phase6final
 */
public class QueryController implements Initializable {

    @FXML
    private TextField distilleryTextField;
    @FXML
    private TextArea messagesTextArea;
    @FXML
    private Button previousButton;
    @FXML
    private Button allMaltsButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField regionTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private Button nextButton;
    @FXML
    private Button maltsFromRegionButton;
    @FXML
    private Button maltsInAgeRangeButton;
    @FXML
    private TextField maltsFromRegionTextField;
    @FXML
    private TextField fromAgeTextField;
    @FXML
    private TextField toAgeTextField;

    private WhiskeyData wdata;
    private SceneCoordinator sc;
    private WhiskeyDataManager wdm;
    private WhiskeyDataValidator wdv;

    /**
     * Injects dependencies 
     * 
     * @param sc SceneCoordiantor
     * @param wdm WhiskeyDataManager
     * @param wdv WhiskeyDataValidator 
     */
    public void inject(SceneCoordinator sc, WhiskeyDataManager wdm, WhiskeyDataValidator wdv) {
        this.sc = sc;
        this.wdm = wdm;
        this.wdv = wdv;
    }

    /**
     * Initializes the controller class.
     * 
     * @param url internal FX use
     * @param rb internal FX use
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wdata = new WhiskeyData();
        wdata.connect();
        wdm = new WhiskeyDataManager(wdata);
        wdv = new WhiskeyDataValidator();
    }

    /**
     * Loads previous record from the current WhiskeyDetails record
     * Can be clicked repeatedly to cycle records. 
     * 
     * @param event action event for the Previous button 
     */
    @FXML
    private void previousButtonClicked(ActionEvent event) {
        WhiskeyDetails previousRecord = wdm.previous();

        if (previousRecord != null) {
            //messagesTextArea.setText(previousRecord.toString());
            distilleryTextField.setText(previousRecord.distillery());
            ageTextField.setText(String.valueOf(previousRecord.age()));
            regionTextField.setText(previousRecord.region());
            priceTextField.setText(String.valueOf(previousRecord.price()));
        }
    }

    /**
     * Loads all records from whiskey DB in the messages window
     * 
     * @param event action event for the All Malts button 
     */
    @FXML
    private void allMaltsButtonClicked(ActionEvent event) {
        int countRecords = wdm.findAllMalts();//gets number of records in whiskeyDetailsList

        if (countRecords > 0) {
            WhiskeyDetails currentRecord = wdm.first();
            messagesTextArea.setText("Records found: " + countRecords);
            distilleryTextField.setText(currentRecord.distillery());
            ageTextField.setText(String.valueOf(currentRecord.age()));
            regionTextField.setText(currentRecord.region());
            priceTextField.setText(String.valueOf(currentRecord.price()));

        } else {
            messagesTextArea.setText("No records were found.");
        }
    }

    /**
     * Clears input fields
     * 
     * @param event action event for Clear button 
     */
    @FXML
    private void clearButtonClicked(ActionEvent event) {    
        //clear inputs
        distilleryTextField.setText("");
        messagesTextArea.setText("");
        ageTextField.setText("");
        regionTextField.setText("");
        priceTextField.setText("");
        maltsFromRegionTextField.setText("");
        fromAgeTextField.setText("");
        toAgeTextField.setText("");
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
     * Loads next record from the current WhiskeyDetails record.
     * Can be clicked repeatedly to cycle records. 
     * 
     * @param event action event for the Next button 
     */
    @FXML
    private void nextButtonClicked(ActionEvent event) {
        WhiskeyDetails nextRecord = wdm.next();

        if (nextRecord != null) {
            //messagesTextArea.setText(nextRecord.toString());
            distilleryTextField.setText(nextRecord.distillery());
            ageTextField.setText(String.valueOf(nextRecord.age()));
            regionTextField.setText(nextRecord.region());
            priceTextField.setText(String.valueOf(nextRecord.price()));
        }
    }

    /**
     * Loads records filtered by specified region     
     * 
     * @param event action event for the Malts from Region button 
     */
    @FXML
    private void maltsFromRegionButtonClicked(ActionEvent event) {
        String r = maltsFromRegionTextField.getText().trim();

        //validate input
        ValidationResponse validation = wdv.checkRegion(r);

        if (!validation.result()) {
            messagesTextArea.setText(validation.message());   
            return;
        }

        int countRecords = wdm.findMaltsFromRegion(r);//gets number of records in maltsFromRegionList

        if (countRecords > 0) {
            WhiskeyDetails currentRecord = wdm.first();
            messagesTextArea.setText("Records found: " + countRecords);
            distilleryTextField.setText(currentRecord.distillery());
            ageTextField.setText(String.valueOf(currentRecord.age()));
            regionTextField.setText(currentRecord.region());
            priceTextField.setText(String.valueOf(currentRecord.price()));

        } else {
            messagesTextArea.setText("No malts found for this region.");            
        }
    }

    /**
     * Loads records filtered by specified age range of malts     
     * 
     * @param event action event for the Malts In Age Range button 
     */     
    @FXML
    private void maltsInAgeRangeButtonClicked(ActionEvent event) {
        String lowerBound = fromAgeTextField.getText().trim();
        String upperBound = toAgeTextField.getText().trim();

        //validate input
        WhiskeyDataValidator.RangeValidationResponse rangeValidation = wdv.checkAgeRange(lowerBound, upperBound);

        if (!rangeValidation.result()) {
            messagesTextArea.setText(rangeValidation.message());
            return;
        }

        //Validated and parsed input 
        int fromAge = rangeValidation.r().lower();
        int toAge = rangeValidation.r().upper();

        int countRecords = wdm.findMaltsInAgeRange(fromAge, toAge);//gets number of records in maltsInRangeList

        if (countRecords > 0) {
            WhiskeyDetails currentRecord = wdm.first();
            messagesTextArea.setText("Records found: " + countRecords);
            distilleryTextField.setText(currentRecord.distillery());
            ageTextField.setText(String.valueOf(currentRecord.age()));
            regionTextField.setText(currentRecord.region());
            priceTextField.setText(String.valueOf(currentRecord.price()));

        } else {
            messagesTextArea.setText("No records were found.");
        }
    }

}
