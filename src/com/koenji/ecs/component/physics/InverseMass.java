package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;

/**
 * InverseMass component to represent the negative mass upon a given body,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public class InverseMass implements IComponent {
  // DECLARE a float for the inverse mass value
  public float inverseMass;

  /**
   * Constructor: the inverse mass of the body
   *
   * @param inverseMass - float of the value
   */
  public InverseMass(float inverseMass) {
    this.inverseMass = inverseMass;
  }
}
