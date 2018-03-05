package com.koenji.ecs.event.bus;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.HashMap;
import java.util.Map;

public class EventGroup implements IEventGroup {

  private Map<EventType, EventHandler> eventObjects;

  public EventGroup() {
    eventObjects = new HashMap<>();
  }

  @Override
  public void fireEvent(Event event) {
    EventType et = event.getEventType();
    handleEvent(et, event);
  }

  @SuppressWarnings("unchecked")
  private <T extends Event> void handleEvent(EventType et, T event) {
    if (eventObjects.containsKey(et)) eventObjects.get(et).handle(event);
    EventType parent = et.getSuperType();
    if (parent != null) handleEvent(parent, event);
  }

  @Override
  public <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler) {
    eventObjects.put(type, handler);
  }

  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type) {
    eventObjects.remove(type);
  }

  @Override
  public void removeAllEventHandlers() {
    eventObjects.clear();
  }
}
