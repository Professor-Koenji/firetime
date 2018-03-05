package com.koenji.ecs.event.bus;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public interface IEventGroup {

  void fireEvent(Event event);
  <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler);
  <T extends Event> void removeEventHandler(EventType<T> type);
  void removeAllEventHandlers();

}
