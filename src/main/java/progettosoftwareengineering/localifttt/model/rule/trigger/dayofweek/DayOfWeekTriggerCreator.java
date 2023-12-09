package progettosoftwareengineering.localifttt.model.rule.trigger.dayofweek;

import java.util.Map;
import progettosoftwareengineering.localifttt.model.rule.trigger.*;

public class DayOfWeekTriggerCreator extends BaseTriggerCreator {

    @Override
    public Trigger createTrigger(TriggerType trigger, Map<String, String> trigParam) {
        if (trigger.equals(TriggerType.DAY_OF_WEEK))
            return new DayOfWeekTrigger(trigParam.get("dayOfWeekTriggerDayOfWeek"));
        else
            return this.nextCreator(trigger, trigParam);
    }
}
