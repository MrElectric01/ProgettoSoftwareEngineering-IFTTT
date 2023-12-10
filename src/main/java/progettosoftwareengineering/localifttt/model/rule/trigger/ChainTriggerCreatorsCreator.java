package progettosoftwareengineering.localifttt.model.rule.trigger;

import progettosoftwareengineering.localifttt.model.rule.trigger.date.DateTriggerCreator;
import progettosoftwareengineering.localifttt.model.rule.trigger.dayofmonth.DayOfMonthTriggerCreator;
import progettosoftwareengineering.localifttt.model.rule.trigger.time.TimeTriggerCreator;
import progettosoftwareengineering.localifttt.model.rule.trigger.dayofweek.DayOfWeekTriggerCreator;
import progettosoftwareengineering.localifttt.model.rule.trigger.fileexistence.FileExistenceTriggerCreator;

//Class that create the Trigger Creators chain.
public class ChainTriggerCreatorsCreator {
//    Reference to the first creator of the chain.
    private static BaseTriggerCreator first;
    
    private static ChainTriggerCreatorsCreator instance = null;
    
//    Constructor that initialize all the Creators and build the chain.
    private ChainTriggerCreatorsCreator() {
        BaseTriggerCreator TTC = new TimeTriggerCreator();
        BaseTriggerCreator DOWTC = new DayOfWeekTriggerCreator();
        BaseTriggerCreator DOMTC = new DayOfMonthTriggerCreator();
        BaseTriggerCreator DTC = new DateTriggerCreator();
        BaseTriggerCreator FETC = new FileExistenceTriggerCreator();
        
        first = TTC;
        TTC.setNext(DOWTC);
        DOWTC.setNext(DOMTC);
        DOMTC.setNext(DTC);
        DTC.setNext(FETC);
    }
    
//    This method uses the Singleton pattern and also returns the first Creator of the chain. 
    public static BaseTriggerCreator chain() {
        if(instance == null)
            instance = new ChainTriggerCreatorsCreator();
        return first;
    }
}
