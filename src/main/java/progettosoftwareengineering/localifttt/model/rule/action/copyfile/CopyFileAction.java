package progettosoftwareengineering.localifttt.model.rule.action.copyfile;

import java.io.*;
import java.nio.file.*;
import progettosoftwareengineering.localifttt.model.rule.action.Action;

//Action for copying a file to a directory.
public class CopyFileAction extends Action{
    
    private final File file;
    private final File directory;

    public CopyFileAction(String filePath, String directoryPath) {
        this.file = new File(filePath);
        this.directory = new File(directoryPath, file.getName());
    }

//    Executes a CopyFileAction copying the passed file into the passed directory (replace if existing), 
//    and notifies the controller. An exception is launched if the file to copy, or the target directory
//    doesn't exist anymore.
    @Override
    public void doAction() {
        Path sourcePath = file.toPath();
        Path targetPath = directory.toPath();
        try {
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            setChanged();
            notifyObservers(new String[] {"The file to copy \"" + file + "\"\nor the target directory \"" + directory + "\" doesn't exist anymore!", "Directory or File not found"});
            return;
        }
        setChanged();
        notifyObservers(new String[] {"File " + file + " copied to " + directory});
    }
    
    @Override
    public String toString() {
        String directoryName = new File(directory.getParent()).getName();
        return "File to copy from: " + file.getName() +"\nTo: " + directoryName;
    }
}
