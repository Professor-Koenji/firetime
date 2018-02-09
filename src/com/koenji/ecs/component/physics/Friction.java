package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

public class Friction extends PVector implements IComponent {

  public Friction() {
    this(1, 1);
  }

  public Friction(float x) {
    //noinspection SuspiciousNameCombination
    this(x, x);
  }

  public Friction(float[] x) {
    this(x[0], x[0]);
  }

  public Friction(float x, float y) {
    set(x, y);
  }

}
