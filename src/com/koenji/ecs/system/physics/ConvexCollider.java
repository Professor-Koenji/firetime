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
    // A list of convex objects
    List<IEntity> convex = new ArrayList<>();
    // Filter out any non-convex (Pos, ConBody) entities
    for (IEntity e : entities) {
      if (e.hasComponents(Position.class, ConvexBody.class)) convex.add(e);
    }
    // Loop thru all but last convex entity
    for (int i = 0; i < convex.size() - 1; ++i) {
      IEntity a = convex.get(i);
      // Get the position, body & edges
      Position pA = a.getComponent(Position.class);
      ConvexBody bA = a.getComponent(ConvexBody.class);
      List<PVector> edgesA = bA.edges();

      for (int j = i + 1; j < convex.size(); ++j) {
        IEntity b = convex.get(j);
        // Get the position, body & edges
        Position pB = b.getComponent(Position.class);
        ConvexBody bB = b.getComponent(ConvexBody.class);
        List<PVector> edgesB = bB.edges();

        // Offload all the data to our collision resolver
        collisionResolver(pA, bA, edgesA, pB, bB, edgesB);
      }
    }
  }

  /**
   * Resolves collisions between two given convexbodies and their positions
   * @param pA The position of shape 1
   * @param bA The convex body of shape 1
   * @param edgesA The edges of shape 1
   * @param pB The position of shape 2
   * @param bB The convex body of shape 2
   * @param edgesB The edges of shape 2
   */
  private void collisionResolver(Position pA, ConvexBody bA, List<PVector> edgesA,
                                    Position pB, ConvexBody bB, List<PVector> edgesB) {
    // Do SAT collision checks!
    List<PVector> edges = new ArrayList<>();
    // Add all edges into our edge list
    edges.addAll(edgesA);
    edges.addAll(edgesB);
    // Our mtv doesn't exist yet (as we don't even know if we have a collision)
    PVector mtv = null;
    // Our minimum vector overlap (Set high initially)
    float minOverlap = 9999;
    // For every edge in the ConvexBodies of our entities
    for (PVector edge : edges) {
      // Get the normal axis of our edge
      //noinspection SuspiciousNameCombination
      PVector axis = new PVector(edge.y, -edge.x).normalize();
      // Get the projection range of the first body upon the axis
      float[] s1 = project(axis, bA, pA);
      // Projection range of the second body upon the axis
      float[] s2 = project(axis, bB, pB);
      // If these projections dont overlap, then we have no collision, and return.
      if (overlap(s1, s2)) return;
      // Else we may have a collision, so get the collision overlap
      float overlap = overlapAmount(s1, s2);
      // If the overlap is smaller than any other overlap seen so far, then set it to the new mtv candidate
      if (overlap < minOverlap) {
        mtv = axis;
        minOverlap = overlap;
      }
    }
    // Wow, we have a collision!
    // Only can get here if no seperating axis was found = collision
    // Translate shapes by MTV (if it exists, just to make IntelliJ happy)
    if (mtv != null) {
      // Set the magnitude of the mtv to our overlap / 2 (as each will move half the mtv)
      mtv.setMag(minOverlap * .5f);
      // Get our displacement vector between the two positions
      PVector displacement = PVector.sub(pB, pA);
      // Project the displacement upon our minimum translation vector
      float dot = mtv.dot(displacement);
      // If the projection was 'backwards', then mtv essentially is reversed
      if (dot < 0 ) {
        pA.add(mtv);
        pB.sub(mtv);
      } else {
        // Else the mtv is normal, and sub 1st shape and add 2nd shape
        pA.sub(mtv);
        pB.add(mtv);
      }
    }
  }

  /**
   * Projects a given ConvexBody upon an axis, returning the projection range
   * @param axis The axis on which to project the shape upon
   * @param shape The shape to perform the axis projection
   * @param offset An offset of the shapes vertices (global position)
   * @return A projection range of [min, max] projection values
   */
  private float[] project(PVector axis, ConvexBody shape, PVector offset) {
    // Get the min/max initial from the projection of the first vertex
    float first = axis.dot(PVector.add(shape.vertices.get(0), offset));
    float[] ret = {first, first};

    // Project every other vertex in the shape
    for (int i = 1; i < shape.vertices.size(); ++i) {
      float p = axis.dot(PVector.add(shape.vertices.get(i), offset));
      // min/max the projection values
      if (p < ret[0]) ret[0] = p;
      if (p > ret[1]) ret[1] = p;
    }

    // Return the projected range
    return ret;
  }

  /**
   * Whether the two ranges are overlapping
   * @param r1 The projection range of shape 1
   * @param r2 The projection range of shape 2
   * @return True if overlap is occuring, else false (a SA exists)
   */
  private boolean overlap(float[] r1, float[] r2) {
    return Math.max(r1[0], r2[0]) > Math.min(r1[1], r2[1]);
  }

  /**
   * Gets the total overlap as a scalar from the projection ranges
   * @param r1 The projection range of shape 1
   * @param r2 The projection range of shape 2
   * @return The total overlap between the two ranges
   */
  private float overlapAmount(float[] r1, float[] r2) {
    return Math.min(r1[1], r2[1]) - Math.max(r1[0], r2[0]);
  }
}