package com.koenji.ecs.event.events;

import com.koenji.ecs.entity.IEntity;
import javafx.event.Event;
import javafx.event.EventType;

public class CollisionEvent extends Event implements IColllisionEvent {

  private IEntity a;
  private IEntity b;

  public CollisionEvent(EventType<? extends CollisionEvent> eventType, IEntity a, IEntity b) {
    super(eventType);
    this.a = a;
    this.b = b;
  }

  @Override
  public IEntity a() {
    return a;
  }

  @Override
  public IEntity b() {
    return b;
  }
}
