package progettosoftwareengineering.localifttt.model.rule.action.deletefile;

import java.io.*;
import java.nio.file.*;
import java.util.Observer;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DeleteFileActionTest {
    
    private String file = "test.txt";
    private DeleteFileAction action = new DeleteFileAction(file);
    private Observer obs = mock(Observer.class);
    
//    In order to verify that if the file exists, the doAction of the DeleteFileAction 
//    delete the file from the directory, we create the file and call the testHelper passing,
//    "true" because we expect that the file exist before the deleting and the String printed
//    for this behaviour.
    @Test
    public void testDoActionNoError() throws IOException {
        Files.createFile(Paths.get(file));
        
        testHelper(true, new String[] {"File " + file + " deleted"});
    }

//    In order to verify that if the doesn't file exists, the doAction of the DeleteFileAction doesn't
//    delete the file from the directory, we create the file and call the testHelper passing,
//    "false" because we expect that the file doesn't exist before the deleting and the String printed
//    for this error behaviour.
    @Test
    public void testDoActionNoFile() throws IOException {
        testHelper(false, new String[] {"The file to delete \"" + file + "\" doesn't exist anymore!", "File not found"});    }
        
//    We observe the action and compare the eixstence of the file with the passed boolean,
//    before to call the doAction() method, and with "false" after,
//    and verify that the update is called with the right parameters.
    private void testHelper(boolean existed, String[] updateArg) {
        File fileToCheck = new File(file);
        action.addObserver(obs);
        assertEquals(existed, fileToCheck.exists());
        action.doAction();
        assertFalse(fileToCheck.exists());
        verify(obs, times(1)).update(action, updateArg);
    }
    
    @Test
    public void testToString() {
        assertEquals("File to delete: " + file, action.toString());
    }
}
