package com.koenji.ecs.event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

/**
 * IEventGroup interface defines the behaviour of an event group implementation within the system
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public interface IEventGroup {

  /**
   * Method: used to fire an Event
   * @param event - Event type
   */
  void fireEvent(Event event);

  /**
   * Method: used to add an EventHandler for the type of EventType
   * @param type    - EventType of event
   * @param handler - EventHandler to handle event
   */
  <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler);

  /**
   * Method: used to remove EventType / EventHandler based on the EventType
   * @param type  - EventType
   */
  <T extends Event> void removeEventHandler(EventType<T> type);

  /**
   * Method: used to remove all EventTypes & EventHandlers
   */
  void removeAllEventHandlers();

}
