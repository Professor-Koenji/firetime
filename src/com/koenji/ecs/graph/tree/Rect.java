package com.koenji.ecs.graph.tree;

import processing.core.PVector;

/**
* The Rectangle used within raw QuadTree spatial partitioning methods.
 *
 * @author Brad Davies
 * @version 1.0
 */
public class Rect implements IRect {

  private PVector position;
  private PVector size;

  /**
   * Construct a Rect from vectors
   * @param position A positional vector (a copy will be made).
   * @param size A size vector (a copy will be made).
   */
  public Rect(PVector position, PVector size) {
    this.position = position.copy();
    this.size = size.copy();
  }

  /**
   * Construct an origin Rect from scalars.
   * @param w The width of the rectangle.
   * @param h The height of the rectangle.
   */
  public Rect(float w, float h) {
    this(0, 0, w, h);
  }

  /**
   * Construct a Rect from scalars.
   * @param x The x position of the rectangle.
   * @param y The y position of the rectangle.
   * @param w The width of the rectangle.
   * @param h The height of the rectangle.
   */
  public Rect(float x, float y, float w, float h) {
    this.position = new PVector(x, y);
    this.size = new PVector(w, h);
  }

  /**
   * @return The x position of the rectangle.
   */
  @Override
  public float getX() {
    return position.x;
  }

  /**
   * @return The y position of the rectangle.
   */
  @Override
  public float getY() {
    return position.y;
  }

  /**
   * @return The width of the rectangle
   */
  @Override
  public float getW() {
    return size.x;
  }

  /**
   * @return The height of the rectangle
   */
  @Override
  public float getH() {
    return size.y;
  }
}
