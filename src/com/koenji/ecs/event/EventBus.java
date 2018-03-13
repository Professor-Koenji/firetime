package com.koenji.ecs.event;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.HashMap;
import java.util.Map;

public class EventBus implements IEventBus {

  private Map<IEventController, IEventGroup> eventControllerGroups;

  public EventBus() {
    eventControllerGroups = new HashMap<>();
  }

  @Override
  public void fireEvent(Event event) {
    fireEvent(event, null, true);
  }

  @Override
  public void fireEvent(Event event, IEventController eventController, boolean propagate) {
    if (propagate || eventController == null) {
      // Dispatch event on all eventgroups#
      for (IEventGroup eg : eventControllerGroups.values()) {
        eg.fireEvent(event);
      }
    } else {
      if (!eventControllerGroups.containsKey(eventController)) return;
      IEventGroup eg = eventControllerGroups.get(eventController);
      eg.fireEvent(event);
    }
  }

  @Override
  public <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler, IEventController eventController) {
    if (!eventControllerGroups.containsKey(eventController)) {
      eventControllerGroups.put(eventController, new EventGroup());
    }
    eventControllerGroups.get(eventController).addEventHandler(type, handler);
  }

  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type, IEventController eventController, boolean global) {
    if (global) {
      // Remove this event type on EVERY SCENE
      for (IEventGroup eg : eventControllerGroups.values()) {
        eg.removeEventHandler(type);
      }
    } else {
      if (eventController != null && !eventControllerGroups.containsKey(eventController)) return;
      IEventGroup eg = eventControllerGroups.get(eventController);
      eg.removeEventHandler(type);
    }
  }

  @Override
  public void removeAllEventHandlers(IEventController eventController, boolean global) {
    if (global) {
      for (IEventGroup eg : eventControllerGroups.values()) {
        eg.removeAllEventHandlers();
      }
    } else {
      if (!eventControllerGroups.containsKey(eventController)) return;
      IEventGroup eg = eventControllerGroups.get(eventController);
      eg.removeAllEventHandlers();
    }
  }
}
