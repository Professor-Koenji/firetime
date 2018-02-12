package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvexBody implements IComponent {

  // The vertices of the convex shape
  public List<PVector> vertices;

  // The approximate size of this body
  // Used in broad-phase collision checks
  public int size;

  // is Static
  public boolean isStatic;

  public static ConvexBody square(float size) {
    return square(size, 0, 0);
  }

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

  public static ConvexBody polygon(int sides, float size) {
    return polygon(sides, size, 0, 0);
  }

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
   * Creates a new ConvexBody with the given vertices
   * @param size The approximate size of the body. Always over-estimate.
   * @param vertices A list of points for the vertices of the shape
   */
  public ConvexBody(int size, PVector ...vertices) {
    this(size, false, vertices);
  }

  public ConvexBody(int size, boolean isStatic, PVector ...vertices) {
    this.size = size;
    this.isStatic = isStatic;
    this.vertices = new ArrayList<>(Arrays.asList(vertices));
  }

  /**
   * Gets a list of the edges of the shape as vectors between vertices
   * @return A list of edge vectors
   */
  public List<PVector> edges() {
    return edges(null);
  }

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

  public List<PVector> rotatedVertices(Rotation r) {
    if (r == null) return vertices;
    List<PVector> rots = new ArrayList<>();
    for (PVector v : vertices) {
      rots.add(rotate(v, r.angle));
    }
    return rots;
  }

  private PVector rotate(PVector v, float angle) {
    float x = v.x * (float) Math.cos(angle) - v.y * (float) Math.sin(angle);
    float y = v.x * (float) Math.sin(angle) + v.y * (float) Math.cos(angle);
    return new PVector(x, y);
  }

  public void setStatic(boolean isStatic) {
    this.isStatic = isStatic;
  }

}
