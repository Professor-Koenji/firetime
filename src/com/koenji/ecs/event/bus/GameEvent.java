package com.koenji.ecs.event.bus;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {

  public static final EventType<GameEvent> ANY = new EventType<>(Event.ANY, "GAME_EVENT");

  public GameEvent(EventType<GameEvent> event) {
    super(event);
  }
}
