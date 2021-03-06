package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;

/**
 * Rotation component to represent the angle upon a given body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class Rotation implements IComponent {

  // DECLARE a float to rep the angle
  public float angle;
  
  public Rotation() {
    this(0);
  }

  /**
   * Constructor: set the angle field given the value
   * @param angle - float of the angle
   */
  public Rotation(float angle) {
    this.angle = angle;
  }
}
