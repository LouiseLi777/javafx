package edu.duke.ece651.xl435.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.TextInputControlMatchers;
import org.testfx.util.WaitForAsyncUtils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class NumButtonControllerTest {
    
    private TextField testText;
    private NumButtonController cont;
    
    @Start
    private void start(Stage stage) {
        testText = new TextField();
        cont = new NumButtonController();
        cont.currentNumber = testText;
    }
    
    @Test
    public void test_onNumberButton(FxRobot robot) {
        Platform.runLater(()->{
            Button b = new Button("7");
            cont.onNumberButton(new ActionEvent(b, null));
        });
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(testText, TextInputControlMatchers.hasText("7"));
    } 
}
