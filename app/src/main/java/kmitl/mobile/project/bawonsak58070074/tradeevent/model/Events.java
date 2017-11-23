package kmitl.mobile.project.bawonsak58070074.tradeevent.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 23/11/2017 AD.
 */

public class Events {

    private List<Event> events = new ArrayList<>();

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event){
        this.events.add(event);
    }

}
