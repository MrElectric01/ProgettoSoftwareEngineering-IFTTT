/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule.action;

import java.util.Observer;

//Interface of an ActionController
public interface ActionController extends Observer{
//    In this method the passed Action must be observed.
    void observeAction(Action action);
}
