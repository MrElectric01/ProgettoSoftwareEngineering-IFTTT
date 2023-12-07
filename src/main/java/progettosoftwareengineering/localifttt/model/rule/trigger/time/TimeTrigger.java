/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt.model.rule.trigger.time;

import java.time.LocalTime;

import progettosoftwareengineering.localifttt.model.rule.trigger.Trigger;

//Trigger for the hour of the day.
public class TimeTrigger extends Trigger {
    
    private LocalTime time;

    public TimeTrigger(String hours, String minutes) {
        time = LocalTime.of(Integer.parseInt(hours), Integer.parseInt(minutes), 0);
    }

//    Triggers the Trigger when the time within the minute of the specified time.
    @Override
    public boolean checkTrigger() {
        return (LocalTime.now().isAfter(time) && LocalTime.now().isBefore(time.plusMinutes(1))) ;
    }

    @Override
    public String toString() {
        return "Time: " + time;
    }
    
}
