package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;

/**
 * RenderCircle component representing the radius and colour upon a body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class RenderCircle implements IComponent {

  // DECLARE a float of the radius
  public float r;
  // DECLARE an int of the rgba colour
  public int rgba;

  /**
   * Constructor: set the radius with a default colour of black
   * @param r
   */
  public RenderCircle(float r) {
    this(r, 0xFFFFFFFF);
  }

  /**
   * Constructor: seth the radius and colour fo the Circle
   * @param r     - float value of radius
   * @param rgba  - int valud of colour
   */
  public RenderCircle(float r, int rgba) {
    this.r = r;
    this.rgba = rgba;
  }

}
