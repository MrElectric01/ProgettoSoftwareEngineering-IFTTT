package progettosoftwareengineering.localifttt.model.rule.trigger.date;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateTriggerTest {

    private String currentDate = LocalDate.now().toString();
    private DateTrigger trigger;

    @Before
    public void setUp() {
        trigger = new DateTrigger(currentDate);
    }

//    In order to verify that checkTrigger for this trigger is triggered when is called
//    the Date specified in the tigger's attributes, we call it after initialize the DateTrigger
//    at the currentDate.
    @Test
    public void testCheckTriggerTrue() {
        assertTrue(trigger.checkTrigger());
    }

//    In order to verify that checkTrigger for this trigger isn't triggered when is called
//    at a different Date from the specified one in the tigger's attributes, we call it after 
//    have initialized a DateTrigger with a day over the current one.
    @Test
    public void testCheckTriggerFalse() {
        DateTrigger trigger2 = new DateTrigger(LocalDate.now().plusDays(1).toString());
        assertFalse(trigger2.checkTrigger());
    }

    @Test
    public void testToString() {
        assertEquals("Date: " + currentDate, trigger.toString());
    }
}
