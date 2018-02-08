package com.koenji.ecs.component.physics;

import com.koenji.ecs.component.IComponent;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvexBody implements IComponent {

  public List<PVector> vertices;

  public ConvexBody(PVector ...vertices) {
    this.vertices = new ArrayList<>();
    this.vertices.addAll(Arrays.asList(vertices));
  }

  public List<PVector> verts(PVector offset) {
    List<PVector> vs = new ArrayList<>();
    for (PVector v : vertices) {
      vs.add(PVector.add(v, offset));
    }
    return vs;
  }

  public List<PVector> normals(PVector offset) {
    List<PVector> ns = new ArrayList<>();
    for (int i = 0; i < vertices.size(); i += 2) {
      PVector vertex = PVector.sub(vertices.get(i + 1), vertices.get(i));
      vertex.add(offset);
      //noinspection SuspiciousNameCombination
      ns.add(new PVector(-vertex.y, vertex.x).normalize());
    }
    PVector finalVertex = PVector.sub(vertices.get(0), vertices.get(vertices.size() - 1));
    finalVertex.add(offset);
    //noinspection SuspiciousNameCombination
    ns.add(new PVector(-finalVertex.y, finalVertex.x).normalize());
    return ns;
  }

}
