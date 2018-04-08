package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

/**
 * Acceleration component representing/wrapping the vector library required to represent acceleration upon a body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public class Acceleration extends PVector implements IComponent {

  /**
   * Constructor: defaults to x,y,z = 0
   */
  public Acceleration() {
    this(0, 0);
  }

  /**
   * Constructor: set x & y values, z set to 0
   *
   * @param x - float value
   * @param y - float value
   */
  public Acceleration(float x, float y) {
    this(x, y, 0);
  }

  /**
   * Constructor: set x,y & z values
   *
   * @param x - float value
   * @param y - float value
   * @param z - float value
   */
  public Acceleration(float x, float y, float z) {
    set(x, y, z);
  }

  /**
   * Constructor: set the x,y & z values via a PVector
   *
   * @param v - PVector class
   */
  public Acceleration(PVector v) {
    set(v.x, v.y, v.z);
  }

}
