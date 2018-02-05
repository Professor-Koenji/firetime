package com.koenji.ecs.input;

import com.koenji.ecs.events.IKeyPress;
import com.koenji.ecs.events.IKeyRelease;
import com.koenji.ecs.events.IMousePress;
import com.koenji.ecs.events.IMouseRelease;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public interface IInputManager {
  void notify(InputEventType type, KeyEvent keyEvent);
  void notify(InputEventType type, MouseEvent mouseEvent);
  void subscribe(InputEventType type, IKeyPress o);
  void subscribe(InputEventType type, IKeyRelease o);
  void subscribe(InputEventType type, IMousePress o);
  void subscribe(InputEventType type, IMouseRelease o);
}
