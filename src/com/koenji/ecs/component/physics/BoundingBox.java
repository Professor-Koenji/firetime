package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

public class BoundingBox implements IComponent {

  public PVector position;
  public PVector size;

  public BoundingBox(float x, float y, float w, float h) {
    this.position = new PVector(x, y);
    this.size = new PVector(w, h);
  }

  public BoundingBox(float[] options) {
    this.position = new PVector(options[0], options[1]);
    this.size = new PVector(options[2], options[3]);
  }
}
