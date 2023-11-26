/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class RulesCheckService extends Service<ObservableList<Rule>>{
    
    private RuleCollection rules = RuleCollection.getInstance();
    private static RulesCheckService instance = null;

    private RulesCheckService() {
    }
    
    public static RulesCheckService getInstance() {
        if(instance == null) {
            instance = new RulesCheckService();
        }
        return instance;
    }
    
//      Static method useful to create the Singleton istance of the Service,
//      and check if the Service is running, to start it in "false" case.
    public static void startChecking() {
        if(!RulesCheckService.getInstance().isRunning())
            RulesCheckService.getInstance().start();
    }
    
//    Method that check all the Rules in the collection every 3 seconds,
//    and if a Rule is checked, the Service ask to the Event Thread to
//    execute the associated action.
    @Override
    protected Task<ObservableList<Rule>> createTask() {
        return new Task<ObservableList<Rule>>() {
            @Override
            protected ObservableList<Rule> call() throws Exception {
                while(!rules.getRules().isEmpty()) {
                    Thread.sleep(3000);
                    for (Rule rule : rules) {
                        if(rule.checkRule()) {
                            Platform.runLater(() -> {
                                rule.activateRule();
                                rules.deleteRule(rule);
                                        }
                            );
                        }
                    }
                }
                instance = null;
                return null;
            }
        };
    }
}
