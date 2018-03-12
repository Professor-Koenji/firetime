package com.koenji.ecs.event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public interface IEventController {
  void fireEvent(Event type);
  void fireEvent(Event type, boolean propagate);
  <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler);
  <T extends Event> void removeEventHandler(EventType<T> type);
  <T extends Event> void removeEventHandler(EventType<T> type, boolean global);
  void removeAllEventHandlers();
  void removeAllEventHandlers(boolean global);
}
