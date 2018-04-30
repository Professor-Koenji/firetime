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
   * Method: used to add an EventHandler
   * @param type            - EventType of event
   * @param handler         - EventHandler to handle events
   */
  <T extends Event> ISubscriber addEventHandler(EventType<T> type, EventHandler<? super T> handler);

  /**
   * Method: used to remove an EventType&amp;EventHandler with option to do so globally
   * @param type            - EventType of event
   */
  <T extends Event> void removeEventHandler(EventType<T> type, EventHandler<? super T> handler);
}
