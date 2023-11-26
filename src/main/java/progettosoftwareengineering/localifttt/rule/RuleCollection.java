/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule;

import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RuleCollection implements Iterable<Rule> {
    
    private ObservableList<Rule> rules;
    private static RuleCollection instance = null;
    
   
    private RuleCollection() {
        rules = FXCollections.observableArrayList();
    }
    
    public static RuleCollection getInstance() {
        if(instance == null)
            instance = new RuleCollection();
        return instance;
    }
    
    public ObservableList<Rule> getRules() {
        return rules;
    }
    
    //Method that adds a rule to the collection and triggers the service that checks them.
    public void addRule(Rule rule) {
        rules.add(rule);
        RulesCheckService.startChecking();
    }
    
    //This method doesn't stop the Service, because if the Collection is empty, the Service stops itself.
    public void deleteRule(Rule rule) {
        rules.remove(rule);
    }

    @Override
    public Iterator iterator() {
        return rules.iterator();
    }
}
