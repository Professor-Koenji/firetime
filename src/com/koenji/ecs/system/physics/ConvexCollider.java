package com.koenji.ecs.system.physics;

import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.system.System;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class ConvexCollider extends System {

  @Override
  public void update(Iterable<IEntity> entities, int dt) {
    super.update(entities, dt);
    //
    List<IEntity> convex = new ArrayList<>();
    //
    for (IEntity e : entities) {
      if (e.hasComponents(Position.class, ConvexBody.class)) convex.add(e);
    }
    //
    for (int i = 0; i < convex.size() - 1; ++i) {
      IEntity a = convex.get(i);
      Position pA = a.getComponent(Position.class);
      ConvexBody bA = a.getComponent(ConvexBody.class);
      List<PVector> edgesA = bA.edges();

      for (int j = i + 1; j < convex.size(); ++j) {
        IEntity b = convex.get(j);
        Position pB = b.getComponent(Position.class);
        ConvexBody bB = b.getComponent(ConvexBody.class);
        List<PVector> edgesB = bB.edges();

        collisionResolver(pA, bA, edgesA, pB, bB, edgesB);
      }
    }
  }

  private void collisionResolver(Position pA, ConvexBody bA, List<PVector> edgesA,
                                    Position pB, ConvexBody bB, List<PVector> edgesB) {
    // Do SAT collision checks!
    List<PVector> edges = new ArrayList<>();
    edges.addAll(edgesA);
    edges.addAll(edgesB);
    PVector mtv = null;
    float minOverlap = 9999;
    for (PVector edge : edges) {
      //noinspection SuspiciousNameCombination
      PVector normal = new PVector(edge.y, -edge.x).normalize();
      float[] s1 = project(normal, bA, pA);
      float[] s2 = project(normal, bB, pB);

      if (overlap(s1, s2)) return;
      float overlap = overlapAmount(s1, s2);
      if (overlap < minOverlap) {
        mtv = normal;
        minOverlap = overlap;
      }
    }
    // Wow, we have a collision!
    // Translate shapes by MTV (if it exists)
    if (mtv != null) {
      mtv.setMag(minOverlap * .5f);
      PVector displacement = PVector.sub(pB, pA);
      float dot = mtv.dot(displacement);
      if (dot < 0 ) {
        pA.add(mtv);
        pB.sub(mtv);
      } else {
        pA.sub(mtv);
        pB.add(mtv);
      }
    }
  }

  private float[] project(PVector axis, ConvexBody shape, PVector offset) {
    float first = axis.dot(PVector.add(shape.vertices.get(0), offset));
    float[] ret = {first, first};

    for (int i = 1; i < shape.vertices.size(); ++i) {
      float p = axis.dot(PVector.add(shape.vertices.get(i), offset));
      if (p < ret[0]) ret[0] = p;
      if (p > ret[1]) ret[1] = p;
    }

    return ret;
  }

  private boolean overlap(float[] r1, float[] r2) {
    return Math.max(r1[0], r2[0]) > Math.min(r1[1], r2[1]);
  }

  private float overlapAmount(float[] r1, float[] r2) {
    return Math.min(r1[1], r2[1]) - Math.max(r1[0], r2[0]);
  }
}
