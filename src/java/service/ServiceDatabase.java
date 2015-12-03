/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.Database;
import java.util.ArrayList;
import model.Person;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author mooncript
 */
public class ServiceDatabase {

    
    private static ServiceDatabase instance = null;
    
    private ServiceDatabase() {

    }

    public static ServiceDatabase getInstance() {
        if (instance == null) {
            instance = new ServiceDatabase();
        }
        return instance;
    }
//*
    public void databaseConnection(String type,  ScheduleEvent event) {
        Database database = new Database(type, event);
        database.databaseConnection();
    }
    
    public void databaseConnection(String type,  ScheduleEvent event, Person person) {
        Database database = new Database(type, event, person);
        database.databaseConnection();
    }
    
    public boolean isRegistred(String type,  ScheduleEvent event, Person person){
        Database database = new Database(type, event, person);
        database.databaseConnection();
       
        return database.getIsRegistred();
    }
    
    
    public ArrayList initiateEvent(){
        
        Database database = new Database("initiate");
        //skal måske laves smart?  behøves ikke hvis man er doven
        return database.initiateEvent();
    }
    
     public ArrayList<String> getEventsByUserID(Person person) {
        
        Database database = new Database("GetEventsByID", person);
        database.databaseConnection();
        return database.getEventList();

                
    }

}
