package com.internshala.javafxapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Label welcomeLabel;

    @FXML
    public ChoiceBox<String> choiceBox;

    @FXML
    public TextField userInputField;

    @FXML
    public Button convertButton;
    private static final String C_TO_F_TEXT = "Celsius to Fahrenheit";
    private static final String F_TO_C_TEXT = "Fahrenheit to Celsius";
    private static final String C_TO_K_TEXT = "Celsius to Kelvin";
    private static final String K_TO_C_TEXT = "Kelvin to Celsius";
    private static final String F_TO_K_TEXT = "Fahrenheit to Kelvin";
    private static final String K_TO_F_TEXT = "Kelvin to Fahrenheit";

    private int isC_TO_F = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceBox.getItems().add(C_TO_F_TEXT);
        choiceBox.getItems().add(F_TO_C_TEXT);
        choiceBox.getItems().add(C_TO_K_TEXT);
        choiceBox.getItems().add(K_TO_C_TEXT);
        choiceBox.getItems().add(F_TO_K_TEXT);
        choiceBox.getItems().add(K_TO_F_TEXT);

        choiceBox.setValue(C_TO_F_TEXT);
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.equals(C_TO_F_TEXT)) {
                isC_TO_F = 1;
            } else if(newValue.equals(F_TO_C_TEXT)){
                isC_TO_F = 2;
            } else if (newValue.equals(C_TO_K_TEXT)) {
                isC_TO_F = 3;
            } else if (newValue.equals(K_TO_C_TEXT)) {
                isC_TO_F = 4;
            } else if (newValue.equals(F_TO_K_TEXT)) {
                isC_TO_F = 5;
            } else {
                isC_TO_F = 6;
            }
        });

        convertButton.setOnAction(actionEvent -> {
            convert();
        });

    }

    private void convert() {
        String input = userInputField.getText();
        float enteredTemperature = 0.0f;
        String unit = "C";
        try {
            enteredTemperature = Float.parseFloat(input);
        } catch (Exception exception) {
            warnUser();
            return;
        }
        float newTemperature = 0.0f;
        if (isC_TO_F == 1) {
            newTemperature = (enteredTemperature * 9 / 5 ) + 32;
            unit = " F";
        } else if (isC_TO_F == 2) {
            newTemperature = (enteredTemperature -32 ) * 5 / 9;
            unit = " C";
        } else if (isC_TO_F == 3) {
            newTemperature = (float) (enteredTemperature + 273.15);
            unit = " K";
        } else if (isC_TO_F == 4) {
            newTemperature = (float) (enteredTemperature - 273.15);
            unit = " C";
        } else if (isC_TO_F == 5) {
            newTemperature = (float) (((enteredTemperature - 32)/1.8) + 273.15);
            unit = " K";
        } else {
            newTemperature = (float) (1.8 * (enteredTemperature - 273.15) + 32);
            unit = " F";
        }
        display(newTemperature, unit);
    }

    private void warnUser() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occured");
        alert.setHeaderText("Invalid Temperature Entered");
        alert.setContentText("Please enter a valid temperature");
        alert.show();;
    }

    private void display(float newTemperature, String unit) {
        System.out.println("The new temperature is: " + newTemperature + unit);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("The new temperature is: " + newTemperature + unit);
        alert.show();

    }
}
