/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule.action;

//Class that create the Action Creators chain.
public class ChainActionCreatorsCreator {
//    Reference to the first creator of the chain.
    private static BaseActionCreator first;
    
    private static ChainActionCreatorsCreator instance = null;
    
//    Constructor that initialize all the Creators and build the chain.
    private ChainActionCreatorsCreator() {
        BaseActionCreator MAC = new MessageActionCreator();
        BaseActionCreator AAC = new AudioActionCreator();
        
        first = MAC;
        MAC.setNext(AAC);
    }
    
//    This method uses the Singleton pattern and also returns the first Creator of the chain. 
    public static BaseActionCreator chain() {
        if(instance == null)
            instance = new ChainActionCreatorsCreator();
        return first;
    }
}
