package progettosoftwareengineering.localifttt.model.rule.action;

import progettosoftwareengineering.localifttt.model.rule.action.audio.AudioActionCreator;
import progettosoftwareengineering.localifttt.model.rule.action.copyfile.CopyFileActionCreator;
import progettosoftwareengineering.localifttt.model.rule.action.message.MessageActionCreator;
import progettosoftwareengineering.localifttt.model.rule.action.movefile.MoveFileActionCreator;
import progettosoftwareengineering.localifttt.model.rule.action.writingtofile.WritingToFileActionCreator;

//Class that create the Action Creators chain.
public class ChainActionCreatorsCreator {
//    Reference to the first creator of the chain.
    private static BaseActionCreator first;
    
    private static ChainActionCreatorsCreator instance = null;
    
//    Constructor that initialize all the Creators and build the chain.
    private ChainActionCreatorsCreator() {
        BaseActionCreator MAC = new MessageActionCreator();
        BaseActionCreator AAC = new AudioActionCreator();
        BaseActionCreator WTFAC = new WritingToFileActionCreator();
        BaseActionCreator MFAC = new MoveFileActionCreator();
        BaseActionCreator CFAC = new CopyFileActionCreator();
        
        first = MAC;
        MAC.setNext(AAC);
        AAC.setNext(WTFAC);
        WTFAC.setNext(MFAC);
        MFAC.setNext(CFAC);
    }
    
//    This method uses the Singleton pattern and also returns the first Creator of the chain. 
    public static BaseActionCreator chain() {
        if(instance == null)
            instance = new ChainActionCreatorsCreator();
        return first;
    }
}
