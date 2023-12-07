/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule.action.message;

import java.util.Observer;
import org.junit.*;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MessageActionTest {
    
    private String message = "test";
    private MessageAction action;

    @Before
    public void setUp() {
        action = new MessageAction(message);
    }

//    In order to verify that the action notify the observer
//    that launch the action, we instance a mock observer and verify
//    if his update method is called.
    @Test
    public void testDoAction() {
        Observer obs = mock(Observer.class);
        action.addObserver(obs);
        action.doAction();
        verify(obs, times(1)).update(action, message);
    }

    @Test
    public void testToString() {
        assertEquals("Message: "+message, action.toString());
    }
    
}
