package progettosoftwareengineering.localifttt.model.rule.action;

import java.util.Observer;

//Interface of an ActionController.
public interface ActionController extends Observer{
//    In this method the passed Action must be observed.
    void observeAction(Action action);
}
