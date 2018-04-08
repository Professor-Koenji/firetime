package com.koenji.ecs.event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

/**
 * IEventController interface defines the behaviour of an event handler within the system
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public interface IEventController {
  /**
   * Method: used to fire an Event
   * @param type - Event type
   */
  void fireEvent(Event type);

  /**
   * Method: used to fire and propagate the Event
   * @param type      - Event type
   * @param propagate - boolean flag
   */
  void fireEvent(Event type, boolean propagate);

  /**
   * Method: used to add an event Handler for the specified EventType
   * @param type    - EventType class
   * @param handler - EventHandler that 'handles' the event
   */
  <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler);

  /**
   * Method: used to remove an EventType
   * @param type - EventType class to remove
   */
  <T extends Event> void removeEventHandler(EventType<T> type);

  /**
   * Method: used to remove an EventType with the global flag
   * @param type   - EventType to remove
   * @param global - boolean flag
   */
  <T extends Event> void removeEventHandler(EventType<T> type, boolean global);

  /**
   * Method: option to remove all EventHandlers
   */
  void removeAllEventHandlers();

  /**
   * Method: option to remove all EventHandlers globally
   * @param global - boolean flag
   */
  void removeAllEventHandlers(boolean global);
}
