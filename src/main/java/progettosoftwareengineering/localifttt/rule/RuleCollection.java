/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RuleCollection extends Observable implements Iterable<Rule>, Observer {
    
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
    
    //Method that adds a rule to the collection.
    public void addRule(Rule rule) {
        rule.addObserver(instance);
        rules.add(rule);
    }
    
    //This method doesn't stop the Service, because if the Collection is empty, the Service stops itself.
    public void deleteRule(Rule rule) {
        rules.remove(rule);
    }

    @Override
    public Iterator iterator() {
        return rules.iterator();
    }

//    When a rule changes, the RuleCollection is setChanged too.
    @Override
    public void update(Observable o, Object arg) {
        instance.setChanged();
        instance.notifyObservers();
    }
}
