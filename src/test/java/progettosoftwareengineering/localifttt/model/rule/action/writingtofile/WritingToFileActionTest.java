package progettosoftwareengineering.localifttt.model.rule.action.writingtofile;

import java.io.*;
import java.nio.file.*;
import java.util.Observer;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class WritingToFileActionTest {

    private final String filePath = "test.txt";
    private final String textToAppend = "toAppendStringTest";
    private WritingToFileAction action;
    private Observer obs;

    @Before
    public void setUp() {
        action = new WritingToFileAction(filePath, textToAppend);
        obs = mock(Observer.class);
    }

//  In order to make the tests indipendent,
//  we delete the file after every test.
    @After
    public void cleanUp() {
        try {
            Files.delete(Paths.get(filePath));
        } catch (IOException ex) {
        //the only exception is launched if the file doesn't exist, 
        // and that is what we want.
        }
    } 

//    In order to verify that the action write the right text in the right file,
//    in addition to the created action in the setUp, we create an other action 
//    with a different text but for the same file. 
//    We verify that, after calling the actions doAction, the file contains two lines 
//    with the two action's texts.
//    In addition we instance a mock observer and verify if his update 
//    method is called with the right execution message.
    @Test
    public void testDoActionExistingPath() {
        String firstString = "firstString";
        WritingToFileAction action2 = new WritingToFileAction(filePath, firstString);
        action.addObserver(obs);
        
        String[] readedLines = {"", ""};

        action2.doAction();
        action.doAction();
        

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            readedLines[0] = br.readLine();
            readedLines[1] = br.readLine();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
        
        assertEquals(firstString, readedLines[0]);
        assertEquals(textToAppend, readedLines[1]);
        verify(obs, times(1)).update(action, new String[] {"The text \"" + textToAppend + "\" has been append to file: " + filePath});
    }
    
//    In order to verify that the action notify the observer
//    that launches the error if filePath doesn't exist, we create
//    an action with a ile in a FakeDirectory and instance a mock observer and 
//    verify, after the doAction execution, if the Observer's update method 
//    is called with the error message.
    @Test
    public void testDoActionNotExistingPath() throws IOException {
        String notExistingPath = "FakeDirectory/file.txt";
        WritingToFileAction action2 = new WritingToFileAction(notExistingPath, "testText");
        action2.addObserver(obs);
        action2.doAction();
        verify(obs, times(1)).update(action2, new String[] {"A directory of the file path \"" + notExistingPath + "\" has been deleted!", "Directory removed"});
    }

    @Test
    public void testToString() {
        assertEquals("Text to append: " + textToAppend +"\nFile: " + new File(filePath).getName(), action.toString());
    }
}
