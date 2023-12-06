/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule;

import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

public class PeriodicallyRuleDecoratorTest {
    private Rule rule;

//    For all the test is useful to have a ConcreteRule to wrap.
    @Before
    public void setUp() {
        Trigger mockTrigger = mock(Trigger.class);
        Action mockAction = mock(Action.class);
        rule = new ConcreteRule("TestRule", mockTrigger, mockAction);
    }
    
//    In order to verify that the Rule is reactivated if the SleepingPeriod si passed and the 
//    Rule ins't already enabled, we check all the four possible combination, thanks to a parametrizedTestMethod.
    @Test
    public void testCheckRuleDisabledSleepingPeriodPassed() throws InterruptedException {
        testCheckRuleParametrized(new SleepingPeriod(0, 0, 0), false, "Enabled", true);
    }

    @Test
    public void testCheckRuleEnabledSleepingPeriodPassed() throws InterruptedException {
        testCheckRuleParametrized(new SleepingPeriod(0, 0, 0), true, "Enabled", true);
    }

    @Test
    public void testCheckRuleEnabledSleepingPeriodNotPassed() throws InterruptedException {
        testCheckRuleParametrized(new SleepingPeriod(0, 0, 1), true, "Enabled", false);
    }

    @Test
    public void testCheckRuleDisabledSleepingPeriodNotPassed() throws InterruptedException {
        testCheckRuleParametrized(new SleepingPeriod(0, 0, 1), false, "Disabled", false);
    }

    private void testCheckRuleParametrized(SleepingPeriod sleepingPeriod, boolean ruleEnabled, String expectedStatus, boolean expectSleepingPeriodPassed) throws InterruptedException {
        PeriodicallyRuleDecorator decorator = new PeriodicallyRuleDecorator(rule, sleepingPeriod);
        decorator.activateRule();
        assertEquals("Disabled", decorator.getStatus());
        Thread.sleep(200);
        if(ruleEnabled) {
            decorator.switchStatus();
            assertEquals("Enabled", decorator.getStatus());
        }
        decorator.checkRule();
        assertEquals(expectSleepingPeriodPassed, decorator.isSleepingPeriodPassed());
        assertEquals(expectedStatus, decorator.getStatus());
    }

//    In order to verify that the activation of a rule, register the ActivationTime, and flag
//    that the we don't reactivated the rule yet, with the dedicated flag, we check both the 
//    LastActivation is between the times before and after the method's call, and that the flag is False.
    @Test
    public void testActivateRule() {
        PeriodicallyRuleDecorator decorator = new PeriodicallyRuleDecorator(rule, new SleepingPeriod(0, 0, 1));
        LocalDateTime before = LocalDateTime.now().minusSeconds(1);
        decorator.activateRule();
        LocalDateTime after = LocalDateTime.now().plusSeconds(1);
        assertTrue(before.isBefore(decorator.getLastActivation()) && after.isAfter(decorator.getLastActivation()));
        assertFalse(decorator.isSleepingPeriodPassed());
    }
}
