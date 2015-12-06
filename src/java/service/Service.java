package service;

import db.Database;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
//we user javax.enterprise.context.SessionScoped because it's a CDI bean and not a JSF managed bean
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Person;
import model.Event;
import model.Registration;
import org.primefaces.model.ScheduleEvent;

@Named("service")
@SessionScoped
public class Service implements Serializable {
    
    //Database database = new Database();
  
    private ArrayList<Event> eventList;
    private ArrayList<Registration> registrationList;

    public Service() {
        
        this.eventList = new ArrayList<>();
        this.registrationList = new ArrayList<>();
    }

   

    public ArrayList<Person> getUserList() {
        Person p = null;
        Database database = new Database("getUsers", p);
        database.databaseConnection();
        return database.getUserList();
    }

    public void createUser(
            String firstname,
            String middlename,
            String lastname,
            String mail,
            String password,
            boolean admin) {
           
            Person user = new Person(firstname, middlename, lastname, mail, password, admin);
            Database database = new Database("CreateUser", user);
            database.databaseConnection();
    }

    public void createRegistration(int personID, String eventID) {
        Registration registration = new Registration(personID, eventID);
        registrationList.add(registration);
        int ID = registrationList.size() + 1;

    }

    //--WorkAround-- Cannot run methods in Datatable / column in person.xhtml
    public void personStat() {
        ArrayList<Person> list = getUserList();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).postStat();
        }
    }

    //Using ArrayList instead of DB, Refactor to DB!! 
    public Person getUserByMail(String mail) {
        //Create empty person object
        Person target = new Person();
        ArrayList<Person> list = getUserList();
        //Find user with matching email
        for (int i = 0; i < list.size(); i++) {
            if (mail.equals(list.get(i).getMail())) {
                target = list.get(i);
            }
        }
        
        //Return populated Person object !! evt TODO catching
        return target;
    }

    public Person getUserByID(int ID) {
        //Create empty person object, to avoid return null
        ArrayList<Person> list = getUserList();
        Person target = new Person();
        //Find user with matching email
        for (int i = 0; i < list.size(); i++) {
            if (ID == list.get(i).getID()) {
                target = list.get(i);
            }
        }
        //Return populated Person object !! evt TODO catching
        return target;
    }

    public Event getEventByID(String ID) {
        //Create empty event object to avoid returning null
        Event target = new Event();
        //Find user with matching email
        for (int i = 0; i < eventList.size(); i++) {
            if (ID == eventList.get(i).getID()) {
                target = eventList.get(i);
            }
        }
        //Return populated Person object !! evt TODO catching
        return target;
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public ArrayList<Registration> getRegistrationList() {
        return registrationList;
    }

    public boolean loginAuthenticator(String eMail, String password) {

        int userId = getUserByMail(eMail).getID();
        ArrayList<Person> list = getUserList();
        Person user;
       
        for (int i = 0; i < list.size(); i++) {
            user = list.get(i);
             System.out.print(user.getMail());
             System.out.print(user.getPassword());
            if (userId == user.getID() && password.equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Event> getEventInformation(Person person) {
        ArrayList<Event> event = new ArrayList<>();
        
        Database database = new Database("getEventObjectsbyPerson", person);
        database.databaseConnection();
        event = database.getEventListObjects();
        for (Event e : event) {
            Database databases = new Database("",person);
        }
        return event;
    }

}
