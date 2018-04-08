package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

/**
 * Position component to represent the placement of a given body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public class Position extends PVector implements IComponent {
  /**
   * Constructor: defaults to x,y = 0
   */
  public Position() {
    this(0, 0);
  }

  /**
   * Constructor: set the x and y position
   * @param x - float value of x
   * @param y - float value of y
   */
  public Position(float x, float y) {
    this(x, y, 0);
  }

  /**
   * Constructor: set the x,y and z position
   * @param x - float value of x
   * @param y - float value of y
   * @param z - float value of z
   */
  public Position(float x, float y, float z) {
    set(x, y, z);
  }

  /**
   * Constructor: set the x,y & z position via PVector class
   * @param v - PVector class with x,y,z values
   */
  public Position(PVector v) {
    set(v.x, v.y, v.z);
  }
}
