package com.koenji.ecs.event.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * KeyEvent is the concrete class that can be 'fired' as a KeyEvent within the system
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public class KeyEvent extends Event implements IKeyEvent {
  // store the keycode
  private int keyCode;
  // flag to represent if key repeated
  private boolean isAutoRepeat;

  /**
   * Constructor: used to set the keyCode and isAutoRepeat field
   * @param eventType     - typeof EventType passed to Event
   * @param keyCode       - int keycode
   * @param isAutoRepeat  - flag if repeated
   */
  public KeyEvent(EventType<? extends KeyEvent> eventType, int keyCode, boolean isAutoRepeat) {
    super(eventType);
    this.keyCode = keyCode;
    this.isAutoRepeat = isAutoRepeat;
  }

  /**
   * Method: GETTER of the keyCode field
   * @return int - keycode
   */
  @Override
  public int keyCode() {
    return keyCode;
  }

  /**
   * Method: GETTER for the isAutoRepeat field
   * @return boolean - key repeated
   */
  @Override
  public boolean isAutoRepeat() {
    return isAutoRepeat;
  }
}
