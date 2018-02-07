package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

public class Gravity implements IComponent {

  public PVector gravity;

  public Gravity(float gravity) {
    this.gravity = new PVector(0, gravity);
  }
}
