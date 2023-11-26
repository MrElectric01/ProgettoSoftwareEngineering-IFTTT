/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule;

import java.util.Map;
import progettosoftwareengineering.localifttt.rule.trigger.*;
import progettosoftwareengineering.localifttt.rule.action.*;

public class Rule {
    
    private String name;
    private final Trigger trigger;
    private final Action action;

    public Rule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
    }

//    These get method is useful for the SetCellValueFactory of the TableView that shows the rules.
    
    public String getName() {
        return name;
    }
    
    public String getTrigger() {
        return trigger.toString();
    }
    
    public String getAction() {
        return action.toString();
    }
    
    public boolean checkRule() {
        return trigger.checkTrigger();
    }

    public void activateRule() {
        action.doAction();
    }
}