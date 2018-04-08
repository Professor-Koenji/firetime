package com.koenji.ecs.component.render;

import com.koenji.ecs.component.IComponent;

/**
 * RenderStroke component representing a side upon a body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davies & Chris Williams
 * @version 1.0
 */

public class Stroke implements IComponent {
  // DECLARE a weight of the stroke
  public float weight;
  // DECLARE a colour of the stroke
  public int rgba;

  /**
   * Constructor: set the weight and colour of the stroke
   * @param weight  - float of the weight
   * @param rgba    - int of the colour
   */
  public Stroke(float weight, int rgba) {
    this.weight = weight;
    this.rgba = rgba;
  }

}
