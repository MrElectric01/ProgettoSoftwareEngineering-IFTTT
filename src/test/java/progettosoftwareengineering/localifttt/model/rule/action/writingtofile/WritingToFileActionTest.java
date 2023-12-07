package progettosoftwareengineering.localifttt.model.rule.action.writingtofile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.*;


import static org.junit.Assert.assertEquals;

public class WritingToFileActionTest {

    private final String filePath = "test.txt";
    private final String textToAppend = "toAppendStringTest";
    private WritingToFileAction action;

    @Before
    public void setUp() {
        action = new WritingToFileAction(filePath, textToAppend);
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

//  In order to verify that the action write the right text in the right file,
//  in addition to the created action in the setUp, we create an other action 
//  with a different text but for the same file. 
//  We verify that, after calling the actions doAction, the file contains two lines 
//  with the two action's texts.
    @Test
    public void testDoAction() {
        String firstString = "firstString";
        WritingToFileAction action2 = new WritingToFileAction(filePath, firstString);
        
        String[] readedLines = {"", ""};

        action2.doAction();
        action.doAction();
        

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            readedLines[0] = br.readLine();
            readedLines[1] = br.readLine();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
        
        assertEquals(firstString, readedLines[0]);
        assertEquals(textToAppend, readedLines[1]);
    }

    @Test
    public void testToString() {
        assertEquals("Text to append: " + textToAppend +"\nFile: " + filePath, action.toString());
    }

}
