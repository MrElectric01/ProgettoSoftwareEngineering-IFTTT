package progettosoftwareengineering.localifttt.model.rule.action.programexecution;

import java.io.*;
import static java.lang.Thread.sleep;
import java.util.*;

import progettosoftwareengineering.localifttt.model.rule.action.Action;

//Action for executing a program.
public class ProgramExecutionAction extends Action {

    private List<String> command;
    private String programPath;
    private String programArguments;
    private transient Process process;

//    In the constructor we build the List that contain the line of a call of the executable
//    from the command line. ProgramArguments and interpreter can be null; 
//    the separator must be a " ".
    public ProgramExecutionAction(String interpreter, String programPath, String programArguments) {
        command = new ArrayList();

        if (interpreter != null) {
            String[] arrayInterpreter = interpreter.split(" ");
            command.addAll(Arrays.asList(arrayInterpreter));
        }

        this.programPath = programPath;
        command.add(programPath);

        if (programArguments != null) {
            this.programArguments = programArguments;
            String[] arrayProgramArguments = programArguments.split(" ");
            command.addAll(Arrays.asList(arrayProgramArguments));
        }
    }

//    Executes a ProgramExecutionAction deleting executing the command, and notifies the controller. 
//    Another customized exception is launche if the process doesn't start 
//    (because isn't still available, or some passed argument is wrong).
    @Override
    public void doAction() {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        try {
            process = processBuilder.start();
//            Just to give the the time to start.
            sleep(200);
            if (!process.isAlive())
                throw new ProcessNotRunningException();
        } catch (ProcessNotRunningException e) {
            setChanged();
            notifyObservers(new String[] {"The executable file: \"" + programPath + "\" is not running! "
                    + "If the file is not an .exe make sure the interpreter is accessible from the environment variable \"PATH\","
                    + "or be sure that the executable is still available!", "Executable Not Running"});
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (InterruptedException ex) { 
            ex.printStackTrace();
        }

        setChanged();
        if (programArguments != null) {
            notifyObservers(new String[] {"The program \"" + programPath + "\" is running with the arguments: " + programArguments});
        } else {
            notifyObservers(new String[] {"The program \"" + programPath + "\" is running."});
        }
    }

//    Method useful only for testing (infact is package).
    Process getProcess() {
        return process;
    }

    @Override
    public String toString() {
        return "Program path: " + programPath;
    }
}