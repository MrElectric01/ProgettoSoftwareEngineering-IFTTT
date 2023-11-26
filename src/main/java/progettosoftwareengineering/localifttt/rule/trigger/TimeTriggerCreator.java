/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule.trigger;

import java.util.Map;

public class TimeTriggerCreator extends BaseTriggerCreator {

    @Override
    public Trigger createTrigger(TriggerType trigger, Map trigParam) {
        if(trigger.equals(TriggerType.TIME))
            return new TimeTrigger(trigParam.get("timeTriggerHour").toString(), trigParam.get("timeTriggerMinutes").toString());
        else
            return this.nextCreator(trigger, trigParam);
    }
    
}
