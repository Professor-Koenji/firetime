package com.koenji.firetime.event;

import javafx.event.Event;
import javafx.event.EventType;

public class Events {

  // Global game event
  public static final EventType<Event> ANY = new EventType<>(Event.ANY);

  // Specific events
  public static final EventType<WeaponFireEvent> WEAPON_FIRE = new EventType<>(ANY);
}
