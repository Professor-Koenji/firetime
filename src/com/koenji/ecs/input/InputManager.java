package com.koenji.ecs.input;

import com.koenji.ecs.events.IKeyPressEvent;
import com.koenji.ecs.events.IObservable;
import com.koenji.ecs.events.KeyPressObservable;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.HashMap;
import java.util.Map;

public class InputManager implements IInputManager {

  private Map<InputEventType, IObservable> eventObservers;

  public InputManager() {
    eventObservers = new HashMap<>();

    eventObservers.put(InputEventType.KEY_PRESS, new KeyPressObservable());
  }

  public void notify(InputEventType type, KeyEvent keyEvent) {
    eventObservers.get(type).notify(keyEvent);
  }

  public void notify(InputEventType type, MouseEvent mouseEvent) {

  }

  public void subscribe(InputEventType type, IKeyPressEvent o) {
    eventObservers.get(type).add(o);
  }
}
