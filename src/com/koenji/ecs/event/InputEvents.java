package com.koenji.ecs.event;

import com.koenji.ecs.event.events.KeyEvent;
import com.koenji.ecs.event.events.MouseEvent;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * InputEvents defines the key input events handled within the system, mapping them to a respective EventType
 *
 * @author Brad Davies & Chris Williams
 * @version 1.0
 */

public class InputEvents {

  public static final EventType<MouseEvent> MOUSE = new EventType<>(Event.ANY, "MOUSE_EVENT");
  public static final EventType<KeyEvent> KEY = new EventType<>(Event.ANY, "KEY_EVENT");

  public static final EventType<MouseEvent> MOUSE_PRESSED = new EventType<>(MOUSE, "MOUSE_PRESSED_EVENT");
  public static final EventType<MouseEvent> MOUSE_RELEASED = new EventType<>(MOUSE, "MOUSE_RELEASED_EVENT");
  public static final EventType<MouseEvent> MOUSE_MOVED = new EventType<>(MOUSE, "MOUSE_MOVED_EVENT");

  public static final EventType<KeyEvent> KEY_PRESSED = new EventType<>(KEY, "KEY_PRESSED_EVENT");
  public static final EventType<KeyEvent> KEY_RELEASED = new EventType<>(KEY, "KEY_RELEASE_EVENT");


}
