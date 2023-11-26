/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt;

import java.time.LocalTime;
import org.junit.*;
import static org.junit.Assert.*;

public class TimeTriggerTest {
    
    private String hour = String.valueOf(LocalTime.now().getHour());
    private String minute = String.valueOf(LocalTime.now().getMinute());
    private TimeTrigger trigger;

    @Before
    public void setUp() {
        trigger = new TimeTrigger(hour, minute);
    }
    
//    In order to verify that cheackTrigger for this trigger is triggered when is called
//    at the time specified in the tigger's attributes, we call it after initialize the TimeTrigger
//    at the current time.
    @Test
    public void testCheckTriggerTrue() {
        assertTrue(trigger.checkTrigger());
    }
    
//    In order to verify that cheackTrigger for this trigger isn't triggered when is called
//    at a different time from the specified one in the tigger's attributes, we call it after 
//    have initialized a TimeTrigger with a minute over the current time.
    @Test
    public void testCheckTriggerFalse() {
        TimeTrigger trigger2 = new TimeTrigger(hour, String.valueOf(Integer.parseInt(minute) + 1));
        assertFalse(trigger2.checkTrigger());
    }

    @Test
    public void testToString() {
        assertEquals("Times: "+hour+":"+minute, trigger.toString());
    }
    
}
