/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;
import progettosoftwareengineering.localifttt.rule.action.Action;
import progettosoftwareengineering.localifttt.rule.trigger.Trigger;

public class RulesCheckServiceTest {
    
//    In order to do all the test on the RulesCheckService,
//    we add to the RuleCollection a Rule that have a 
//    permanently false Trigger.
    @Before
    public void setUp() {
        new JFXPanel();
        Trigger triggerFalse = mock(Trigger.class);
        when(triggerFalse.checkTrigger()).thenReturn(false);
        Action action = mock(Action.class);
        Rule ruleFalse = new Rule("TestRule", triggerFalse, action);
        RuleCollection.getInstance().addRule(ruleFalse);
    }
    
    @After
    public void cleanUp() {
        RuleCollection.getInstance().getRules().clear();
    }

//    In order to verify that the implementation of the Singleton pattern is correctly done,
//    we get two instance, and verify if the second one is the same object of the first one.
    @Test
    public void testGetInstance() {
        RulesCheckService service1 = RulesCheckService.getInstance();
        RulesCheckService service2 = RulesCheckService.getInstance();
        assertEquals(service1, service2);
    }

//    In order to verify that startChecking method starts the service if it isn't running,
//    we first check it isn't in execution and, after calling the method, we then confirm
//    that it is running.
    @Test
    public void testStartChecking() {
        Boolean[] test = {true};

        Platform.runLater(() -> {
            test[0] = RulesCheckService.getInstance().isRunning();
        });
        waitForFxEvents();

        assertFalse(test[0]);
        
        Platform.runLater(() -> {
            RulesCheckService.startChecking();
            test[0] = RulesCheckService.getInstance().isRunning();
        });
        waitForFxEvents();

        assertTrue(test[0]);
    }
    
//    In order to verify that the tested method iterates all the rules in the collection, 
//    we first insert a rule whose trigger value is true, and then verify both the action 
//    of the inserted Rule has been executed, and the rule has been removed from the 
//    collection, checking its size (it must contains only the FalseTriggerRule).
    @Test
    public void testCreateTask() {
        Trigger triggerTrue = mock(Trigger.class);
        when(triggerTrue.checkTrigger()).thenReturn(true);
        Action action = mock(Action.class);
        Rule ruleTrue = new Rule("TestRule", triggerTrue, action);
        RuleCollection.getInstance().addRule(ruleTrue);
        
        Platform.runLater(() -> {
            RulesCheckService.startChecking();
            try {
                Thread.sleep(RulesCheckService.getInstance().getCheckingPeriod()+1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        waitForFxEvents();
        
        verify(action, times(1)).doAction();
    }
    
}
