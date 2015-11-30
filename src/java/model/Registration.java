/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.inject.Inject;
import service.Service;

/**
 *
 * @author Morten
 */
public class Registration implements Serializable {
    @Inject private Service service;
    @Inject private Event event;
    //Properties
    private int registrationID;
    private int personID;
    private String eventID;
    private LocalDate registrationDate;
    private boolean confirmed = false;

    public Registration(int personID, String eventID) {
        this.personID = personID;
        this.eventID = eventID;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public int getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(int registrationID) {
        this.registrationID = registrationID;
    }

   
    
/**    public boolean validateRegistration (int ID){
       //Event e = new Event();
       
        if (service.getEventByID(eventID) != null) {
           //e = service.getEventByID(eventID);
           return true;
       }
        return false;
    }
   **/
     
    
    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
