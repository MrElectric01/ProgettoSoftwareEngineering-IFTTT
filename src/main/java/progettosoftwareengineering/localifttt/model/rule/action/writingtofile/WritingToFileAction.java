package progettosoftwareengineering.localifttt.model.rule.action.writingtofile;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import progettosoftwareengineering.localifttt.model.rule.action.Action;

//Action for the append of a text to a file.
public class WritingToFileAction extends Action{

    private final String filePath;
    private final String textToAppend;

    public  WritingToFileAction(String path, String toAppend){
        this.filePath = path;
        this.textToAppend = toAppend;
    } 

//    Executes a WritingToFileAction writing the passed text in append 
//    to the passed file (create it if not exist), and notifies the controller.
//    An exception is launched if only a path's directory of the selected File is removed.
    @Override
    public void doAction() {
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(this.filePath, true))) {
            bf.newLine();
            bf.write(textToAppend);
        } catch(FileNotFoundException ex) {
            setChanged();
            notifyObservers(new String[] {"A directory of the file path \"" + filePath + "\" has been deleted!", "Directory removed"});
            return;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
        setChanged();
        notifyObservers(new String[] {"The text \"" + textToAppend + "\" has been append to file: " + filePath});
    }

    @Override
    public String toString() {
        return "Text to append: " + textToAppend +"\nFile: " + filePath;
    }
}
