package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

public class Gravity extends PVector implements IComponent {

  public Gravity(float gravity) {
    set(0, gravity);
  }

  public Gravity(PVector v) {
    set(v.x, v.y, v.z);
  }
}
