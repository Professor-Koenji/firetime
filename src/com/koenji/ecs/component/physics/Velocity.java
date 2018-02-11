package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

public class Velocity extends PVector implements IComponent {

  public Velocity() {
    this(0, 0);
  }

  public Velocity(float x, float y) {
    this(x, y, 0);
  }

  public Velocity(float x, float y, float z) {
    set(x, y, z);
  }

  public Velocity(PVector vector) {
    set(vector.x, vector.y, vector.z);
  }

}
