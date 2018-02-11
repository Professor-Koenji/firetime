package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

public class BoundingBox implements IComponent {

  public static final int REFLECT = 1;
  public static final int WRAP = 2;

  public int type;
  public PVector position;
  public PVector size;

  public BoundingBox(float x, float y, float w, float h) {
    this(BoundingBox.REFLECT, x, y, w, h);
  }

  public BoundingBox(int type, float x, float y, float w, float h) {
    this.type = type;
    this.position = new PVector(x, y);
    this.size = new PVector(w, h);
  }

  public BoundingBox(PVector pos, PVector size) {
    this(BoundingBox.REFLECT, pos, size);
  }

  public BoundingBox(int type, PVector pos, PVector size) {
    this.type = type;
    this.position = pos.copy();
    this.size = size.copy();
  }
}
