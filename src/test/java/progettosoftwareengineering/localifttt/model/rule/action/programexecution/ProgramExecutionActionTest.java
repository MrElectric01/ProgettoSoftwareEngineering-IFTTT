package progettosoftwareengineering.localifttt.model.rule.action.programexecution;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.Observer;
import org.junit.*;
import static org.mockito.Mockito.*;

public class ProgramExecutionActionTest {

    private final String interpreter = "java -jar";
    private final String programPath;
    private final String arguments = "test %\\r 2";
    private ProgramExecutionAction action;
    private final String fileToRead = "outputTestProgram.csv";
    private Observer obs = mock(Observer.class);

    
//    After retrieving the test program path using getResource() from the specific project folder, 
//    we first decode it correctly, and then replace the '/' with '\' to obtain the same path format
//    returned by the FileChooser through the UI.
//    TestProgram.jar is a build of a test program that, after a sleeping time (to simulate a long execution) write in a file (fileToRead)
//    the command line passed arguments.
    public ProgramExecutionActionTest() throws UnsupportedEncodingException, URISyntaxException {
        programPath = URLDecoder.decode(ProgramExecutionActionTest.class.getResource("TestProgram.jar").getPath().substring(1), "UTF-8");
    }

    @Before
    public void setUp() {
        action = new ProgramExecutionAction(interpreter, programPath, arguments);
    }

//    In order to verify that if the executbale is executed, we execute the doAction
//    of the action created in the setUp, and verify both, read the created file and verify
//    that his content is the passed arguments, and that the observer receives the String printed
//    for this behaviour.
    @Test
    public void testDoActionWithoutError() throws InterruptedException, IOException {
        action.addObserver(obs);
        action.doAction();
        action.getProcess().waitFor();

        String line;
        StringBuilder stringBuffer = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Files.delete(Paths.get(fileToRead));

        assertEquals(arguments, stringBuffer.toString());
        verify(obs, times(1)).update(action, new String[] {"The program \"" + programPath + "\" is running with the arguments: " + arguments});
    }
    
//    In order to verify that we have an error if the executbale doesn't exist, we execute the doAction
//    of a new Action with a FakeProgram, and verify that the observer receives the String printed
//    for this error behaviour.
    @Test
    public void testDoActionWithError() throws InterruptedException, IOException {
        ProgramExecutionAction action2 = new ProgramExecutionAction(interpreter, "FakeProgram", arguments);
        action2.addObserver(obs);
        action2.doAction();
        verify(obs, times(1)).update(action2, new String[] {"The executable file: \"FakeProgram\" is not running! "
                    + "If the file is not an .exe make sure the interpreter is accessible from the environment variable \"PATH\","
                    + "or be sure that the executable is still available!", "Executable Not Running"});
    }

    @Test
    public void testToString() {
        assertEquals("Program path: " + programPath, action.toString());
    }

}
