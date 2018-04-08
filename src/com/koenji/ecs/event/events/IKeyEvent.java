package com.koenji.ecs.event.events;

/**
 * IKeyEvent interface defines the behaviour of a key Event within the system
 *
 * @author Brad Davies & Chris Williams
 * @version 1.0
 */

public interface IKeyEvent {
  /**
   * Method: the keyCode of the Event
   * @return - int keycode
   */
  int keyCode();

  /**
   * Method: flag to represent if key held down
   * @return - boolean flag
   */
  boolean isAutoRepeat();
}
