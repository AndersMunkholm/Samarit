/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Event;
import model.EventFrame;
import model.Person;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author mooncript
 */
public class Database {

    private Statement stmt = null;
    private ResultSet res = null;
    private ArrayList<Person> list;
    private ArrayList<String> eventList;
    private Connection minConnection;
    private String type;
    private ScheduleEvent event;
    private Person person;
    private Boolean isRegistred = null;
    private EventFrame eventFrame;
    private ArrayList<EventFrame> eventFrameList;
    private Event eventModel;
    
    public Database(String type, ScheduleEvent event, Person person) {
        this.type = type;
        this.event = event;
        this.person = person;

    }
    
    public Database(String type, Event event) {
        this.eventModel = event;
        this.type = type;
    
    }
    
    
    public Database(String type, EventFrame eventFrame) {
        this.type = type;
        this.eventFrame = eventFrame;
        this.eventFrameList = new ArrayList<>();
    }

    public Boolean getIsRegistred() {
        return isRegistred;
    }

    public Database(String type, ScheduleEvent event) {
        this.type = type;
        this.event = event;
        this.eventList = new ArrayList<>();
    }

    public Database(String type, Person person) {
        this.type = type;
        this.person = person;
        this.eventList = new ArrayList<>();
    }

    public Database(String type) {
        this.type = type;
    }

