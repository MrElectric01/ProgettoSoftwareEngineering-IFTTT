/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule.action;

import java.io.Serializable;
import java.util.Observable;

public abstract class Action extends Observable implements Serializable {
    public abstract void doAction();
}
