package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

public class Position extends PVector implements IComponent {
  public Position() {
    this(0, 0);
  }

  public Position(float x, float y) {
    this(x, y, 0);
  }

  public Position(float x, float y, float z) {
    set(x, y, z);
  }
}
