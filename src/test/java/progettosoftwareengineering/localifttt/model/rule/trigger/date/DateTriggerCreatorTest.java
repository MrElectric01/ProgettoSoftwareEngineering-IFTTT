package progettosoftwareengineering.localifttt.model.rule.trigger.date;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import progettosoftwareengineering.localifttt.model.rule.trigger.*;
public class DateTriggerCreatorTest {

//    In order to verify that this creator return the right Trigger,
//    we call the createTrigger method with the TriggerType "DATE", and the
//    appropried parameters, and verify that the returned Trigger is an
//    instance of DateTrigger.
    @Test
    public void testCreateTrigger() {
        DateTriggerCreator DTC = new DateTriggerCreator();
        Map<String, String> trigParam = new HashMap();
        trigParam.put("dateTriggerDate", "2023-12-31");

        Trigger trigger = DTC.createTrigger(TriggerType.DATE, trigParam);
        assertTrue(trigger instanceof DateTrigger);
    }
}