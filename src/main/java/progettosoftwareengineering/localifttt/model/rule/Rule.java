/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule;

import java.io.Serializable;

import progettosoftwareengineering.localifttt.model.rule.action.Action;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

import java.util.Observable;

//Abstract class useful for the application of Decorator pattern (Component), 
//which concreteComponent is ConcreteRule.
public abstract class Rule extends Observable implements Serializable{

    public abstract String getName();
    
    public abstract Trigger getTrigger();
    
    public abstract Action getAction();
    
    public abstract String getStatus();
    
    public abstract void switchStatus();
    
    public abstract boolean checkRule();

    public abstract void activateRule();
    
    protected void changed() {
        setChanged();
        notifyObservers();
    }
}