/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule;

import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BackupRulesTest {
    
//    Get the istance of the Rule Collection and add a Rule.
//    These are useful for all the tests.
//    We do a sleep in order to give the right time of saving to the BackupRules Thread.
    @Before
    public void setUp() throws InterruptedException {
        Rule rule = new Rule ("Test Rule", null, null);
        BackupRules.getInstance().setBackupFile("TestBackupRules.bin");
        RuleCollection.getInstance().addRule(rule);
        Thread.sleep(200);
    }
    
//    In order to make the tests indipendent,
//    we clear the Collection after every test.
    @After
    public void cleanUp() {
        RuleCollection.getInstance().getRules().clear();
    }

//    In order to verify that the implementation of the Singleton pattern is correctly done,
//    we get two instance, and verify if the second one is the same object of the first one.
    @Test
    public void testGetInstance() {
        BackupRules instance1 = BackupRules.getInstance();
        BackupRules instance2 = BackupRules.getInstance();
        assertEquals(instance1, instance2);
    }

//    In order to verify that the tested method has saved the Rule created in the setup,
//    we read the same file and verify if there is only one rule, and if that rule has the 
//    same name of the Rule created in the setUp.
    @Test
    public void testRun() {
        List<Rule> backup = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("TestBackupRules.bin")))) {
            backup = (ArrayList<Rule>) ois.readObject();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        assertEquals(1, backup.size());
        assertEquals("Test Rule", backup.get(0).getName());
    }

//    In order to verify that the tested method execute a Thread that runs
//    the runnable class passed, we use a class Boolean attribute that is turned
//    true in the Overrided method that provvide the Runnable interface.
    @Test
    public void testUpdate() {
        assertTrue(BackupRules.getInstance().getExecute());
    }
    
}
