package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

/**
 * Gravity component to represent the force upon a given body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class Gravity extends PVector implements IComponent {

  /**
   * Constructor: set the value of y
   * @param gravity - float of the y value
   */
  public Gravity(float gravity) {
    set(0, gravity);
  }

  /**
   * Constructor: set the x,y,z value via the PVector class
   * @param v - PVector class of x,y,z
   */
  public Gravity(PVector v) {
    set(v.x, v.y, v.z);
  }
}
