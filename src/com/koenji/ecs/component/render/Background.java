package com.koenji.ecs.component.render;

/**
 * Background component representing the colour given upon a body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davies & Chris Williams
 * @version 1.0
 */

import com.koenji.ecs.component.IComponent;

public class Background implements IComponent {
  // DECLARE an int to store the rgba value
  public int rgba;

  /**
   * Constructor: set the rgba field given the value passed
   * @param rgba - int of the rgba value
   */
  public Background(int rgba) {
    this.rgba = rgba;
  }

}
