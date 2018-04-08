package com.koenji.ecs.event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

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
  private Map<IEventController, IEventGroup> eventControllerGroups;

  /**
   * Constructor: instantiaties eventControllerGroups field
   */
  public EventBus() {
    eventControllerGroups = new HashMap<>();
  }

  /**
   * Method: used to fire an event, set the propagate flag to true
   * @param event - Event type
   */
  @Override
  public void fireEvent(Event event) {
    fireEvent(event, null, true);
  }

  /**
   * Method: used to fire an event to a single or multiple EventControllers depending on eventController or propagate flag
   * @param event           - Event type
   * @param eventController - IEventController to handle events
   * @param propagate       - boolean flag to propagate
   */
  @Override
  public void fireEvent(Event event, IEventController eventController, boolean propagate) {
    if (propagate || eventController == null) {
      // Dispatch event on all eventgroups#
      for (IEventGroup eg : eventControllerGroups.values()) {
        eg.fireEvent(event);
      }
    } else {
      if (!eventControllerGroups.containsKey(eventController)) return;
      IEventGroup eg = eventControllerGroups.get(eventController);
      eg.fireEvent(event);
    }
  }

  /**
   * Method: used to add an EventType, EventHandler and IEventController to eventControllerGroups field
   * @param type            - EventType of event
   * @param handler         - EventHandler to handle events
   * @param eventController - IEventController to handle event
   */
  @Override
  public <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler, IEventController eventController) {
    if (!eventControllerGroups.containsKey(eventController)) {
      eventControllerGroups.put(eventController, new EventGroup());
    }
    eventControllerGroups.get(eventController).addEventHandler(type, handler);
  }

  /**
   * Method: used to remove an event from the eventControllerGroups field, removes for every scene if gloabal flag
   * @param type            - EventType of event
   * @param eventController - IEventController that handles events
   * @param global          - boolean to remove all instances
   * @param <T>
   */
  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type, IEventController eventController, boolean global) {
    if (global) {
      // Remove this event type on EVERY SCENE
      for (IEventGroup eg : eventControllerGroups.values()) {
        eg.removeEventHandler(type);
      }
    } else {
      if (eventController != null && !eventControllerGroups.containsKey(eventController)) return;
      IEventGroup eg = eventControllerGroups.get(eventController);
      eg.removeEventHandler(type);
    }
  }

  /**
   * Method: used to remove an IEventController from the eventControllerGroups field
   * @param eventController - IEventController that handles events
   * @param global          - boolean to remove all instances
   */
  @Override
  public void removeAllEventHandlers(IEventController eventController, boolean global) {
    if (global) {
      for (IEventGroup eg : eventControllerGroups.values()) {
        eg.removeAllEventHandlers();
      }
    } else {
      if (!eventControllerGroups.containsKey(eventController)) return;
      IEventGroup eg = eventControllerGroups.get(eventController);
      eg.removeAllEventHandlers();
    }
  }
}
