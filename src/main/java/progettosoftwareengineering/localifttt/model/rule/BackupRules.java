/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule;

import java.io.*;
import java.util.*;

public class BackupRules implements Runnable, Observer {
    
    private static String backupFile = "BackupRules.bin";
    private static BackupRules instance = null;
    
//    Attribute useful for the testing.
    private Boolean execute = false;
    
        private BackupRules() {
    }

    public static BackupRules getInstance() {
        if (instance == null) 
            instance = new BackupRules();
        return instance;
    }
    
//    This method is syncronized on the RuleCollection because there are other Threads
//    that can modify that Collection. In the synchronized part the Collection is saved in a binary file.
    @Override
    public void run() {
        execute = true;
        synchronized(RuleCollection.getInstance().getRules()) {
            try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(backupFile)))) {
                oos.writeObject(RuleCollection.getInstance().getRules());
            }   catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

//    Every time the RuleCollection is modified, this method lunches a new Thread
//    that execute this Runnable class.
    @Override
    public void update(Observable o, Object arg) {
        Thread backup = new Thread(instance);
        backup.start();
    }
    
//    Useful method for changing the file in tests.
    void setBackupFile(String backupFile) {
        this.backupFile = backupFile;
    }
    
//    Useful method to check the run method execution in tests.
    Boolean getExecute() {
        return execute;
    }
    
//    Static method for the Reloading of the rule collection.
    public static List<Rule> reloadBackup() {
        List<Rule> backup = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(backupFile)))) {
            backup = (ArrayList<Rule>) ois.readObject();
        } catch (FileNotFoundException ex) {
//            In this case we do nothing, because if there isn't a BackupFile the backup Collection must be empty.
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return backup;
    }
}
