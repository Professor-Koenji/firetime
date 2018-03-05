package com.koenji.ecs.event;

import com.koenji.ecs.scene.IScene;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.HashMap;
import java.util.Map;

public class EventBus implements IEventBus {

  private Map<IScene, IEventGroup> sceneEventGroups;

  public EventBus() {
    sceneEventGroups = new HashMap<>();
  }

  @Override
  public void fireEvent(Event event) {
    fireEvent(event, null, true);
  }

  @Override
  public void fireEvent(Event event, IScene scene, boolean propagate) {
    if (propagate) {
      // Dispatch event on all eventgroups#
      for (IEventGroup eg : sceneEventGroups.values()) {
        eg.fireEvent(event);
      }
    } else {
      if (scene != null && !sceneEventGroups.containsKey(scene)) return;
      IEventGroup eg = sceneEventGroups.get(scene);
      eg.fireEvent(event);
    }
  }

  @Override
  public <T extends Event> void addEventHandler(EventType<T> type, EventHandler<? super T> handler, IScene scene) {
    if (!sceneEventGroups.containsKey(scene)) {
      sceneEventGroups.put(scene, new EventGroup());
    }
    sceneEventGroups.get(scene).addEventHandler(type, handler);
  }

  @Override
  public <T extends Event> void removeEventHandler(EventType<T> type, IScene scene, boolean global) {
    if (global) {
      // Remove this event type on EVERY SCENE
      for (IEventGroup eg : sceneEventGroups.values()) {
        eg.removeEventHandler(type);
      }
    } else {
      if (!sceneEventGroups.containsKey(scene)) return;
      IEventGroup eg = sceneEventGroups.get(scene);
      eg.removeEventHandler(type);
    }
  }

  @Override
  public void removeAllEventHandlers(IScene scene, boolean global) {
    if (global) {
      for (IEventGroup eg : sceneEventGroups.values()) {
        eg.removeAllEventHandlers();
      }
    } else {
      if (!sceneEventGroups.containsKey(scene)) return;
      IEventGroup eg = sceneEventGroups.get(scene);
      eg.removeAllEventHandlers();
    }
  }
}
