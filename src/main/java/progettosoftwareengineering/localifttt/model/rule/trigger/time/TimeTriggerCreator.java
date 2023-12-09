package progettosoftwareengineering.localifttt.model.rule.trigger.time;

import java.util.Map;

import progettosoftwareengineering.localifttt.model.rule.trigger.*;

public class TimeTriggerCreator extends BaseTriggerCreator {

    @Override
    public Trigger createTrigger(TriggerType trigger, Map<String,String>  trigParam) {
        if(trigger.equals(TriggerType.TIME))
            return new TimeTrigger(trigParam.get("timeTriggerHours"), trigParam.get("timeTriggerMinutes"));
        else
            return this.nextCreator(trigger, trigParam);
    }
}
