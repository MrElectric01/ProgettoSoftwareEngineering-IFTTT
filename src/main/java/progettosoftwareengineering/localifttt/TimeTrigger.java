/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettosoftwareengineering.localifttt;

import java.time.LocalTime;

//Trigger for the hour of the day.
public class TimeTrigger implements Trigger {
    
    private LocalTime time;

    public TimeTrigger(String hour, String minutes) {
        time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minutes), 0);
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
