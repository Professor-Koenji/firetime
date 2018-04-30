package com.koenji.ecs.event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;

import java.util.HashMap;
import java.util.Map;

/**
 * EventBus class defines the behaviour of an event implementation within the system
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class EventBus implements IEventBus {

  // Map of IEventControllers and associated IEventGroups
  private Group eventHandlers;

  /**
   * Constructor: instantiaties eventControllerGroups field
   */
  public EventBus() {
    eventHandlers = new Group();
  }

  /**
   * Method: used to fire an event to a single or multiple EventControllers depending on eventController or propagate flag
   * @param event           - Event type
   */
  @Override
  public void fireEvent(Event event) {
    eventHandlers.fireEvent(event);
  }

  /**
   * Method: used to add an EventType, EventHandler and IEventController to eventControllerGroups field
   * @param type            - EventType of event
   * @param handler         - EventHandler to handle events
   */
  @Override
  public <T extends Event> ISubscriber addEventHandler(EventType<T> type, EventHandler<? super T> handler) {
    eventHandlers.addEventHandler(type, handler);
    return new Subscriber(this, type, (EventHandler<? super Event>) handler);
  }

  /**
   * Method: used to remove an event from the eventControllerGroups field, removes for every scene if gloabal flag
   * @param type            - EventType of event
   */
  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type, EventHandler<? super T> handler) {
    eventHandlers.removeEventHandler(type, handler);
  }
}
