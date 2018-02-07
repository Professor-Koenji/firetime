package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;

public class InverseMass implements IComponent {
  public float inverseMass;

  public InverseMass(float inverseMass) {
    this.inverseMass = inverseMass;
  }
}
