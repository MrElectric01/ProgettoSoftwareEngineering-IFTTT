package progettosoftwareengineering.localifttt.model.rule.action.movefile;

import java.io.*;
import java.nio.file.*;
import progettosoftwareengineering.localifttt.model.rule.action.Action;

//Action for moving a file to a directory.
public class MoveFileAction extends Action {
    
    private final File file;
    private final File directory;

    public MoveFileAction(String filePath, String directoryPath) {
        this.file = new File(filePath);
        this.directory = new File(directoryPath, file.getName());
    }
    
//    Executes a MoveFileAction moving the passed file into the passed directory (replace if existing), 
//    and notifies the controller. An exception is launched the file to move, or the target directory
//    doesn't exist anymore.
    @Override
    public void doAction() {
        Path sourcePath = file.toPath();
        Path targetPath = directory.toPath();
        try {
            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            setChanged();
            notifyObservers(new String[] {"The file to move \"" + file + "\"\nor the target directory \"" + directory + "\" doesn't exist anymore!", "Directory or File not found"});
            return;
        }
        setChanged();
        notifyObservers(new String[] {"File " + file + " moved to " + directory});
    }
    
    @Override
    public String toString() {
        String directoryName = new File(directory.getParent()).getName();
        return "File to move from: " + file.getName() +"\nTo: " + directoryName;
    }
}
