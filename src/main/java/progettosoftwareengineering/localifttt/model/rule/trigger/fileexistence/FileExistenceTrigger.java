package progettosoftwareengineering.localifttt.model.rule.trigger.fileexistence;

import java.io.File;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

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
        return "File to check existence: " + file.getAbsolutePath();
    }
}
