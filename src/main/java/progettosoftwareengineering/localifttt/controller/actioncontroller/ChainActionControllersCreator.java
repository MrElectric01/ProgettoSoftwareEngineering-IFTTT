package progettosoftwareengineering.localifttt.controller.actioncontroller;

//Class that create the Action Controllers chain.
public class ChainActionControllersCreator {
    
    private static BaseActionController first;
    
    private static ChainActionControllersCreator instance = null;
    
//    Constructor that initialize all the Controllers and build the chain.
    private ChainActionControllersCreator() {
        BaseActionController MAC = new MessageActionController();
        BaseActionController AAC = new AudioActionController();
        BaseActionController NAEC = new NotifyActionExecutionController();
        
        first = MAC;
        MAC.setNext(AAC);
        AAC.setNext(NAEC);
    }
    
//    This method uses the Singleton pattern and also returns the first Controller of the chain. 
    public static BaseActionController chain() {
        if(instance == null)
            instance = new ChainActionControllersCreator();
        return first;
    }
    
}
