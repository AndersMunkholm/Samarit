/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Date;
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
            assertTrue(!event.get(1).getTitle().toString().isEmpty());
            assertTrue(!event.get(1).getStartDate().toString().isEmpty());
            assertTrue(!event.get(1).getEndDate().toString().isEmpty());
        }
        
        
    }
    
    
    @Test
    public void getEventsByUserId() {
        Person person = new Person(1, "", "", "", 
            "", "",true);
        
        ServiceDatabase service = ServiceDatabase.getInstance();
        ArrayList<String> e = service.getEventsByUserID(person);
        for (String f : e) {
            assertTrue(!f.isEmpty());
        }
    }
   
    
    
    
    
    
    @Test
    public void testGetUserList() {
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
    
    @Test 
    public void testCreateUser() {
        Service service = new Service();
        Person person = service.getUserByMail("test@mail.dk");
        assertTrue(person != null);
        assertTrue(person.getFirstName().equals("Rasmus"));
        assertTrue(person.getMiddleName().equals("Gert"));
        assertTrue(person.getLastName().equals("christensen"));
        assertTrue(person.getMail().equals("test@mail.dk"));
        assertTrue(person.getPassword().equals("password"));
        assertTrue(person.getID() == 1);
        
    
    }
    
    
    @Test
    public void testGetUserById() {
        Service service = new Service();
        Person person = service.getUserByID(1);
        assertTrue(person != null);
        assertTrue(person.getFirstName().equals("Rasmus"));
        assertTrue(person.getMiddleName().equals("Gert"));
        assertTrue(person.getLastName().equals("christensen"));
        assertTrue(person.getMail().equals("test@mail.dk"));
        assertTrue(person.getPassword().equals("password"));
        assertTrue(person.getID() == 1);
        
        
    
    
    }
    
    //Bruger ikke denne metode mere, bruger de andre metoder til at hente Event's
//    @Test
//    public void getEventByID() {
//        Service service = new Service();
//        Event event = service.getEventByID("f55eeb96-151e-4782-8d57-92072d1782fd");
//        assertTrue(event != null);
//        assertTrue(event.getName().equals("doom"));
//        assertTrue(event.getStartDate().equals("Tue Dec 08 23:00:00 CET 2015"));
//        assertTrue(event.getEndDate().equals("Tue Dec 08 23:00:00 CET 2015"));
//        assertTrue(event.getID().equals("f55eeb96-151e-4782-8d57-92072d1782fd"));
//    
//    
//    
//    }
    
    @Test
    public void testLoginAuthenticator() {
        Service service = new Service();
        boolean e = service.loginAuthenticator("test@mail.dk", "password");
        assertTrue(e);
        
    
    
    
    }
    
    
    @Test
    public void TestgetEventInformation() {
        Service service = new Service();
        Person person = service.getUserByID(1);
        ArrayList<Event> event = service.getEventInformation(person);
        ArrayList<EventFrame> j = null;
        for (int i = 0; i < event.size(); i++) {
            
            assertTrue(event != null);
            j = event.get(i).getEventFrames();
            assertTrue(event.get(i).getName().equals("doom") || event.get(i).getName().equals("ABC jule party"));
            assertTrue(event.get(i).getStartDate().toString().equals("Tue Dec 08 23:00:00 CET 2015") || event.get(i).getStartDate().toString().equals("Tue Dec 15 23:00:00 CET 2015"));
            assertTrue(event.get(i).getEndDate().toString().equals("Tue Dec 08 23:00:00 CET 2015") || event.get(i).getEndDate().toString().equals("Sun Dec 27 23:00:00 CET 2015"));
            
            
            
            for (int k = 0; k < j.size(); k++) {
                assertTrue(!j.get(k).getEndTime().toString().isEmpty());
                assertTrue(!j.get(k).getStartDate().toString().isEmpty());
                assertTrue(!j.get(k).getStartTime().toString().isEmpty());
                assertTrue(j.get(k).getEventId().equals(event.get(i).getID()));
            }
        }
    
    
    }
    
    
  
}
