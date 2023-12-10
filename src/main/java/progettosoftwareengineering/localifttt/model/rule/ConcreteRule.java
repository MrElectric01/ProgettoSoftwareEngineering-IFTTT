package progettosoftwareengineering.localifttt.model.rule;

import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

//ConcreteComponent of the Decorator pattern.
public class ConcreteRule extends Rule{
    private String name;
    private final Trigger trigger;
    private final Action action;
    private boolean status;

    public ConcreteRule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.status = true;
    }

//    These get method is useful for the SetCellValueFactory of the TableView that shows the rules.
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Trigger getTrigger() {
        return trigger;
    }
    
    @Override
    public Action getAction() {
        return action;
    }
    
    @Override
    public String getStatus() {
        if(status)
            return "Enabled";
        return "Disabled";
    }
    
//    Change the Rule status from enable to disable (and viceversa) and notify the Observers.
    @Override
    public synchronized void switchStatus() {
        this.status=!this.status;
        changed();
    }
    
//    The Rule trigger is checked if the Rule is enable (status = true).
    @Override
    public boolean checkRule() {
        if(this.status)
            return trigger.checkTrigger();
        else 
            return false;
    }

//    After the activation of a Rule, we disable it.
    @Override
    public void activateRule() {
        action.doAction();
        switchStatus();
    }
}
