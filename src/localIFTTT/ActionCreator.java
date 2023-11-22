/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package localIFTTT;

import java.util.Map;

public class ActionCreator {
    //Action factory method based on the ActionType. 
    //The argument 'actParam' contains all the possible parameters of a specific Action in a Map.
    static Action createAction(ActionType action, Map actParam) {
        switch(action) {
            default:
                return null;
        }
    };
}
