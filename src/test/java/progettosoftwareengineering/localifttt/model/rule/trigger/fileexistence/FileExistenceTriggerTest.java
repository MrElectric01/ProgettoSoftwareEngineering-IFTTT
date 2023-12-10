package progettosoftwareengineering.localifttt.model.rule.trigger.fileexistence;

import java.io.*;
import java.nio.file.*;
import org.junit.*;
import static org.junit.Assert.*;

public class FileExistenceTriggerTest {

    private String directoryPath = "existenceDirectory";
    private String filePath = directoryPath + "/existenceTest.txt";
    private FileExistenceTrigger trigger;

    @Before
    public void setUp() throws IOException {
        trigger = new FileExistenceTrigger(filePath);
        Files.createDirectory(Paths.get(directoryPath));
    }
    
    @After
    public void cleanUp() throws IOException {
        Files.delete(Paths.get(directoryPath));
    }

//    In order to verify that checkTrigger for this trigger is triggered when
//    the File specified in the tigger's attributes exists, we call it after the file creation.
    @Test
    public void testCheckTriggerTrue() throws IOException {
        Files.createFile(Paths.get(filePath));
        assertTrue(trigger.checkTrigger());
        Files.delete(Paths.get(filePath));
    }

//    In order to verify that checkTrigger for this trigger isn't triggered when
//    the File specified in the tigger's attributes doesn't exist, we call it without create the file.
    @Test
    public void testCheckTriggerFalse() {
        assertFalse(trigger.checkTrigger());
    }

    @Test
    public void testToString() {
        assertEquals("File to check existence: " + new File(filePath).getName() + "\nDirectory: " + new File(directoryPath).getName(), trigger.toString());
    }
}
