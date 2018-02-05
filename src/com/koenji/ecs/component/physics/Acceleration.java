package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

public class Acceleration extends PVector implements IComponent {

  public Acceleration() {
    this(0, 0);
  }

  public Acceleration(float x, float y) {
    this(x, y, 0);
  }

  public Acceleration(float x, float y, float z) {
    set(x, y, z);
  }

}
