/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.rule.action;

import java.util.Map;

public class AudioActionCreator extends BaseActionCreator{

    @Override
    public Action createAction(ActionType action, Map actParam) {
        if(action.equals(ActionType.AUDIO))
            return new AudioAction(actParam.get("audioActionAudioPath").toString());
        else
            return this.nextCreator(action, actParam);
    }
    
}
