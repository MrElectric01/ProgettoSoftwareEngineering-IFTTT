/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule.action;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

public class MessageActionTest {
    
    private String message = "test";
    private MessageAction action;

    @Before
    public void setUp() {
        action = new MessageAction(message);
    }

//    In order to verify that the action is to open a dialog box with the message
//    inserted at the time of creation, we open it to visually confirm.
    @Test
    public void testDoAction() {
        new JFXPanel();
        Platform.runLater(() -> action.doAction());
        waitForFxEvents();
    }

    @Test
    public void testToString() {
        assertEquals("Message: "+message, action.toString());
    }
    
}
