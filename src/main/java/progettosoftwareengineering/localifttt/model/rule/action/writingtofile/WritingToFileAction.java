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

//      Execute a WritingToFileAction writing the passed text in append 
//      to the passed file (create it if not exist), and notify the controller.
    @Override
    public void doAction() {
       try(BufferedWriter bf = new BufferedWriter(new FileWriter(this.filePath,true))){
            bf.write(textToAppend);
            bf.newLine();
       }catch(FileNotFoundException ex){
        //if the file doesn't exist this method will create it.
       }
       catch(IOException ex){
        ex.printStackTrace();
       }
       
       setChanged();
       notifyObservers("The text \"" + textToAppend + "\" has been append to file: " + filePath);
    }

    @Override
    public String toString() {
        return "Text to append: " + textToAppend +"\nFile: " + filePath;
    }

}
