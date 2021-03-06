package Beans;

import service.ServiceDatabase;
import java.io.Serializable;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;
import model.Person;

@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {

    private ScheduleModel eventModel;
    @Inject
    private ServiceDatabase dbService;
    private boolean isRegistered;
    private String registerString;
    private ScheduleEvent event = new DefaultScheduleEvent();

    public ScheduleView() {
        this.dbService = ServiceDatabase.getInstance();
    }

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        initiateEvent();
        event = new DefaultScheduleEvent();
    }

    public String getRegisterString() {
        return registerString;
    }

    public void setRegisterString(String registerString) {
        this.registerString = registerString;
    }

    public boolean isIsRegistered() {
        System.out.print(isRegistered);
        return isRegistered;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        if (event.getId() == null) {
            eventModel.addEvent(event);
            dbService.databaseConnection("CreateEvent", event);

        } else {
            eventModel.updateEvent(event);
            dbService.databaseConnection("UpdateEvent", event);
        }

        event = new DefaultScheduleEvent();
    }

    public void deleteEvent(ActionEvent actionEvent) {
        eventModel.deleteEvent(event);
        dbService.databaseConnection("DeleteEvent", event);
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        getRegistrationText();
        getRegistrationName();  
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void createEventRegistration(Person person) {
        dbService.databaseConnection("CreateEventRegistration", event, person);
        event = new DefaultScheduleEvent();
    }

    public void deleteEventRegistration(Person person) {
        dbService.databaseConnection("DeleteEventRegistration", event, person);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void initiateEvent() {
        ArrayList<ScheduleEvent> list = dbService.initiateEvent();

        for (ScheduleEvent e : list) {
            String id = e.getId();
            eventModel.addEvent(e);
            e.setId(id);
        }
    }

    public boolean isRegistered(Person person) {
        if (!dbService.isRegistred("IsRegistered", event, person)) {
            isRegistered = true;
            createEventRegistration(person);
           
            return true;
        } else {
             isRegistered = false;
            deleteEventRegistration(person);
            return false;
        }
    }
    
    public String getRegistrationName(){
        if(!isRegistered)
            return "Registered to event";
                    else
            return "Deleted from event";
    }

    public String getRegistrationText() {
        
        if (!isRegistered) {
            return "You have registered for this event";
        } else {
            return "You have deleted your registration for this event";
        }
    }

    public boolean returnIsRegistered() {

        return isRegistered;

    }

}
