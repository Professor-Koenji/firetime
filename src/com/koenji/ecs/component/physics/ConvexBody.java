package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ConvexBody component to represent the confines of the body acting upon,
 * a concrete implementation of the IComponent interface
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public class ConvexBody implements IComponent {

  // DECLARE the vertices of the convex shape
  public List<PVector> vertices;

  // DECLARE the approximate size of this body
  // Used in broad-phase collision checks
  public int size;

  // DECLARE the is Static value
  public boolean isStatic;

  /**
   * Method: create a static instance of a square body with size passed
   * @param size - size of the body
   * @return     - typeof ConvexBody
   */
  public static ConvexBody square(float size) {
    return square(size, 0, 0);
  }

  /**
   * Method: create a static instance of a square body with size and offset for x & y
   * @param size      - of the body
   * @param offsetX   - x offset
   * @param offsetY   - y offset
   * @return          - typeof ConvexBody
   */
  public static ConvexBody square(float size, float offsetX, float offsetY) {
    return new ConvexBody(
      (int) size,
      false,
      new PVector(offsetX, offsetY),
      new PVector(offsetX + size, offsetY),
      new PVector(offsetX + size, offsetY + size),
      new PVector(offsetX, offsetY + size)
    );
  }

  /**
   * Method: create a static instance of a polygon body setting the num of sides and size
   * @param sides - of the body
   * @param size  - of the body
   * @return      - typeof ConvexBody
   */
  public static ConvexBody polygon(int sides, float size) {
    return polygon(sides, size, 0, 0);
  }

  /**
   * Method: create a static instance of a polygon, setting the size
   * @param sides   - of the body
   * @param size    - of the body
   * @param offsetX - x offset
   * @param offsetY - y offset
   * @return        - typeof ConvexBody
   */
  public static ConvexBody polygon(int sides, float size, float offsetX, float offsetY) {
    PVector[] vs = new PVector[sides];
    float angleSlice = ((float) Math.PI * 2) / sides;
    float angle = angleSlice / 2;
    for (int i = 0; i < sides; ++i) {
      vs[i] = new PVector((float) Math.sin(angle) * size + offsetX, (float) Math.cos(angle) * size + offsetY);
      angle += angleSlice;
    }
    return new ConvexBody((int) size, false,  vs);
  }

  /**
   * Constructor: creates a new ConvexBody with the given vertices
   * @param size     - the approximate size of the body. Always over-estimate.
   * @param vertices - a list of points for the vertices of the shape
   */
  public ConvexBody(int size, PVector ...vertices) {
    this(size, false, vertices);
  }

  /**
   * Constructor: creates a new ConvexBody with given vertices and define if static
   * @param size     - the approximate size of the body. Always over-estimate.
   * @param isStatic - flag to set the isStatic field
   * @param vertices - a list of points for the vertices of the shape
   */
  public ConvexBody(int size, boolean isStatic, PVector ...vertices) {
    this.size = size;
    this.isStatic = isStatic;
    this.vertices = new ArrayList<>(Arrays.asList(vertices));
  }

  /**
   * Method: Gets a list of the edges of the shape as vectors between vertices
   * @return - A list of edge vectors
   */
  public List<PVector> edges() {
    return edges(null);
  }

  /**
   * Method: Gets a list of the edges of the shape as vectors between vertices with Rotation
   * @param r - Rotation class of the angle
   * @return  - A list of edge vectors
   */
  public List<PVector> edges(Rotation r) {
    List<PVector> es = new ArrayList<>();
    for (int i = 0; i < vertices.size(); ++i) {
      PVector a = vertices.get(i);
      PVector b = vertices.get((i + 1) % vertices.size());
      PVector edge;
      //
      if (r != null) {
        edge = PVector.sub(rotate(b, r.angle), rotate(a, r.angle));
      } else {
        edge = PVector.sub(b, a);
      }
      // Rotate the edge
      es.add(edge);
    }
    return es;
  }

  /**
   * Method: Rotates vertices List by the Rotation class
   * @param r - value of the Rotation class
   * @return  - List of edge vectors
   */
  public List<PVector> rotatedVertices(Rotation r) {
    if (r == null) return vertices;
    List<PVector> rots = new ArrayList<>();
    for (PVector v : vertices) {
      rots.add(rotate(v, r.angle));
    }
    return rots;
  }

  /**
   * Method: Rotates a PVector given the angle
   * @param v     - PVector class, x,y
   * @param angle - float of the angle
   * @return      - new PVector with rotation
   */
  private PVector rotate(PVector v, float angle) {
    float x = v.x * (float) Math.cos(angle) - v.y * (float) Math.sin(angle);
    float y = v.x * (float) Math.sin(angle) + v.y * (float) Math.cos(angle);
    return new PVector(x, y);
  }

  /**
   * Method: Setter of the isStatic field
   * @param isStatic - boolean flag
   */
  public void setStatic(boolean isStatic) {
    this.isStatic = isStatic;
  }

}
