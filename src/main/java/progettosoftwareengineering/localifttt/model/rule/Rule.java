/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule;

import java.io.Serializable;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;
import progettosoftwareengineering.localifttt.model.rule.action.Action;
import java.util.Observable;


public class Rule extends Observable implements Serializable {
    
    private String name;
    private final Trigger trigger;
    private final Action action;
    private boolean status;

    public Rule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.status = true;
    }

//    These get method is useful for the SetCellValueFactory of the TableView that shows the rules.
    
    public String getName() {
        return name;
    }
    
    public Trigger getTrigger() {
        return trigger;
    }
    
    public Action getAction() {
        return action;
    }
    
    public String getStatus() {
        if(status)
            return "Enabled";
        return "Disabled";
    }
    
//    Change the Rule status from enable to disable (and viceversa) and notify the Observers.
    public synchronized void switchStatus() {
        this.status=!this.status;
        changed();
    }
    
//    The Rule trigger is checked if the Rule is enable (status = true).
    public boolean checkRule() {
        if(this.status)
            return trigger.checkTrigger();
        else 
            return false;
    }

    public void activateRule() {
        action.doAction();
    }

    private void changed(){
        setChanged();
        notifyObservers();
    }
}