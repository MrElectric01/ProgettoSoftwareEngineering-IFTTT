package progettosoftwareengineering.localifttt.model.rule.trigger;

import java.io.Serializable;

public abstract class Trigger implements Serializable {
    public abstract boolean checkTrigger();
}
