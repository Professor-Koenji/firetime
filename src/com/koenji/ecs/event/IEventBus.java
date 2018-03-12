package com.koenji.ecs.event;

import com.koenji.ecs.scene.IScene;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public interface IEventBus {
  void fireEvent(Event event);
  void fireEvent(Event event, IEventController eventController, boolean propagate);
  <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler, IEventController eventController);
  <T extends Event> void removeEventHandler(EventType<T> type, IEventController eventController, boolean global);
  void removeAllEventHandlers(IEventController eventController, boolean global);
}
