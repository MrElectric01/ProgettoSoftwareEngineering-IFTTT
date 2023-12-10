package progettosoftwareengineering.localifttt.model.rule.trigger.fileexistence;

import java.util.Map;
import progettosoftwareengineering.localifttt.model.rule.trigger.*;

public class FileExistenceTriggerCreator extends BaseTriggerCreator {

    @Override
    public Trigger createTrigger(TriggerType trigger, Map<String, String> trigParam) {
        if (trigger.equals(TriggerType.FILE_EXISTENCE))
            return new FileExistenceTrigger(trigParam.get("fileExistenceTriggerFilePath"));
        else
            return this.nextCreator(trigger, trigParam);
    }
}