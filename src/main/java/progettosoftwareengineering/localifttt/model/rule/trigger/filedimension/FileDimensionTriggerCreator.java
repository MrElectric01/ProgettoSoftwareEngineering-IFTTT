package progettosoftwareengineering.localifttt.model.rule.trigger.filedimension;

import java.util.Map;
import progettosoftwareengineering.localifttt.model.rule.trigger.*;

public class FileDimensionTriggerCreator extends BaseTriggerCreator {

    @Override
    public Trigger createTrigger(TriggerType trigger, Map<String, String> trigParam) {
        if (trigger.equals(TriggerType.FILE_DIMENSION)) {
            return new FileDimensionTrigger(trigParam.get("fileDimensionTriggerFilePath"), trigParam.get("fileDimensionTriggerSizeThreshold"), trigParam.get("fileDimensionTriggerSizeUnit"));
        } else {
            return this.nextCreator(trigger, trigParam);
        }
    }
}
