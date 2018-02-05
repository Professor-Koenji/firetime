package com.koenji.ecs.input;

import com.koenji.ecs.events.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class InputManager implements IInputManager {

  private Map<InputEventType, IObservable> eventObservers;

  public InputManager() {
    eventObservers = new HashMap<>();

    eventObservers.put(InputEventType.KEY_PRESS, new KeyPressObservable());
    eventObservers.put(InputEventType.KEY_RELEASE, new KeyReleaseObservable());

    eventObservers.put(InputEventType.MOUSE_PRESS, new MousePressObservable());
    eventObservers.put(InputEventType.MOUSE_RELEASE, new MouseReleaseObservable());
  }

  public void notify(InputEventType type, KeyEvent keyEvent) {
    eventObservers.get(type).notify(keyEvent);
  }

  public void notify(InputEventType type, MouseEvent mouseEvent) {
    eventObservers.get(type).notify(mouseEvent);
  }

  @Override
  public void subscribe(InputEventType type, IKeyPress o) {
    eventObservers.get(type).add(o);
  }

  @Override
  public void subscribe(InputEventType type, IKeyRelease o) {
    eventObservers.get(type).add(o);
  }

  @Override
  public void subscribe(InputEventType type, IMousePress o) {
    eventObservers.get(type).add(o);
  }

  @Override
  public void subscribe(InputEventType type, IMouseRelease o) {
    eventObservers.get(type).add(o);
  }
}
