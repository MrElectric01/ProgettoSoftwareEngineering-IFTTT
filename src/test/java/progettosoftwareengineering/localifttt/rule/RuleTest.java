/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule;

import progettosoftwareengineering.localifttt.rule.trigger.Trigger;
import progettosoftwareengineering.localifttt.rule.action.Action;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RuleTest {

    private Trigger mockTrigger;

    private Action mockAction;

    private Rule rule;

    @Before
    public void setUp() {
        mockTrigger = mock(Trigger.class);
        mockAction = mock(Action.class);
        rule = new Rule("TestRule", mockTrigger, mockAction);
    }

    @Test
    public void testGetName() {
        assertEquals("TestRule", rule.getName());
    }
    
//    In order to verify that the get method return the Trigger's toString() results,
//    we set a mock return of the method. 
    @Test
    public void testGetTrigger() {
        when(mockTrigger.toString()).thenReturn("MockTrigger");
        assertEquals("MockTrigger", rule.getTrigger());
    }

//    In order to verify that the get method return the Action's toString() results,
//    we set a mock return of the method. 
    @Test
    public void testGetAction() {
        when(mockAction.toString()).thenReturn("MockAction");
        assertEquals("MockAction", rule.getAction());
    }

//    In order to verify that checkRule method has the same return of the
//    Trigger's checkTrigger method, we set a mock return of the checkTrigger
//    method for the "true" case and also for the "false" case. 
    @Test
    public void testCheckRuleTrue() {
        when(mockTrigger.checkTrigger()).thenReturn(true);
        assertTrue(rule.checkRule());
    }
    
    @Test
    public void testCheckRuleFalse() {
        when(mockTrigger.checkTrigger()).thenReturn(false);
        assertFalse(rule.checkRule());
    }

//    In order to verify that activeRule method execute the Action's
//    doAction() method, we verify, with the specific Mockito's method,
//    that the doAction() method is executed.
    @Test
    public void testActivateRule() {
        rule.activateRule();
        verify(mockAction, times(1)).doAction();
    }
}
