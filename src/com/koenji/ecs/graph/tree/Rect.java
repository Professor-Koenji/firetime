package com.koenji.ecs.graph.tree;

import processing.core.PVector;

public class Rect implements IRect {

  private PVector position;
  private PVector size;

  public Rect(PVector position, PVector size) {
    this.position = position.copy();
    this.size = size.copy();
  }

  public Rect(float w, float h) {
    this(0, 0, w, h);
  }

  public Rect(float x, float y, float w, float h) {
    this.position = new PVector(x, y);
    this.size = new PVector(w, h);
  }

  @Override
  public float getX() {
    return position.x;
  }

  @Override
  public float getY() {
    return position.y;
  }

  @Override
  public float getW() {
    return size.x;
  }

  @Override
  public float getH() {
    return size.y;
  }
}
