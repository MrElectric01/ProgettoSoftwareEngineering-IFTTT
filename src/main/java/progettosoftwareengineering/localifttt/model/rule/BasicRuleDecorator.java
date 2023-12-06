/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule;

import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

//This is an abstract class, because we don't need to use it.
//We use the protected method "changed()" to notifyObservers,
//in the same method of the ConcreteComponent, because if we use
//a Decorator, the Observed class is this and not the Wrapped one. 
public abstract class BasicRuleDecorator extends Rule {
    private Rule ruleDecorated;

    public BasicRuleDecorator(Rule rule) {
        ruleDecorated = rule;
    }

    @Override
    public String getName() {
        return ruleDecorated.getName();
    }

    @Override
    public Trigger getTrigger() {
        return ruleDecorated.getTrigger();
    }

    @Override
    public Action getAction() {
        return ruleDecorated.getAction();
    }

    @Override
    public String getStatus() {
        return ruleDecorated.getStatus();
    }

    @Override
    public void switchStatus() {
        ruleDecorated.switchStatus();
        changed();
    }

    @Override
    public boolean checkRule() {
        return ruleDecorated.checkRule();
    }

    @Override
    public void activateRule() {
        ruleDecorated.activateRule();
        changed();
    }
}
