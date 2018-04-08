package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;

/**
 * CircleBody component to represent the circle area of the body acting upon,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public class CircleBody implements IComponent {

  // DECLARE a float of the radius
  public float r;

  /**
   * Constructor: set the radius of the body
   * @param r - float of the radius
   */
  public CircleBody(float r) {
    this.r = r;
  }
}
