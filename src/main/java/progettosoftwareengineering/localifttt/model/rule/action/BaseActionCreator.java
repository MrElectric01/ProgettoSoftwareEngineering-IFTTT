package progettosoftwareengineering.localifttt.model.rule.action;

import java.util.Map;

//Abstract class for the base behaviour of an ActionCreator in the Chain.
public abstract class BaseActionCreator {
//    Reference to the next Creator in the chain, initially null.
    private BaseActionCreator next = null;

//    Method that set the "next" reference.
    protected void setNext(BaseActionCreator next) {
        this.next=next;
    } 

//    Abstract Method that all the Creators that extends this class have to Override
//    in order to handle their specific Action creation.
    public abstract Action createAction(ActionType action, Map<String,String> actParam);
    
//    If the Creator can't handle the request, this method will be
//    called to pass it to the next in the chain, if exist.
    protected Action nextCreator(ActionType action, Map<String,String> actParam) {
        if(next != null)
            return next.createAction(action, actParam);
        else
            return null;
    }
}

