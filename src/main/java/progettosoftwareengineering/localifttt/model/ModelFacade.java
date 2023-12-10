package progettosoftwareengineering.localifttt.model;

import java.util.List;
import java.util.Map;
import progettosoftwareengineering.localifttt.model.rule.trigger.*;
import progettosoftwareengineering.localifttt.model.rule.*;
import progettosoftwareengineering.localifttt.model.rule.action.*;

//Class useful for handle all the call to the Model classes from the Controllers.
//Not tested because all the methods just do calls to tested methods.
public class ModelFacade {
    
//    Private constructor to prevent instantiation.
    private ModelFacade() {
    }
    
//    Create and return a trigger of the passed Type with the passed Parameters, using the CreatorsChain.
    public static Trigger createTrigger(TriggerType trigger, Map<String,String> trigParam) {
        return ChainTriggerCreatorsCreator.chain().createTrigger(trigger, trigParam);
    }
    
//    Create and return a action of the passed Type with the passed Parameters, using the CreatorsChain.
    public static Action createAction(ActionType action, Map<String,String> actParam, ActionController controller) {
        Action newAction = ChainActionCreatorsCreator.chain().createAction(action, actParam);
        controller.observeAction(newAction);
        return newAction;
    }
    
//    Delete a rule from RuleCollection.
    public static void deleteRuleFromRuleCollection(Rule rule) {
        RuleCollection.getInstance().deleteRule(rule);
    }
    
//    Return the instance of the RuleCollection.
    public static RuleCollection getRuleCollection() {
        return RuleCollection.getInstance();
    }
    
//    Add a new Rule to the RuleCollection and start the CheckingThread (deactivated for default).
//    The Rule is decorated or not with the SleepingPeriod, based on the passed boolean.
    public static void addToRuleCollection(String ruleName, Trigger ruleTrigger, Action ruleAction, Boolean decorate, Integer days, Integer hours, Integer minutes) {
        Rule rule = new ConcreteRule(ruleName, ruleTrigger, ruleAction);
        if(decorate) 
            rule = new PeriodicallyRuleDecorator(rule, new SleepingPeriod(days, hours, minutes));
        RuleCollection.getInstance().addRule(rule);
        RulesCheckThread.startChecking();
    }
    
//    Switch the status of the passed rule, in the RuleCollection.
    public static void switchRuleStatus(Rule rule) {
        RuleCollection.getInstance().getRules().get(RuleCollection.getInstance().getRules().indexOf(rule)).switchStatus();
    }
    
//    This method reload the Backup file. If the returned List isEmpty ( the file doesn't exist or it's empty)
//    this method does nothing; otherwhise it registers all the Controller Observers to the RuleActions,
//    adds all the rule in the RuleCollection and starts the Checking Thread.
    public static void firstOpening(ActionController controller) {
        List<Rule> backup = BackupRules.reloadBackup();
        if(!backup.isEmpty()) {
            for(Rule rule: backup) {
                controller.observeAction(rule.getAction());
            }
            RuleCollection.getInstance().addAll(backup);
            RulesCheckThread.startChecking();
        }
    }
}
