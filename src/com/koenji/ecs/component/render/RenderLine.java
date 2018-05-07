package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

/**
 * RenderLine component representing a line upon a body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class RenderLine implements IComponent {
  // DECLARE a PVector of the position
  public PVector to;
  // DECLARE the rgba of the line
  public int rgba;
  // DECLARE the width of the line
  public float weight;

  /**
   * Constructor: set the x,y colour and weight of the RenderLine
   * @param x       - float x value
   * @param y       - float y value
   * @param rgba    - int rgba value
   * @param weight  - float weight value
   */
  public RenderLine(float x, float y, int rgba, float weight) {
    this.to = new PVector(x, y);
    this.rgba = rgba;
    this.weight = weight;
  }
}
