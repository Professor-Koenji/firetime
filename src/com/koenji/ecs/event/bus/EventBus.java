package com.koenji.ecs.event.bus;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public class EventBus implements IEventBus {

  private EventGroup group = new EventGroup();

  @Override
  public void fireEvent(Event event) {
    group.fireEvent(event);
  }

  @Override
  public <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler) {
    group.addEventHandler(type, handler);
  }

  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type) {
    group.removeEventHandler(type);
  }
}
