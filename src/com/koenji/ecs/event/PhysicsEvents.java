package com.koenji.ecs.event;

import com.koenji.ecs.event.events.CollisionEvent;
import javafx.event.Event;
import javafx.event.EventType;

public class PhysicsEvents {

  public static final EventType<CollisionEvent> COLLISION = new EventType<>(Event.ANY, "COLLISION_EVENT");

  public static final EventType<CollisionEvent> CONVEX_COLLISION = new EventType<>(COLLISION, "CONVEX_COLLISION");
  public static final EventType<CollisionEvent> CIRCLE_COLLISION = new EventType<>(COLLISION, "CIRCLE_COLLISION");

}
