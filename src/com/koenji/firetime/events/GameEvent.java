package com.koenji.firetime.events;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {

  public static final EventType<GameEvent> END_OF_LEVEL = new EventType<>(Event.ANY, "END_OF_LEVEL");

  public GameEvent(EventType<? extends Event> eventType) {
    super(eventType);
  }

}
