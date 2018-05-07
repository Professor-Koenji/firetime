package com.koenji.ecs.event.events;

import com.koenji.ecs.entity.IEntity;
import javafx.event.Event;
import javafx.event.EventType;

public class CollisionEvent extends Event implements IColllisionEvent {

  private IEntity a;
  private IEntity b;

  /**
   * Defines a new CollisionEvent, of the given type and with the two colliding entities.
   * This should be fed into a fireEvent call on an event group.
   * @param eventType The event type that has occured
   * @param a The first entity
   * @param b The second entity
   */
  public CollisionEvent(EventType<? extends CollisionEvent> eventType, IEntity a, IEntity b) {
    super(eventType);
    this.a = a;
    this.b = b;
  }

  /**
   * The first colliding entity.
   * @return One of the colliding entities.
   */
  @Override
  public IEntity a() {
    return a;
  }

  /**
   * The second colliding entity.
   * @return The other colliding entity.
   */
  @Override
  public IEntity b() {
    return b;
  }
}
