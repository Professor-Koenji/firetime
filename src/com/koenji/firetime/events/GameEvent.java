package com.koenji.firetime.events;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {

  public int data;

  public static final EventType<GameEvent> END_OF_LEVEL = new EventType<>(Event.ANY, "END_OF_LEVEL");
  public static final EventType<GameEvent> GOT_KEY = new EventType<>(Event.ANY, "GOT_KEY");
  public static final EventType<GameEvent> KILLED_GUARD = new EventType<>(Event.ANY, "KILLED_GUARD");

  public GameEvent(EventType<? extends Event> eventType) {
    super(eventType);
  }

  public GameEvent(EventType<? extends Event> eventType, int data) {
    super(eventType);
    this.data = data;
  }

}
