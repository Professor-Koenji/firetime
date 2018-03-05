package com.koenji.ecs.event.events;

import javafx.event.Event;
import javafx.event.EventType;

public class KeyEvent extends Event implements IKeyEvent {

  private int keyCode;
  private boolean isAutoRepeat;

  public KeyEvent(EventType<? extends KeyEvent> eventType, int keyCode, boolean isAutoRepeat) {
    super(eventType);
    this.keyCode = keyCode;
    this.isAutoRepeat = isAutoRepeat;
  }

  @Override
  public int keyCode() {
    return keyCode;
  }

  @Override
  public boolean isAutoRepeat() {
    return isAutoRepeat;
  }
}
