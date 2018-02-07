package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;

public class CircleBody implements IComponent {

  public float r;

  public CircleBody(float r) {
    this.r = r;
  }
}
