package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;

public class Circle implements IComponent {

  public float r;

  public Circle(float r) {
    this.r = r;
  }
}
