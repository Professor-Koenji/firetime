package com.koenji.firetime.events;

import javafx.event.Event;
import javafx.event.EventType;

public class EmitBulletEvent extends Event {

  public static final EventType<EmitBulletEvent> EMIT_BULLET = new EventType<>(Event.ANY, "EMIT_BULLET");

  private float x;
  private float y;
  private float angle;

  public EmitBulletEvent(EventType<? extends Event> eventType, float x, float y, float angle) {
    super(eventType);
    //
    this.x = x;
    this.y = y;
    this.angle = angle;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float angle() {
    return angle;
  }
}
