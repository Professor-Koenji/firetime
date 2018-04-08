package com.koenji.ecs.event.events;

import javafx.event.Event;
import javafx.event.EventType;
import processing.core.PVector;

/**
 * MouseEvent is the concrete class that can be 'fired' as a MouseEvent within the system
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class MouseEvent extends Event implements IMouseEvent {
  // Position of the mouse when event fired as a PVector
  private PVector position;
  // Mouse code that triggered the event
  private int button;

  /**
   * Constructor: used to set the position of the mouse and the button pressed
   * @param eventType - typeof EventType passed to Event
   * @param x         - x pos
   * @param y         - y pos
   * @param button    - int code of the mouse button
   */
  public MouseEvent(EventType<? extends MouseEvent> eventType, int x, int y, int button) {
    super(eventType);
    this.position = new PVector(x, y);
    this.button = button;
  }

  /**
   * Method: GETTER for the position of the mouse event
   * @return  - PVector of the coords
   */
  @Override
  public PVector position() {
    return position;
  }

  /**
   * Method: GETTER for the button field
   * @return - int of the mouse button clicked
   */
  @Override
  public int button() {
    return button;
  }
}
