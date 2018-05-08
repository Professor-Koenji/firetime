package com.koenji.ecs.component.render;

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
