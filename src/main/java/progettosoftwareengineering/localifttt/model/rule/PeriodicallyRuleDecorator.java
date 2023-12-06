/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule;

import java.time.LocalDateTime;

//RuleDecorator for the reactivation after a SleepingPeriod.
public class PeriodicallyRuleDecorator extends BasicRuleDecorator {
    private SleepingPeriod sleepingPeriod;
//    Attribute for register the lastActivation moment.
    private LocalDateTime lastActivation;
//    Flag to know that we already reactivate the rule after the SleepingPeriod.
    private boolean sleepingPeriodPassed = true;
    
    public PeriodicallyRuleDecorator(Rule rule, SleepingPeriod sleepingPeriod) {
        super(rule);
        this.sleepingPeriod = sleepingPeriod;
    }

//    Method useful only for testing (infact is package).
    LocalDateTime getLastActivation() {
        return lastActivation;
    }

//    Method useful only for testing (infact is package).
    boolean isSleepingPeriodPassed() {
        return sleepingPeriodPassed;
    }
    
//    In this method if we don't have already reactivated the Rule and the SleepingPeriod is passsed,
//    we flag that we reactivate it and, if the Rule isn't Enabled, we switch its Status. Eventually,
//    we call the super method.
//    We use this method to reactivate the Rule after the SleepingPeriod, because is the only
//    one that is periodically checked by the CheckRulesThread. 
    @Override
    public boolean checkRule() {
        if((!sleepingPeriodPassed) && LocalDateTime.now().isAfter(lastActivation.plusDays(sleepingPeriod.getDays()).plusHours(sleepingPeriod.getHours()).plusMinutes(sleepingPeriod.getMinutes()))) {
            if(super.getStatus().equals("Disabled")) {
                super.switchStatus();
            }
            sleepingPeriodPassed = true;
        }
        return super.checkRule();
    }
    
//    After activation of the Rule, we register the Activation time and
//    flag that the SleepingPeriod is not passed  (so we don't have alredy reactivate the rule).
    @Override
    public void activateRule() {
        super.activateRule();
        lastActivation = LocalDateTime.now();
        sleepingPeriodPassed = false;
    }
}
