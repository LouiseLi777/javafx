package edu.duke.ece651.xl435.calculator.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.*;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {
    @FXML
    TextField currentNumber;
    @FXML
    NumButtonController numButtonController;
    public void initialize(URL location, ResourceBundle resources) {
      numButtonController.currentNumber= currentNumber;
  } 
}

