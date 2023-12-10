package progettosoftwareengineering.localifttt.model.rule.trigger.dayofmonth;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DayOfMonthTriggerTest {

    private String dayOfMonth = String.valueOf(LocalDate.now().getDayOfMonth());
    private DayOfMonthTrigger trigger;

    @Before
    public void setUp() {
        trigger = new DayOfMonthTrigger(dayOfMonth);
    }

//    In order to verify that checkTrigger for this trigger is triggered when is called
//    the DayOfMonth specified in the tigger's attributes, we call it after initialize the DayOfMonthTrigger
//    at the current dayOfMonth.
    @Test
    public void testCheckTriggerTrue() {
        assertTrue(trigger.checkTrigger());
    }

//    In order to verify that checkTrigger for this trigger isn't triggered when is called
//    at a different dayOfMonth from the specified one in the tigger's attributes, we call it after 
//    have initialized a DayOfMonthTrigger with a day over th current one.
    @Test
    public void testCheckTriggerFalse() {
        DayOfMonthTrigger trigger2 = new DayOfMonthTrigger(String.valueOf(LocalDate.now().plusDays(1).getDayOfMonth()));
        assertFalse(trigger2.checkTrigger());
    }

    @Test
    public void testToString() {
        assertEquals("Day of Month: " + dayOfMonth, trigger.toString());
    }
}
