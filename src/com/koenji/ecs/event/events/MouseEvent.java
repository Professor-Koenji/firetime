package com.koenji.ecs.event.events;

import com.koenji.ecs.event.InputEvents;
import javafx.event.Event;
import javafx.event.EventType;
import processing.core.PVector;

public class MouseEvent extends Event implements IMouseEvent {

  private PVector position;
  private int button;

  public MouseEvent(EventType<? extends MouseEvent> eventType, int x, int y, int button) {
    super(eventType);
    this.position = new PVector(x, y);
    this.button = button;
  }

  @Override
  public PVector position() {
    return position;
  }

  @Override
  public int button() {
    return button;
  }
}
