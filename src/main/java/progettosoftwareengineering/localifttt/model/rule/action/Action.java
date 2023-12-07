package progettosoftwareengineering.localifttt.model.rule.action;

import java.io.Serializable;
import java.util.Observable;

public abstract class Action extends Observable implements Serializable {
    public abstract void doAction();
}
