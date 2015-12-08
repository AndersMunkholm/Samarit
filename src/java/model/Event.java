package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
    private Date startDate;
    private Date endDate;
    private ArrayList<Registration> registrations = new ArrayList<Registration>();
    private ArrayList<EventFrame> eventFrames;
    @Inject
    private Service service;

    public Event() {
        this.eventFrames = new ArrayList<>();
    }

    public Event(String ID, String name, Date startDate, Date endDate) {
        this.ID = ID;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventFrames = new ArrayList<>();
    }

    //**   Dette er ikke en comment men javadoc, Du skylder nu øl
     //* public boolean isEvent(int ID){ for(int i = 0;
     //* i<service.getEventList().size(); i++){
     //* if(service.getEventList().get(i).getID() == ID ){ return true; } }
//     *
//     * return false; };
//     *
//     *
//     */
    
    
    public void createEventFrame(LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        this.eventFrames.add(new EventFrame(startDate, startTime, endTime, this));
    
    }
    
    public EventFrame getEventFrame(int index) {
        return this.eventFrames.get(index);
    }
    
    public void removeEventFrame(int index) {
        this.eventFrames.remove(index);
    
    }
    
    public ArrayList<EventFrame> getEventFrames() {
        return this.eventFrames;
    }
    
    public void addEventFrame(EventFrame eventFrame) {
        this.eventFrames.add(eventFrame);
    
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String JSON = "";
        String JSONFRAMES = "[ ";
        for (EventFrame f : this.eventFrames) {
            JSONFRAMES = JSONFRAMES + f.toString() + ",";
        }
        
        JSONFRAMES = JSONFRAMES.substring(0, JSONFRAMES.length()-1) + " ]";
        
        JSON =  " { " + " \"Name\": \"" + this.name+ "\", \"StartDate\": \"" 
                + this.startDate + "\", \"EndDate\": \"" 
                + this.endDate + "\", \"EventFrames\": " 
                + JSONFRAMES + " }";
        
        
        return JSON;
    }
    
}
