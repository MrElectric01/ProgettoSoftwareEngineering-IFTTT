/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class RuleCollectionTest {

    private Rule rule;

    private RuleCollection ruleCollection;
    
//    Get the istance of the Rule Collection and add a Rule.
//    These are useful for all the tests.
//    We must delete the Observer because we don't want the autosave.
    @Before
    public void setUp() {
        rule = spy(new ConcreteRule("TestRule", null, null));
        RuleCollection.getInstance().deleteObservers();
        ruleCollection = RuleCollection.getInstance();
        ruleCollection.addRule(rule);
    }
    
//    In order to make the tests indipendent,
//    we clear the Collection after every test.
    @After
    public void cleanUp() {
        ruleCollection.getRules().clear();
    }

//    In order to verify that the implementation of the Singleton pattern is correctly done,
//    we get a second instance, and verify if the second one is the same object of the first one.
    @Test
    public void testGetInstance() {
        RuleCollection instance2 = RuleCollection.getInstance();

        assertEquals(ruleCollection, instance2);
    }
    
//    In order to veirify if the addition into the Collection is correct,
//    we verify if the Collection contains the Rule added in the Setup,
//    the added rule is observed.
    @Test
    public void testAddRule() {
        assertTrue(ruleCollection.getRules().contains(rule));
        assertEquals(1, rule.countObservers());
    }
    
//    In order to veirify if the addition into the RuleCollection from another
//    Collection with this method is correct, we verify if the RuleCollection 
//    contains the two new Rule created in this test method, imported from
//    a created List<Rule>.
    @Test
    public void testAddAll() {
        Rule rule1 = new ConcreteRule("TestRule1", null, null);
        Rule rule2 = new ConcreteRule("TestRule2", null, null);
        List<Rule> list = new ArrayList();
        list.add(rule1);
        list.add(rule2);
        ruleCollection.addAll(list);
        assertTrue(ruleCollection.getRules().contains(rule1));
        assertTrue(ruleCollection.getRules().contains(rule2));
    }

//    In order to verify if the deletion of a Rule from the Collection is correct,
//    we elete the Rule added in the Setup, and verify if the Collection is empty.
    @Test
    public void testDeleteRule() {
        ruleCollection.deleteRule(rule);
        assertTrue(ruleCollection.getRules().isEmpty());
    }

//    In order to verify if the iteration of the Collection is correctly done,
//    we add a second Rule into the ruleCollection and add all the rules into a second collection
//    and verify if this second collection contains all the elements of the ruleCollection.
    @Test
    public void testIterator() {
        Rule rule2 = mock(Rule.class);
        ruleCollection.addRule(rule2);
        
        List<Rule> copy = new ArrayList();

        for (Rule r : ruleCollection) {
            copy.add(r);
        }

        assertTrue(copy.containsAll(ruleCollection.getRules()));
    }
}
