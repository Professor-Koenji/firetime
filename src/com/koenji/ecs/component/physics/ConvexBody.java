package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvexBody implements IComponent {

  // The vertices of the convex shape
  public List<PVector> vertices;

  public static ConvexBody square(float size) {
    return square(size, 0, 0);
  }

  public static ConvexBody square(float size, float offsetX, float offsetY) {
    return new ConvexBody(
      new PVector(offsetX, offsetY),
      new PVector(offsetX + size, offsetY),
      new PVector(offsetX + size, offsetY + size),
      new PVector(offsetX, offsetY + size)
    );
  }

  /**
   * Creates a new ConvexBody with the given vertices
   * @param vertices A list of points for the vertices of the shape
   */
  public ConvexBody(PVector ...vertices) {
    this.vertices = new ArrayList<>(Arrays.asList(vertices));
  }

  /**
   * Gets a list of the edges of the shape as vectors between vertices
   * @return A list of edge vectors
   */
  public List<PVector> edges() {
    List<PVector> es = new ArrayList<>();
    for (int i = 0; i < vertices.size(); ++i) {
      PVector a = vertices.get(i);
      PVector b = vertices.get((i + 1) % vertices.size());
      es.add(PVector.sub(b, a));
    }
    return es;
  }

}
