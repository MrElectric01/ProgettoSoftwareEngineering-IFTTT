package progettosoftwareengineering.localifttt.model.rule.trigger.dayofweek;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;
import progettosoftwareengineering.localifttt.model.rule.trigger.TriggerType;

public class DayOfWeekTriggerCreatorTest {

//    In order to verify that this creator return the right Trigger,
//    we call the createTrigger method with the TriggerType "DAY_OF_WEEK", and the
//    appropried parameters, and verify that the returned Trigger is an
//    instance of DayOfWeekTrigger.
    @Test
    public void testCreateTrigger() {
        DayOfWeekTriggerCreator DOWTC = new DayOfWeekTriggerCreator();
        Map<String, String> trigParam = new HashMap();
        trigParam.put("dayOfWeekTriggerDayOfWeek", "MONDAY");

        Trigger trigger = DOWTC.createTrigger(TriggerType.DAY_OF_WEEK, trigParam);
        assertTrue(trigger instanceof DayOfWeekTrigger);
    }
}
