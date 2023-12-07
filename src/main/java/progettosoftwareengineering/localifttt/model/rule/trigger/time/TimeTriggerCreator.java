/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule.trigger.time;

import java.util.Map;

import progettosoftwareengineering.localifttt.model.rule.trigger.*;

public class TimeTriggerCreator extends BaseTriggerCreator {

    @Override
    public Trigger createTrigger(TriggerType trigger, Map<String,String>  trigParam) {
        if(trigger.equals(TriggerType.TIME))
            return new TimeTrigger(trigParam.get("timeTriggerHours").toString(), trigParam.get("timeTriggerMinutes").toString());
        else
            return this.nextCreator(trigger, trigParam);
    }
    
}
