/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule.trigger;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.assertTrue;

public class TimeTriggerCreatorTest {

//    In order to verify that this creator return the right Trigger,
//    we call the createTrigger method with the TriggerType "TIME", and the
//    appropried parameters, and verify that the returned Trigger is an
//    instance of TimeTrigger.
    @Test
    public void testCreateTrigger() {
        TimeTriggerCreator TTC = new TimeTriggerCreator();
        Map<String,String> trigParam = new HashMap();
        trigParam.put("timeTriggerHour", "0");
        trigParam.put("timeTriggerMinutes", "0");
        
        Trigger trigger = TTC.createTrigger(TriggerType.TIME, trigParam);
        assertTrue(trigger instanceof TimeTrigger);
    }
}
