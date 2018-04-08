package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;

public class AngularVelocity implements IComponent {

  public float velocity;

  public AngularVelocity() {
    this(0);
  }

  public AngularVelocity(float velocity) {
    this.velocity = velocity;
  }

}
