package progettosoftwareengineering.localifttt.controller.actioncontroller;

import progettosoftwareengineering.localifttt.model.rule.action.*;

//Abstract class for the base behaviour of an ActionController in the Chain.
public abstract class BaseActionController implements ActionController {
//    Reference to the next Controller in the chain, initially null.
    private BaseActionController next = null;
    
//    Method that set the "next" reference.
    protected void setNext(BaseActionController next) {
        this.next=next;
    }
    
//    Abstract Method that all the Controllers that extends this class have to Override
//    in order to handle their specific Action observation.
    @Override
    public abstract void observeAction(Action action);
    
//    If the Controller can't handle the request, this method will be
//    called to pass it to the next in the chain, if exist.
    protected void nextController(Action action) {
        if(next != null) 
            next.observeAction(action);
    }
}
