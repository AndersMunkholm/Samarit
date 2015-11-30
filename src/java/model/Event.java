
package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.inject.Inject;
import model.Registration;
import service.Service;

/**
 *
 * @author Morten
 */
public class Event {
    //Properties
    private String ID;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Registration> registrations = new ArrayList<Registration>();
    private ArrayList<EventFrame> eventFrames = new ArrayList<>();
    
    @Inject private Service service;
    
    public Event()
    {
  
    }

    public Event(String ID, String name, LocalDate startDate, LocalDate endDate) {
        this.ID = ID;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
 /**   
  public boolean isEvent(int ID){
        for(int i = 0; i<service.getEventList().size(); i++){
            if(service.getEventList().get(i).getID() == ID ){
                return true;
            }
        }
        
      return false;  
    };

  **/
    
    /**
     * 
     * @param startDate
     * @param endDate
     * @param startTime
     * @param endTime 
     * 
     * creates and adds an eventFrame to the event, This is a composition
     */
  
    public void createEventFrame(LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        this.eventFrames.add(new EventFrame(startDate, startTime, endTime, this));
        
    
    }
    
    
    public EventFrame getEventFrame(int index) {
        return this.eventFrames.get(index);
    
    }
    
    
    public void addEventFrame(EventFrame eventFrame) {
        this.eventFrames.add(eventFrame);
    
    }
    
    public void removeEventFrame(EventFrame eventFrame) {
        this.eventFrames.remove(eventFrame);
    }
  
  
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    
}
