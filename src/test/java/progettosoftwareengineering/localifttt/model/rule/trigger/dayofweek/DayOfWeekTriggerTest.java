package progettosoftwareengineering.localifttt.model.rule.trigger.dayofweek;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DayOfWeekTriggerTest {

    private String dayOfWeek = LocalDate.now().getDayOfWeek().toString();
    private DayOfWeekTrigger trigger;

    @Before
    public void setUp() {
        trigger = new DayOfWeekTrigger(dayOfWeek);
    }

//    In order to verify that checkTrigger for this trigger is triggered when is called
//    the DayOfWeek specified in the tigger's attributes, we call it after initialize the DayOfWeekTrigger
//    at the current dayOfWeek.
    @Test
    public void testCheckTriggerTrue() {
        assertTrue(trigger.checkTrigger());
    }

//    In order to verify that checkTrigger for this trigger isn't triggered when is called
//    at a different dayOfWeek from the specified one in the tigger's attributes, we call it after 
//    have initialized a DayOfWeekTrigger with a day over th current one.
    @Test
    public void testCheckTriggerFalse() {
        DayOfWeekTrigger trigger2 = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek().plus(1).toString());
        assertFalse(trigger2.checkTrigger());
    }

    @Test
    public void testToString() {
        assertEquals("Day of Week: " + dayOfWeek, trigger.toString());
    }
}
