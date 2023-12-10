package progettosoftwareengineering.localifttt.model.rule.action.copyfile;

import java.io.*;
import java.util.Observer;
import org.junit.Test;
import java.nio.file.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CopyFileActionTest {
    
    private String file = "test.txt";
    private String directory = "test";
    private CopyFileAction action = new CopyFileAction(file, directory);
    private Observer obs = mock(Observer.class);

//    In order to verify that if the file and the directory exists,
//    the doAction of the CopyFileAction copy the file in the directory,
//    we create the file and the directory and call the testHelper passing,
//    "true" because we expect that the file is copied and the String printed
//    for this behaviour.
    @Test
    public void testDoActionNoError() throws IOException {
        Files.createDirectory(Paths.get(directory));
        Files.createFile(Paths.get(file));
        
        testHelper(true, new String[] {"File " + file + " copied to " + directory + File.separator + file});
        
        Files.delete(Paths.get(directory + "/" + file));
        Files.delete(Paths.get(file));
        Files.delete(Paths.get(directory));
    }
    
//    In order to verify that if the directory exists but the file doesn't,
//    the doAction of the CopyFileAction doesn't copy the file in the directory,
//    we create only the directory and call the testHelper passing,
//    "false" because we expect that the file isn't copied and the String printed
//    for this error behaviour.
    @Test
    public void testDoActionNoFile() throws IOException {
        Files.createDirectory(Paths.get(directory));
        
        testHelper(false, new String[] {"The file to copy \"" + file + "\"\nor the target directory \"" + directory + File.separator + file + "\" doesn't exist anymore!", "Directory or File not found"});
        
        Files.delete(Paths.get(directory));
    }
    
//    In order to verify that if the file exists but the directory doesn't,
//    the doAction of the CopyFileAction doesn't copy the file in the directory,
//    we create only the file and call the testHelper passing,
//    "false" because we expect that the file isn't copied and the String printed
//    for this error behaviour.
        @Test
    public void testDoActionNoDirectory() throws IOException {
        Files.createFile(Paths.get(file));
        
        testHelper(false, new String[] {"The file to copy \"" + file + "\"\nor the target directory \"" + directory + File.separator + file + "\" doesn't exist anymore!", "Directory or File not found"});

        Files.delete(Paths.get(file));
    }
        
//    We observe the action and compare the eixstence of the file with the passed boolean,
//    and verify that the update is called with the right parameters.
    private void testHelper(boolean created, String[] updateArg) {
        action.addObserver(obs);
        action.doAction();
        assertEquals(created, new File(directory + "/" + file).exists());
        verify(obs, times(1)).update(action, updateArg);
    }
    
    @Test
    public void testToString() {
        assertEquals("File to copy from: " + new File(file).getName() +"\nTo: " + new File(directory).getName(), action.toString());
    }
}
