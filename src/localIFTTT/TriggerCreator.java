/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package localIFTTT;

import java.util.Map;

/**
 *
 * @author giosu
 */
public class TriggerCreator {
    //Trigger factory method based on the TriggerType. 
    //The argument 'trigParam' contains all the possible parameters of a specific Trigger in a Map.
    static Trigger createTrigger(TriggerType trigger, Map trigParam){
        switch(trigger) {
            default:
                return null;
        }
    };
}
