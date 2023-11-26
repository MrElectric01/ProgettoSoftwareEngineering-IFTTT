/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class RuleCollectionTest {

    private Rule rule;

    private RuleCollection ruleCollection;
    
    //Get the istance of the Rule Collection and add a Rule.
    //These are useful for all the tests.
    @Before
    public void setUp() {
        rule = mock(Rule.class);
        ruleCollection = RuleCollection.getInstance();
        ruleCollection.addRule(rule);
    }
    
    //In order to make the tests indipendent,
    //we clear the Collection after every test.
    @After
    public void cleanUp() {
        ruleCollection.getRules().clear();
    }

    //In order to verify that the implementation of the Singleton pattern is correctly done,
    //we get a second instance, and verify if the second one is the same object of the first one.
    @Test
    public void testGetInstance() {
        RuleCollection instance2 = RuleCollection.getInstance();

        assertEquals(ruleCollection, instance2);
    }
    
    //In order to veirify if the addition into the Collection is correct,
    //we verify if the Collection contains the Rule added in the Setup.
    @Test
    public void testAddRule() {
        assertTrue(ruleCollection.getRules().contains(rule));
    }

    //In order to verify if the deletion of a Rule from the Collection is correct,
    //we elete the Rule added in the Setup, and verify if the Collection is empty.
    @Test
    public void testDeleteRule() {
        ruleCollection.deleteRule(rule);
        assertTrue(ruleCollection.getRules().isEmpty());
    }

    //In order to verify if the iteration of the Collection is correctly done,
    //we add a second Rule into the ruleCollection and add all the rules into a second collection
    //and verify if this second collection contains all the elements of the ruleCollection.
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
