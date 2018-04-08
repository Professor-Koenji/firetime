package com.koenji.ecs.event.events;

import processing.core.PVector;

/**
 * IKeyEvent interface defines the behaviour of a mouse Event within the system
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public interface IMouseEvent {

  /**
   * Method: used to return the position of the mouse when the event was fired
   * @return - PVector of the position
   */
  PVector position();

  /**
   * Method: used to return the mouse button that was pressed
   * @return - int code of the mouse button
   */
  int button();

}
