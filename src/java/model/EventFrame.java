/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Anders
 */
public class EventFrame {

    private LocalDate startDate;
    
    private LocalTime startTime;
    private LocalTime endTime;
    private int eventFrameId = 0;
    private Event event = null;
    private ArrayList<Person> persons = new ArrayList<>(); 

    /**
     * 
     * @param startDate LocalDate
     * @param startTime LocalTime
     * @param endTime LocalTime
     * @param event Event
     */
    
    public EventFrame(LocalDate startDate, LocalTime startTime, LocalTime endTime,Event event) {
        this.startDate = startDate;
        this.event = event;
        this.startTime = startTime;
        this.endTime = endTime;
       
    }
    
    public EventFrame(LocalDate startDate, LocalTime startTime, LocalTime endTime,Event event, int eventFrameId) {
        this.startDate = startDate;
        this.event = event;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventFrameId = eventFrameId;

    }
    
    
    
    

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getEventFrameId() {
        return eventFrameId;
    }

    public void setEventFrameId(int eventFrameId) {
        this.eventFrameId = eventFrameId;
    }

    public Person getPersons(int index) {
        return persons.get(index);
    }

    public void addPerson(Person person) {
        this.persons.add(person);
        
    }
    
    public void removePerson(Person person) {
        this.persons.remove(person);
    
    }
    
    public String getEventId() {
        return event.getID();
    
    }
    
    @Override
    public String toString() {
        String JSON = "";
        
        JSON =  " { " + " \"StartDate\": \"" + this.startDate + "\", \"StartTime\": \"" + this.startTime + "\", \"endTime\": \"" + this.endTime + "\" }";
        
        
        
        return JSON;
    }

    public void setEvent(Event e) {
        this.event = e;
    }

}
