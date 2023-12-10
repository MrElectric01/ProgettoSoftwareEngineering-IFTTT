package progettosoftwareengineering.localifttt.model.rule;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BackupRulesTest {

    private final String testBackupFile = "TestBackupRules.bin";
    
//    Get the istance of the Rule Collection and add a Rule.
//    These are useful for all the tests.
//    We do a sleep in order to give the right time of saving to the BackupRules Thread.
    @Before
    public void setUp() throws InterruptedException {
        Rule rule = new ConcreteRule ("Test Rule", null, null);
        BackupRules.getInstance().setBackupFile(testBackupFile);
//        We observe the RuleCollection here because in other test we delete its observers.
        RuleCollection.getInstance().addObserver(BackupRules.getInstance());
        RuleCollection.getInstance().addRule(rule);
        Thread.sleep(500);
    }
 
    
//    In order to make the tests indipendent,
//    we clear the Collection and the file after every test.
    @After
    public void cleanUp() {
        RuleCollection.getInstance().getRules().clear();
        try {
            Files.delete(Paths.get(testBackupFile));
        } catch (IOException e) {
            //the only exception is launched if the file doesn't exist, 
            // and that is what we want.
        }
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
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(testBackupFile)))) {
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

//    In order to verify that the tested method reloads the rules saved in the setup,
//    we check if the returned List has the right size, and if the only Rule is the
//    Rule created in the setUp.
    @Test
    public void testReloadBackupExist() {
        List<Rule> backup = BackupRules.reloadBackup();
        
        assertEquals(1, backup.size());
        assertEquals("Test Rule", backup.get(0).getName());
    }
    
//    In order to verify that the tested method returns an empty List if the Backup
//    file doesn't exist, we delete the file created in the setUp, and we check that the
//    returned list is empty.
    @Test
    public void testReloadBackupNotExist() {
        try {
            Files.delete(Paths.get(testBackupFile));
        } catch (IOException e) {
            //the only exception is launched if the file doesn't exist, 
            // and that is what we want.
        }
        List<Rule> backup = BackupRules.reloadBackup();
        
        assertTrue(backup.isEmpty());
    }
}
