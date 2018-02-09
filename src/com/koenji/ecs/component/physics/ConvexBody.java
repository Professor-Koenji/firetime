package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvexBody implements IComponent {

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

  public ConvexBody(PVector ...vertices) {
    this.vertices = new ArrayList<>(Arrays.asList(vertices));
  }

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
