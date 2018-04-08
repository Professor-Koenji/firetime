package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

/**
 * BoundingBox component to represent the confines of the body acting upon,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public class BoundingBox implements IComponent {

  // DECLARE a static int to set reflection
  public static final int REFLECT = 1;
  // DECLARE a static int to set wrapping
  public static final int WRAP = 2;
  // DECLARE an int of the type
  public int type;
  // DELCARE a PVector of the position
  public PVector position;
  // DECLARE a PVector of the size
  public PVector size;

  /**
   * Constructor: set the position, height & width of the bounding box, defaults to static REFLECT val
   * @param x - float of x pos
   * @param y - float of y pos
   * @param w - float of width
   * @param h - float of height
   */
  public BoundingBox(float x, float y, float w, float h) {
    this(BoundingBox.REFLECT, x, y, w, h);
  }

  /**
   * Constructor: set the type, position, height & width of the bounding box
   * @param type - int of type
   * @param x    - float of x pos
   * @param y    - float of y pos
   * @param w    - float of width
   * @param h    - float of height
   */
  public BoundingBox(int type, float x, float y, float w, float h) {
    this.type = type;
    this.position = new PVector(x, y);
    this.size = new PVector(w, h);
  }

  /**
   * Constructor: set position and size via PVector class
   * @param pos   - PVector of position
   * @param size  - PVector of size
   */
  public BoundingBox(PVector pos, PVector size) {
    this(BoundingBox.REFLECT, pos, size);
  }

  /**
   * Constructor: set type, postion and size via PVector class
   * @param type - int of type
   * @param pos  - PVector of position
   * @param size - PVector of size
   */
  public BoundingBox(int type, PVector pos, PVector size) {
    this.type = type;
    this.position = pos.copy();
    this.size = size.copy();
  }
}
