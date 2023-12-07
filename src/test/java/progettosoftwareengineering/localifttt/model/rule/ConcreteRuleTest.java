package progettosoftwareengineering.localifttt.model.rule;

import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConcreteRuleTest {

    private Trigger mockTrigger;

    private Action mockAction;

    private Rule rule;

    @Before
    public void setUp() {
        mockTrigger = mock(Trigger.class);
        mockAction = mock(Action.class);
        rule = new ConcreteRule("TestRule", mockTrigger, mockAction);
    }

    @Test
    public void testGetName() {
        assertEquals("TestRule", rule.getName());
    }
    
//    In order to verify that the get method returns the rigth Trigger,
//    we check that is equal to the Trigger created in the setUp. 
    @Test
    public void testGetTrigger() {
        assertEquals(mockTrigger, rule.getTrigger());
    }

//    In order to verify that the get method returns the rigth Action,
//    we check that is equal to the Action created in the setUp. 
    @Test
    public void testGetAction() {
        assertEquals(mockAction, rule.getAction());
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
    
//    In order to verify that checkRule method return false when the rule
//    is disable, even if the Trigger is true, we set the mock return of the checkTrigger
//    method at "true", but disable the rule, and so check if the checkRule is false.
    @Test
    public void testCheckRuleDisabled() {
        when(mockTrigger.checkTrigger()).thenReturn(true);
        rule.switchStatus();
        assertFalse(rule.checkRule());
    }

//    In order to verify that activeRule method execute the Action's
//    doAction() method, we verify both, with the specific Mockito's method,
//    that the doAction() method is executed, and the RuleStatus is disabled.
    @Test
    public void testActivateRule() {
        rule.activateRule();
        verify(mockAction, times(1)).doAction();
        assertEquals("Disabled", rule.getStatus());
    }
}
