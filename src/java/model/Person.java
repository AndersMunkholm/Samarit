/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Person{

    //Properties
    private int ID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mail;
    private String password;
    //Instead of deleting a person, person is set as not active
    private boolean isAdmin;
    private double postsTaken;
    private double postsOffered;
    private double postStat;
    
    public Person() {}
    
    public Person(String firstName, String middleName, String lastName, 
            String mail, String password,boolean isAdmin) {
      
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    public Person(int id, String firstName, String middleName, String lastName, 
            String mail, String password,boolean isAdmin) {
      
        this.ID = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    
    public void postStat(){
       
        double percentage = 0;
        if(postsTaken != 0 && postsOffered != 0){
        percentage = (postsTaken / postsOffered)*100;
        System.out.println(2/3);
        postStat = percentage;
    }
    }

    public double getPostStat() {
        return postStat;
    }

    public void setPostStat(double postStat) {
        this.postStat = postStat;
    }

    public double getPostsTaken() {
        return postsTaken;
    }

    public void setPostsTaken(double postsTaken) {
        this.postsTaken = postsTaken;
    }

    public double getPostsOffered() {
        return postsOffered;
    }

    public void setPostsOffered(double postsOffered) {
        this.postsOffered = postsOffered;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
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

    public boolean isAdmin() {
        return isAdmin;
    }
    
    public void setAdmin(boolean admin){
        this.isAdmin = admin;
    }
    
    
}
