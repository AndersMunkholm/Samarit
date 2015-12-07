/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import model.Event;
import model.EventFrame;
import model.Person;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.primefaces.model.ScheduleEvent;
import service.Service;
import service.ServiceDatabase;

/**
 *
 * @author Anders
 */
public class ModelServiceDatabaseTest {
    
    
    public ModelServiceDatabaseTest() {
    }
    
    
    

    @Test
    public void EventCreationNotEmpty() {
        ServiceDatabase service = ServiceDatabase.getInstance();
        ArrayList<ScheduleEvent> event = service.initiateEvent();
        assertTrue(event.size() > 0);
        for (int i = 0; i < event.size(); i++) {
            assertTrue(!event.get(1).getId().isEmpty());
            assertTrue(!event.get(1).getData().toString().isEmpty());
            assertTrue(!event.get(1).getStartDate().toString().isEmpty());
            assertTrue(!event.get(1).getEndDate().toString().isEmpty());
        }
        
        
    }
    
    
    @Test
    public void getEventsByUserId() {
        Person person = new Person();
        ServiceDatabase service = ServiceDatabase.getInstance();
        ArrayList<String> e = service.getEventsByUserID(person);
        assertTrue(!e.isEmpty());
        System.out.println(e);
    
    
    
    
    }
    
    @Test
    public void testIsRegistred() {
        Person person = new Person();
        ServiceDatabase service = ServiceDatabase.getInstance();
        assertTrue(service.isRegistred(null, null, person));
        
    
    
    
    }
    
    
    @Test
    public void testGetEventById() {
        Service service = new Service();
        String eventId = "";
        Event e = service.getEventByID(eventId);
        assertTrue(e.getID().equals(eventId));
        assertTrue(!e.getName().isEmpty());
        
        
        
    }
    
    @Test
    public void testService() {
         Service service = new Service();
         ArrayList<Person> e = service.getUserList();
         assertTrue(e.size() > 0);
         for (int i = 0; i < e.size(); i++) {
            assertTrue(!e.get(0).getFirstName().isEmpty());
            assertTrue(!e.get(0).getMiddleName().isEmpty());
            assertTrue(!e.get(0).getLastName().isEmpty());
            assertTrue(e.get(0).getID() > 0);
            assertTrue(!e.get(0).getPassword().isEmpty());
         }
    }
  
}
