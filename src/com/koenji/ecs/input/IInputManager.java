package com.koenji.ecs.input;

import com.koenji.ecs.events.IKeyPressEvent;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public interface IInputManager {
  void notify(InputEventType type, KeyEvent keyEvent);
  void notify(InputEventType type, MouseEvent mouseEvent);
  void subscribe(InputEventType type, IKeyPressEvent o);
}
