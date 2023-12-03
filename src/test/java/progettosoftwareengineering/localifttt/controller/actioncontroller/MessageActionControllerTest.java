/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.controller.actioncontroller;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;
import progettosoftwareengineering.localifttt.model.rule.action.MessageAction;

public class MessageActionControllerTest {
    
    private MessageActionController MAC;
    private MessageAction action;

    @Before
    public void setUp() {
        MAC = new MessageActionController();
        action = spy(new MessageAction("test"));
        MAC.observeAction(action);
    }

//    In order to verify that this controller observe the right Action,
//    in the setUp we call the observeAction method with a MessageAction
//    as parameter, and in this test we verify if that action has an observer.
    @Test
    public void testObserveAction() {
        assertEquals(1, action.countObservers());
    }

//    In order to verify that the update of this controller is to open 
//    a dialog box with the message of the MessageAction that trigger 
//    the observer, we execute the doAction of the action created in 
//    the setUp to visually confirm.
    @Test
    public void testUpdate() {
        new JFXPanel();
        Platform.runLater(() -> action.doAction());
        waitForFxEvents();
    }
}
