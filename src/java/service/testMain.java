
package model;

import service.Service;

public class testMain {

    public static void main(String[] args) {
        Service service = new Service();
        
        service.createUser("Rasmus", "", "Christensen", "Rasmus@email.dk","abcde", false);
        
        System.out.println(service.getUserList().size());
        System.out.println("getUserByEmail " + service.getUserByMail("Marco@email.dk").getFirstName() + " " + service.getUserByMail("Marco@email.dk").getID());
        System.out.println("getUserByEmail " + service.getUserByMail("Morten@email.dk").getFirstName() + " " + service.getUserByMail("Morten@email.dk").getID());
        System.out.println("getUserByEmail " + service.getUserByMail("Rasmus@email.dk").getFirstName() + " " + service.getUserByMail("Rasmus@email.dk").getID());
        System.out.println(service.loginAuthenticator("Marco@email.dk", "abcd"));
        System.out.println(service.loginAuthenticator("Mrco@email.dk", "abcd"));
        System.out.println(service.getRegistrationList().get(0).getEventID());
    
    }    
    
}