package progettosoftwareengineering.localifttt.model.rule.trigger.dayofmonth;

import java.util.Map;
import progettosoftwareengineering.localifttt.model.rule.trigger.BaseTriggerCreator;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;
import progettosoftwareengineering.localifttt.model.rule.trigger.TriggerType;

public class DayOfMonthTriggerCreator extends BaseTriggerCreator {

    @Override
    public Trigger createTrigger(TriggerType trigger, Map<String, String> trigParam) {
        if (trigger.equals(TriggerType.DAY_OF_MONTH)) {
            return new DayOfMonthTrigger(trigParam.get("dayOfMonthTriggerDayOfMonth"));
        } else {
            return this.nextCreator(trigger, trigParam);
        }
    }
}
