package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;

public class Rotation implements IComponent {

  public float angle;

  public Rotation(float angle) {
    this.angle = angle;
  }
}
