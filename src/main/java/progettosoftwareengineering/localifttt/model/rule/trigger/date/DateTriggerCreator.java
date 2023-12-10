package progettosoftwareengineering.localifttt.model.rule.trigger.date;

import java.util.Map;
import progettosoftwareengineering.localifttt.model.rule.trigger.*;

public class DateTriggerCreator extends BaseTriggerCreator {

    @Override
    public Trigger createTrigger(TriggerType trigger, Map<String, String> trigParam) {
        if (trigger.equals(TriggerType.DATE)) {
            return new DateTrigger(trigParam.get("dateTriggerDate"));
        } else {
            return this.nextCreator(trigger, trigParam);
        }
    }
}
