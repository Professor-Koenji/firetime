package com.koenji.ecs.event.bus;

import com.koenji.ecs.scene.IScene;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public interface IEventBus {
  void fireEvent(Event event, IScene scene, boolean propagate);

  <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler, IScene scene);
  <T extends Event> void removeEventHandler(EventType<T> type, IScene scene, boolean global);

  void removeAllEventHandlers(IScene scene, boolean global);
}
