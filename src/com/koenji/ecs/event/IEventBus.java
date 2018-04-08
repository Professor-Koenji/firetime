package com.koenji.ecs.event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

/**
 * IEventBus interface defines the behaviour of an event implementation within the system
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public interface IEventBus {
  /**
   * Method: used to fire an Event type
   * @param event - Event type
   */
  void fireEvent(Event event);

  /**
   * Method: used to fire an Event and include the IEventController with propagate flag
   * @param event           - Event type
   * @param eventController - IEventController to handle events
   * @param propagate       - boolean flag to propagate
   */
  void fireEvent(Event event, IEventController eventController, boolean propagate);

  /**
   * Method: used to add an EventHandler
   * @param type            - EventType of event
   * @param handler         - EventHandler to handle events
   * @param eventController - IEventController to handle event
   */
  <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler, IEventController eventController);

  /**
   * Method: used to remove an EventType&amp;EventHandler with option to do so globally
   * @param type            - EventType of event
   * @param eventController - IEventController that handles events
   * @param global          - boolean to remove all instances
   */
  <T extends Event> void removeEventHandler(EventType<T> type, IEventController eventController, boolean global);

  /**
   * Method: used to remove an IEventController with the option to do so globally
   * @param eventController - IEventController that handles events
   * @param global          - boolean to remove all instances
   */
  void removeAllEventHandlers(IEventController eventController, boolean global);
}
