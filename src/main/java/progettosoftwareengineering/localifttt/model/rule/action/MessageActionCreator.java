/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule.action;

import java.util.Map;

public class MessageActionCreator extends BaseActionCreator{

    @Override
    public Action createAction(ActionType action, Map actParam) {
        if(action.equals(ActionType.MESSAGE))
            return new MessageAction(actParam.get("messageActionMessage").toString());
        else
            return this.nextCreator(action, actParam);
    }
}
