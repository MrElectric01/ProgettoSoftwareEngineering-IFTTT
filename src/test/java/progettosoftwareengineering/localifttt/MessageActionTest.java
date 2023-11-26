/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class MessageActionTest {
    
    private String message = "test";
    private MessageAction action;

    @Before
    public void setUp() {
        action = new MessageAction(message);
    }

//    This method can't be automatically tested because the only
//    function that it has, is to open a Dialog box (Alert), that can be opened
//    only from a JavaFX Event Thread.
    @Test
    public void testDoAction() {
    }

    @Test
    public void testToString() {
        assertEquals("Message: "+message, action.toString());
    }
    
}
