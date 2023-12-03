/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule;

import java.util.*;

public class RuleCollection extends Observable implements Iterable<Rule>, Observer {
    
    private List<Rule> rules;
    private static RuleCollection instance = null;
    
//   In addition to initialize the Collection, we set as Deamon the RulesCheckThread,
//   we set as Deamon the BackupRules and register it as observer.
    private RuleCollection() {
        rules = new ArrayList();
        RulesCheckThread.getInstance().setDaemon(true);
    }
    
    public static RuleCollection getInstance() {
        if(instance == null) {
            instance = new RuleCollection();
            instance.addObserver(BackupRules.getInstance());
        }
        return instance;
    }
    
    public List<Rule> getRules() {
        return rules;
    }
    
//    Method that adds a rule to the collection observing it, starting the Thread 
//    and notify the Observers that the RuleCollection is changed.
    public void addRule(Rule rule) {
        rule.addObserver(instance);
        rules.add(rule);
        changed();
    }

    
    public void deleteRule(Rule rule) {
        rules.remove(rule);
        changed();
    }

    @Override
    public Iterator iterator() {
        return rules.iterator();
    }

//    When a rule changes, the RuleCollection is setChanged too.
    @Override
    public void update(Observable o, Object arg) {
        changed();
    }
    
    private void changed() {
        setChanged();
        notifyObservers();
    }
}
