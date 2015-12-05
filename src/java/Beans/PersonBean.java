/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Marco
 */

import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named; 
   // or import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped; 
import javax.inject.Inject;
import model.Person;
import model.Event;
import service.Service;
import service.ServiceDatabase;

   // or import javax.faces.bean.SessionScoped;

@Named("user") // or @ManagedBean(name="user")
@SessionScoped


public class PersonBean implements Serializable {
   
    private int ID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mail;
    private String password;
    private boolean admin;
    @Inject private Service service;
    private ServiceDatabase dbService;
    
    public PersonBean() {
        this.dbService = ServiceDatabase.getInstance();
    }

    public boolean loggedIn(){
     if(firstName != null){
         return true;
     }
    
    return false;
}
     public PersonBean(int ID, String firstName, String middleName, String lastName, String mail, String password, boolean admin) {
        this.dbService = ServiceDatabase.getInstance();
        this.ID = ID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.admin = admin;
    }
  
     
    public Person getLoggedIN(){
        Person person = new Person(ID, firstName, middleName, lastName, mail, password, admin);
        return person;
    }
    
    
    
    public ArrayList<String> getEventsByID(){
       return dbService.getEventsByUserID(getLoggedIN());
    };
    
    public void login(){
       Person tmp = service.getUserByMail(mail);
       ID = tmp.getID();
       firstName=tmp.getFirstName();
       middleName=tmp.getMiddleName();
       lastName=tmp.getLastName();
       admin=tmp.isAdmin();
      
    }

    public void clearCredentials(){
        ID = 0;
        firstName = null;
        middleName = null;
        lastName = null;
        mail = null;
        password = null;
        admin = false;
        
        
    }
    
    public String getAllSamarits() {
        ArrayList<Person> personList = service.getUserList();
        String JSON = "[ ";        
        for (Person o : personList) {
                   JSON = JSON + " { " + " \"FirstName\": \"" + o.getFirstName() + "\", \"LastName\": \"" + o.getLastName() + "\", \"mail\": \"" + o.getMail() + "\" },";
                }
        
        JSON = JSON.substring(0, JSON.length()-1) + " ]";
        
        System.out.println(JSON);
        return JSON;
    }
    
//    {
//  "one": "Singular sensation",
//  "two": "Beady little eyes",
//  "three": "Little birds pitch by my doorstep"
//}
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }
    
    
}
