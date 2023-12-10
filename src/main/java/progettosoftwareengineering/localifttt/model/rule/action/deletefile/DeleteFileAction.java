package progettosoftwareengineering.localifttt.model.rule.action.deletefile;

import java.io.*;
import java.nio.file.*;
import progettosoftwareengineering.localifttt.model.rule.action.Action;

//Action for deleting a file from a directory.
public class DeleteFileAction extends Action {
    
    private File file;

    public DeleteFileAction(String filePath) {
        this.file = new File(filePath);
    }

//    Executes a DeleteFileAction deleting the passed file, and notifies the controller. 
//    An exception is launched if the file to delete doesn't exist anymore.
    @Override
    public void doAction() {
        Path filePath = file.toPath();
        try {
            Files.delete(filePath);
        } catch (IOException ex) {
            setChanged();
            notifyObservers(new String[] {"The file to delete \"" + file + "\" doesn't exist anymore!", "File not found"});
            return;
        }
        setChanged();
        notifyObservers(new String[] {"File " + file + " deleted"});
    }
    
    @Override
    public String toString() {
        return "File to delete: " + file.getName();
    }
}
