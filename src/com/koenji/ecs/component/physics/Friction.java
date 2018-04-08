package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

/**
 * Friction component to represent the resistance upon a given body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public class Friction extends PVector implements IComponent {

  /**
   * Constructor: sets a default of x=1,y=1
   */
  public Friction() {
    this(1, 1);
  }

  /**
   * Constructor: set the x value
   * @param x - value of x
   */
  public Friction(float x) {
    //noinspection SuspiciousNameCombination
    this(x, x);
  }

  /**
   * Constructor: set the x & y value
   * @param x - value of x
   * @param y - value of y
   */
  public Friction(float x, float y) {
    set(x, y);
  }

  /**
   * Constructor: set x,y,z values via a PVector
   * @param v - PVector class of x,y,z
   */
  public Friction(PVector v) {
    set(v.x, v.y, v.z);
  }

}
