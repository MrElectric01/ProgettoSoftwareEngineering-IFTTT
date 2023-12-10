package progettosoftwareengineering.localifttt.model.rule.trigger.dayofmonth;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import progettosoftwareengineering.localifttt.model.rule.trigger.*;

public class DayOfMonthTriggerCreatorTest {

//    In order to verify that this creator return the right Trigger,
//    we call the createTrigger method with the TriggerType "DAY_OF_MONTH", and the
//    appropried parameters, and verify that the returned Trigger is an
//    instance of DayOfMonthTrigger.
    @Test
    public void testCreateTrigger() {
        DayOfMonthTriggerCreator DOMTC = new DayOfMonthTriggerCreator();
        Map<String, String> trigParam = new HashMap();
        trigParam.put("dayOfMonthTriggerDayOfMonth", "20");

        Trigger trigger = DOMTC.createTrigger(TriggerType.DAY_OF_MONTH, trigParam);
        assertTrue(trigger instanceof DayOfMonthTrigger);
    }
}
