/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule.trigger;

import java.util.Map;

//Abstract class for the base behaviour of a TriggerCreator in the Chain.
public abstract class BaseTriggerCreator {
//    Reference to the next Creator in the chain, initially null.
    private BaseTriggerCreator next = null;

    public BaseTriggerCreator getNext() {
        return next;
    }

//    Method that set the "next" reference.
    protected void setNext(BaseTriggerCreator next) {
        this.next=next;
    } 

//    Abstract Method that all the Creators that extends this class have to Override
//    in order to handle their specific Trigger creation.
    public abstract Trigger createTrigger(TriggerType trigger, Map trigParam);
    
//    If the Creator can't handle the request, this method will be
//    called to pass it to the next in the chain, if exist.
    protected Trigger nextCreator(TriggerType trigger, Map trigParam) {
        if(next != null)
            return next.createTrigger(trigger, trigParam);
        else
            return null;
    }
}

