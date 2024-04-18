package edu.duke.ece651.xl435.calculator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.ListViewMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;
import org.testfx.util.WaitForAsyncUtils;

import edu.duke.ece651.xl435.calculator.controller.NumButtonController;
import edu.duke.ece651.xl435.calculator.model.RPNStack;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class NumButtonControllerTest {
    
    private TextField testText;
    private NumButtonController cont;
    private RPNStack model;
    
    @Start
    private void start(Stage stage) {
        testText = new TextField();
        model = mock(RPNStack.class);
        cont = new NumButtonController(model);
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
    
    @Test
    void test_enterButton(FxRobot robot){
        Platform.runLater(()->{
            testText.setText("1234.5");
            Button b = new Button("Enter");
            cont.onEnter(new ActionEvent(b,null));
        });

        WaitForAsyncUtils.waitForFxEvents();
        verify(model).pushNum(1234.5);
        verifyNoMoreInteractions(model);
        FxAssert.verifyThat(testText, TextInputControlMatchers.hasText(""));
    }

    @Test
    void test_plus(FxRobot robot){
        model = new RPNStack();
        model.pushNum(11.5);
        Platform.runLater(()->{
            testText.setText("10.3");
            Button b = new Button("plus");
            cont.onPlus(new ActionEvent(b,null));
        });

        WaitForAsyncUtils.waitForFxEvents();
        // assertEquals(21.8,model.getList().get(0));
        // assertEquals(1,model.getList().size());
        FxAssert.verifyThat(testText, TextInputControlMatchers.hasText(""));
        // FxAssert.verifyThat("#rpnstack", ListViewMatchers.hasItems(1));
        // FxAssert.verifyThat("#rpnstack", ListViewMatchers.hasListCell(21.8));
    }
}