    public void databaseConnection() {

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://KAIBYS\\SQLEXPRESS;databaseName=Samarit;user=sa;password=Andersti29;");
            stmt = minConnection.createStatement();
            if (type.equals("CreateEvent")) {
                opretEvent();
            }

            if (type.equals("UpdateEvent")) {
                updateEvent();
            }

            if (type.equals("DeleteEvent")) {
                deleteEvent();
            }
            if (type.equals("CreateUser")) {
                createPerson();
            }

            if (type.equals("UpdateUser")) {
                updatePerson();
            }

            if (type.equals("DeleteUser")) {
                deletePerson();
            }

            if (type.equals("getUsers")) {
                getUsers();
            }

            if (type.equals("CreateEventRegistration")) {
                createEventRegistration(event, person);
            }

            if (type.equals("DeleteEventRegistration")) {
                deleteEventRegistration(event, person);
            }

            if (type.equals("IsRegistered")) {
                isRegisteredToEvent(event, person);
            }

            if (type.equals("GetEventsByID")) {
           
                getEventsByID(person);
              
            }
            if (type.equals("CreateEventFrame")) {
           
                createEventFrame(this.eventFrame);
              
            }
            if (type.equals("DeleteEventFrame")) {
           
                deleteEventFrame(this.eventFrame);
              
            }
            if (type.equals("GetEventFramesEventId")) {
           
                getEventFramesEventId(this.eventModel);
              
            }

        } catch (Exception e) {
            System.out.println("fejl:  type: " + type + " Message: " + e.getMessage());
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (minConnection != null) {
                    minConnection.close();
                }
            } catch (SQLException e) {
                //HVEM SKAL GIVE ØL?! HVEM HAR FJERNET DETTE FRA EN CATCH ?!
            }
        }

    }

    public void opretEvent() throws SQLException {
        res = stmt.executeQuery("Execute OpretEvent '"
                + this.event.getId() + "', '" + this.event.getTitle() + "', '"
                + this.event.getStartDate() + "', '" + this.event.getEndDate() + "';");
    }

    public void updateEvent() throws SQLException {
        res = stmt.executeQuery("Execute UpdateEvent '"
                + this.event.getId() + "', '" + this.event.getTitle() + "', '"
                + this.event.getStartDate() + "', '" + this.event.getEndDate() + "';");

    }

    ;
    
    public synchronized ArrayList initiateEvent() {
        //Dette burde også ligge inde i en metode//eller laves om til sin egen klasse, bestemmer man selv
        ArrayList<ScheduleEvent> list = new ArrayList<>();
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            minConnection = DriverManager
                    .getConnection("jdbc:sqlserver://KAIBYS\\SQLEXPRESS;databaseName=Samarit;user=sa;password=Andersti29;");
            stmt = minConnection.createStatement();
            res = stmt.executeQuery("select * from SamaritEvent;");
            while (res.next()) {
                ScheduleEvent event;

                DateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
                Date beginDate = format.parse(res.getString(3));
                Date endDate = format.parse(res.getString(4));

                event = new DefaultScheduleEvent(res.getString(2), beginDate, endDate);
                event.setId(res.getString(1));
                list.add(event);
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (minConnection != null) {
                    minConnection.close();
                }
            } catch (SQLException e) {

            }
        }
        return list;
    }

    private void deleteEvent() throws SQLException {
        res = stmt.executeQuery("Execute deleteEvent '" + this.event.getId() + "';");
    }

    private void createPerson() throws SQLException {
        res = stmt.executeQuery("Execute createPerson '"
                + this.person.getFirstName() + "', '" + this.person.getMiddleName() + "', '"
                + this.person.getLastName() + "', '" + this.person.getMail() + "', '" + this.person.getPassword()
                + "', '" + this.person.isAdmin() + "';");
    }

    private void updatePerson() throws SQLException {
        res = stmt.executeQuery("Execute updatePerson '"
                + this.person.getID() + "', '" + this.person.getFirstName()
                + "', '" + this.person.getMiddleName() + "', '"
                + this.person.getLastName() + "', '" + this.person.getMail() + "', '" + this.person.getPassword()
                + "', '" + this.person.isAdmin() + "';");
    }

    private void deletePerson() throws SQLException {
        res = stmt.executeQuery("Execute deletePerson '" + this.person.getID() + "';");
    }

    private void getUsers() throws SQLException {
        list = new ArrayList<>();
        res = stmt.executeQuery("select * from Person;");
        while (res.next()) {
            Person p = new Person(res.getInt("personId"), res.getString("firstName"),
                    res.getString("middleName"), res.getString("lastName"),
                    res.getString("mail"), res.getString("userPassword"), res.getBoolean("isAdmin"));
            list.add(p);
        }
    }

    public ArrayList<Person> getUserList() {
        return list;
    }

    public void createEventRegistration(ScheduleEvent event, Person person) throws SQLException {
        res = stmt.executeQuery("Execute CreateEventRegistration '" + person.getID() + "', '" + event.getId() + "';");
    }

    public void deleteEventRegistration(ScheduleEvent event, Person person) throws SQLException {
        res = stmt.executeQuery("Execute DeleteEventRegistration '" + person.getID() + "', '" + event.getId() + "';");
    }

    public void isRegisteredToEvent(ScheduleEvent event, Person person) throws SQLException {
        res = stmt.executeQuery("Execute IsRegistered '" + person.getID() + "', '" + event.getId() + "';");

        res.next();
        int temp = res.getInt(1);
        System.out.print(temp);

        if (temp > 0) {
            isRegistred = true;
        } else {
            isRegistred = false;
        }
    }

    public void getEventsByID(Person person) throws SQLException, ParseException {
        res = stmt.executeQuery("Execute eventsByPerson '" + person.getID() + "';" );
        while (res.next()) {         
            String tempEvent = "Titel: " + res.getString(2) + "\n";
            eventList.add(tempEvent);
        }
    }

    public ArrayList<String> getEventList() {
        return eventList;
    }

    
    public void createEventFrame(EventFrame eventFrame) throws SQLException {
        res = stmt.executeQuery("Execute createEventFrame '" + eventFrame.getStartDate().toString() + "', '" + eventFrame.getStartTime().toString() + "', '" + eventFrame.getEndTime().toString() + "', '" + eventFrame.getEventId() + "';");
        
    
    }
    
    public void deleteEventFrame(EventFrame eventFrame) throws SQLException {
        res = stmt.executeQuery("Execute deleteEventFrame '" + eventFrame.getEventFrameId() + "';");
    
    }
    
    public void getEventFramesEventId(Event event) throws SQLException {
        res = stmt.executeQuery("Execute getEventFrameEvent '" + eventFrame.getEventId() + "';");
        while (res.next()) {
            this.eventFrameList.add(new EventFrame(
                    LocalDate.parse(res.getString("startDate"))
                    ,LocalTime.parse(res.getString("startTime"))
                    ,LocalTime.parse(res.getString("endTime"))
                    ,event,
                    Integer.parseInt(res.getString("eventFrameId"))));
            
        }
    
    }
    
    
    public ArrayList<EventFrame> getFrameList() {
    
        return this.eventFrameList;
    }

    
    
   
    
}
