package com.koenji.ecs.system.physics;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.graph.tree.IQuadTree;
import com.koenji.ecs.graph.tree.IRect;
import com.koenji.ecs.graph.tree.QuadTree;
import com.koenji.ecs.graph.tree.Rect;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.system.System;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class ConvexCollider extends System {

  private IQuadTree quadTree;

  @Override
  public void added(IScene scene, IEventController eventController) {
    super.added(scene, eventController);
    //
    quadTree = new QuadTree(new Rect(scene.gc().getWidth(), scene.gc().getHeight()), 10, 5);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void entityAdded(IEntity entity) {
    // Only those with Position & ConvexBody
    if (entity.hasComponents(Position.class, ConvexBody.class)) {
      entities.add(entity);
    }
  }

  @Override
  public void update(int dt) {
    super.update(dt);

    // Setup the quadtree
    List<ConvexEntity> shapes = new ArrayList<>();
    quadTree.clear();
    for (IEntity e : entities) {
      ConvexEntity ce = new ConvexEntity(e);
      shapes.add(ce);
      quadTree.insert(ce);
    }

    for (ConvexEntity ce : shapes) {
      // Pre-fetch some stuff
      IEntity a = ce.getEntity();
      // Get the position, body & edges
      Position pA = a.getComponent(Position.class);
      ConvexBody bA = a.getComponent(ConvexBody.class);
      Rotation rA = a.getComponent(Rotation.class);
      List<PVector> edgesA = bA.edges(rA);

      // Get nearby convex entities
      List<ConvexEntity> nearby = quadTree.retrieve(ce);
      for (ConvexEntity nce : nearby) {
        IEntity b = nce.getEntity();
        // Get the position, body & edges
        Position pB = b.getComponent(Position.class);
        ConvexBody bB = b.getComponent(ConvexBody.class);

        /*
         Broad-phase collision check
         */
        // Are both ConvexBody's static, if so just skip!
        // Static bodies cannot have collision response with each other
        if (bA.isStatic && bB.isStatic) continue;

        // Get the 2nd bodies edges by this point, as we will need them
        Rotation rB = b.getComponent(Rotation.class);
        List<PVector> edgesB = bB.edges(rB);

        // Offload all the data to our collision resolver
        /*
         * Narrow-phase checks
         */
        collisionCheck(a, b, pA, bA, edgesA, pB, bB, edgesB);
      }
    }
  }

  /**
   * Resolves collisions between two given convexbodies and their positions
   * @param a The first IEntity
   * @param b The second IEntity
   * @param pA The position of shape 1
   * @param bA The convex body of shape 1
   * @param edgesA The edges of shape 1
   * @param pB The position of shape 2
   * @param bB The convex body of shape 2
   * @param edgesB The edges of shape 2
   */
  private void collisionCheck(IEntity a, IEntity b, Position pA, ConvexBody bA, List<PVector> edgesA,
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
      Rotation rA = a.getComponent(Rotation.class);
      float[] s1 = project(axis, bA.rotatedVertices(rA), pA);
      // Projection range of the second body upon the axis
      Rotation rB = b.getComponent(Rotation.class);
      float[] s2 = project(axis, bB.rotatedVertices(rB), pB);
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
      Velocity vA = a.getComponent(Velocity.class);
      Velocity vB = b.getComponent(Velocity.class);
      // Set the magnitude of the mtv to our overlap / 2 (as each will move half the mtv)
      mtv.setMag(minOverlap * .5f);
      // Get our displacement vector between the two positions
      PVector displacement = PVector.sub(pB, pA);
      // Project the displacement upon our minimum translation vector
      float dot = mtv.dot(displacement);
      // If the projection was 'backwards', then mtv essentially is reversed
      if (dot >= 0) mtv.mult(-1);
      if (!bA.isStatic) {
        pA.add(mtv);
        if (vA != null) vA.add(PVector.mult(mtv, 2));
      }
      if (!bB.isStatic) {
        pB.sub(mtv);
        if (vB != null) vB.sub(PVector.mult(mtv, 2));
      }
    }
  }

  /**
   * Projects a given ConvexBody upon an axis, returning the projection range
   * @param axis The axis on which to project the shape upon
   * @param vertices The shape to perform the axis projection
   * @param offset An offset of the shapes vertices (global position)
   * @return A projection range of [min, max] projection values
   */
  private float[] project(PVector axis, List<PVector> vertices, PVector offset) {
    // Get the min/max initial from the projection of the first vertex
    float first = axis.dot(PVector.add(vertices.get(0), offset));
    float[] ret = {first, first};

    // Project every other vertex in the shape
    for (int i = 1; i < vertices.size(); ++i) {
      float p = axis.dot(PVector.add(vertices.get(i), offset));
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

  private class ConvexEntity implements IRect {

    private IEntity entity;

    public ConvexEntity(IEntity entity) {
      this.entity = entity;
    }

    @Override
    public float getX() {
      return entity.getComponent(Position.class).x;
    }

    @Override
    public float getY() {
      return entity.getComponent(Position.class).y;
    }

    @Override
    public float getW() {
      return entity.getComponent(ConvexBody.class).size;
    }

    @Override
    public float getH() {
      return getW();
    }

    public IEntity getEntity() {
      return entity;
    }
  }
}
