package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

/**
 * Velocity component to represent the velocity upon a given body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class Velocity extends PVector implements IComponent {

  /**
   * Constructor: defaults x,y = 0
   */
  public Velocity() {
    this(0, 0);
  }

  /**
   * Constructor: set the x&amp;y values, z = 0
   * @param x - float of the x value
   * @param y - float of the y value
   */
  public Velocity(float x, float y) {
    this(x, y, 0);
  }

  /**
   * Constructor: set the x,y&amp;z values
   * @param x - float of the x value
   * @param y - float of the y value
   * @param z - float of the z value
   */
  public Velocity(float x, float y, float z) {
    set(x, y, z);
  }

  /**
   * Constructor: set the x,y&amp;z values via a PVector class
   * @param vector - PVector class with x,y,z values
   */
  public Velocity(PVector vector) {
    set(vector.x, vector.y, vector.z);
  }

}
