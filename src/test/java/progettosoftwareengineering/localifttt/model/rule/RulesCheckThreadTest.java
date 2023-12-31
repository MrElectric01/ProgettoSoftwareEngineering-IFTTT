package progettosoftwareengineering.localifttt.model.rule;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

public class RulesCheckThreadTest {

//    In order to do all the test on the RulesCheckThread,
//    we add to the RuleCollection a Rule that have a 
//    permanently false Trigger.
//    We must delete the Observer because we don't want the autosave.
    @Before
    public void setUp() {
        Trigger triggerFalse = mock(Trigger.class);
        when(triggerFalse.checkTrigger()).thenReturn(false);
        Action action = mock(Action.class);
        Rule ruleFalse = new ConcreteRule("TestRule", triggerFalse, action);
        RuleCollection.getInstance().deleteObservers();
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
        RulesCheckThread thread1 = RulesCheckThread.getInstance();
        RulesCheckThread thread2 = RulesCheckThread.getInstance();
        assertEquals(thread1, thread2);
    }

//    In order to verify that startChecking method starts the thread if it isn't running,
//    we call the method and check that it is running.
    @Test
    public void testStartChecking() {
        RulesCheckThread.startChecking();
        assertTrue(RulesCheckThread.getInstance().isAlive());
    }

//    In order to verify that the tested method iterates all the rules in the collection, 
//    we first insert a rule whose trigger value is true, and then verify the action 
//    of the inserted Rule has been executed.
    @Test
    public void testRun() throws InterruptedException {
        Trigger triggerTrue = mock(Trigger.class);
        when(triggerTrue.checkTrigger()).thenReturn(true);
        Action action = mock(Action.class);
        Rule ruleTrue = new ConcreteRule("TestRule", triggerTrue, action);
        RuleCollection.getInstance().addRule(ruleTrue);
        
        RulesCheckThread.startChecking();
        
        Thread.sleep(RulesCheckThread.getInstance().getCheckingPeriod()+1000);
        
        verify(action, times(1)).doAction();
    }
}
