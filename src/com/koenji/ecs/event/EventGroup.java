package com.koenji.ecs.event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.ArrayList;
import java.util.List;

/**
 * EventGroup defines the implementation of IEventGroup which handlers and passes the Event fired to an EventHandler within the system
 * EventGroup's can be mapped to any individual 'region' where Events either should remain scoped
 * or be propagated outside of the EventGroup and into the 'global' event space.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class EventGroup implements IEventGroup {

  // Map to store the EventType and EventHandlers
  private List<EventObject> eventObjects;

  /**
   * Constructor: creates the eventObjects Map
   */
  public EventGroup() {
    eventObjects = new ArrayList<>();
  }

  /**
   * Method: used to pass the event with handler to the handleEvent method
   * @param event - Event type to fire
   */
  @Override
  public void fireEvent(Event event) {
    EventType et = event.getEventType();
    handleEvent(et, event);
  }

  /**
   * Method: recursive method used to fire every parent of the EventType
   * @param et    - EventType of event
   * @param event - Event type that was fired
   */
  @SuppressWarnings("unchecked")
  private <T extends Event> void handleEvent(EventType et, T event) {
    for (EventObject eo : eventObjects) {
      if (eo.eventType == et) eo.eventHandler.handle(event);
    }
    EventType parent = et.getSuperType();
    if (parent != null) handleEvent(parent, event);
  }

  /**
   * Method: used to add an EventHandler based on the EventType to the eventObjects Map
   * @param type    - EventType of event
   * @param handler - EventHandler to handle event
   */
  @Override
  public <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler) {
    eventObjects.add(new EventObject(type, handler));
  }

  /**
   * Method: used to remove the type of EventType from the eventObjects Map
   * @param type  - EventType of event to remove
   */
  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type) {
    for (int i = eventObjects.size() - 1; i >= 0; --i) {
      if (eventObjects.get(i).eventType == type) eventObjects.remove(i);
    }
  }

  /**
   * Method: used to remove and clear the eventObjects Map
   */
  @Override
  public void removeAllEventHandlers() {
    eventObjects.clear();
  }

  private class EventObject {
    public EventType eventType;
    public EventHandler eventHandler;

    public EventObject(EventType eventType, EventHandler eventHandler) {
      this.eventType = eventType;
      this.eventHandler = eventHandler;
    }
  }
}
