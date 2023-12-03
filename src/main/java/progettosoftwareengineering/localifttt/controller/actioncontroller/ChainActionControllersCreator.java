/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.controller.actioncontroller;

//Class that create the Action Controllers chain.
public class ChainActionControllersCreator {
    
    private static BaseActionController first;
    
    private static ChainActionControllersCreator instance = null;
    
//    Constructor that initialize all the Controllers and build the chain.
    private ChainActionControllersCreator() {
        BaseActionController MAC = new MessageActionController();
        BaseActionController AAC = new AudioActionController();
        
        first = MAC;
        MAC.setNext(AAC);
    }
    
//    This method uses the Singleton pattern and also returns the first Controller of the chain. 
    public static BaseActionController chain() {
        if(instance == null)
            instance = new ChainActionControllersCreator();
        return first;
    }
    
}
