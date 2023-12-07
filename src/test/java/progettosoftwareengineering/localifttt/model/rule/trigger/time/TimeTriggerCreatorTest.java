/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule.trigger.time;

import java.util.*;
import org.junit.*;

import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;
import progettosoftwareengineering.localifttt.model.rule.trigger.TriggerType;


import static org.junit.Assert.assertTrue;

public class TimeTriggerCreatorTest {

//    In order to verify that this creator return the right Trigger,
//    we call the createTrigger method with the TriggerType "TIME", and the
//    appropried parameters, and verify that the returned Trigger is an
//    instance of TimeTrigger.
    @Test
    public void testCreateTrigger() {
        TimeTriggerCreator TTC = new TimeTriggerCreator();
        Map<String,String> trigParam = new HashMap<String,String> ();
        trigParam.put("timeTriggerHours", "0");
        trigParam.put("timeTriggerMinutes", "0");
        
        Trigger trigger = TTC.createTrigger(TriggerType.TIME, trigParam);
        assertTrue(trigger instanceof TimeTrigger);
    }
}