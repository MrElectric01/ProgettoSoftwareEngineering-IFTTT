package progettosoftwareengineering.localifttt.model.rule.trigger.filedimension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

public class FileDimensionTrigger extends Trigger {

    private File file;
    private long sizeThreshold;
    private String sizeUnit;

    public FileDimensionTrigger(String filePath, String sizeThreshold, String sizeUnit) {
        this.file = new File(filePath);
        this.sizeThreshold = Long.parseLong(sizeThreshold);
        this.sizeUnit = sizeUnit.toUpperCase();
    }

//     Triggers the Trigger when the specified file exists and its size is greater
//     than the specified threshold.
    @Override
    public boolean checkTrigger() {
        long fileSize = 0;
        if (file.exists()) {
            try {
                fileSize = Files.size(file.toPath());
            } catch (IOException ex) {
//                if the file doesn't exist, we consider its size to be 0.
            }
        }
        return fileSize > convertToBytes(sizeThreshold, sizeUnit);
    }

    private long convertToBytes(long size, String unit) {
        switch (unit) {
            case "KB":
                return size * 1024;
            case "MB":
                return size * 1024 * 1024;
            case "GB":
                return size * 1024 * 1024 * 1024;
            default:
                return size;
        }
    }

    @Override
    public String toString() {
        return "File to check dimension: " + file.getAbsolutePath() + "\n(Threshold: " + sizeThreshold + " " + sizeUnit + ")";
    }
}
