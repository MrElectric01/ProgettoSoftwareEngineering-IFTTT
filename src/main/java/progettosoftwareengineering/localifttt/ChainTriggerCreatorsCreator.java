/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt;

//Class that create the Trigger Creators chain.
public class ChainTriggerCreatorsCreator {
//    Reference to the first creator of the chain.
    private static BaseTriggerCreator first;
    
    private static ChainTriggerCreatorsCreator instance = null;
    
//    Constructor that initialize all the Creators and build the chain.
    private ChainTriggerCreatorsCreator() {
        first = null;
//        TODO
    }
    
//    This method uses the Singleton pattern and also returns the first Creator of the chain. 
    public static BaseTriggerCreator chain() {
        if(instance == null)
            instance = new ChainTriggerCreatorsCreator();
        return first;
    }
}