package com.koenji.ecs.input;

import com.koenji.ecs.events.*;
import processing.event.Event;

import java.util.HashMap;
import java.util.Map;

public class InputManager implements IInputManager {

  private Map<Class<? extends IObserver>, IObservable> eventObservers;

  public InputManager() {
    eventObservers = new HashMap<>();

    eventObservers.put(IKeyPress.class, new KeyPressObservable());
    eventObservers.put(IKeyRelease.class, new KeyReleaseObservable());

    eventObservers.put(IMousePress.class, new MousePressObservable());
    eventObservers.put(IMouseRelease.class, new MouseReleaseObservable());
  }

  @SuppressWarnings("unchecked")
  public <T extends IObserver> void notify(Class <T> type, Event event) {
    eventObservers.get(type).notify(event);
  }

  @SuppressWarnings("unchecked")
  public <T extends IObserver> void subscribe(Class <T> type, T instance) {
    eventObservers.get(type).add(instance);
  }
}
