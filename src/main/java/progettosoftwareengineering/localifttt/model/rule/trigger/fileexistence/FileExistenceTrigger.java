package progettosoftwareengineering.localifttt.model.rule.trigger.fileexistence;

import java.io.File;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

//Trigger for a file existence checking
public class FileExistenceTrigger extends Trigger {

    private File file;

    public FileExistenceTrigger(String filePath) {
        file = new File(filePath);
    }

//     Triggers the Trigger when the specified file exists.
    @Override
    public boolean checkTrigger() {
        return file.exists();
    }

    @Override
    public String toString() {
        String directory = new File(file.getParent()).getName();
        return "File to check existence: " + file.getName() + "\nDirectory: " + directory;
    }
}
