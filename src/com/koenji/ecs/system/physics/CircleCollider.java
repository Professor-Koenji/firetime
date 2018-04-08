package com.koenji.ecs.system.physics;

import com.koenji.ecs.component.physics.InverseMass;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.event.PhysicsEvents;
import com.koenji.ecs.event.events.CollisionEvent;
import com.koenji.ecs.graph.tree.IQuadTree;
import com.koenji.ecs.graph.tree.IRect;
import com.koenji.ecs.graph.tree.QuadTree;
import com.koenji.ecs.graph.tree.Rect;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.System;
import com.koenji.ecs.component.physics.CircleBody;
import com.koenji.ecs.wrappers.IGraphicsContext;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class CircleCollider extends System {

  private IQuadTree qt;

  /**
   * Creates a new CircleCollider using the default quadtree implementation.
   */
  public CircleCollider() {
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);
    this.qt = new QuadTree(new Rect(gc.getWidth(), gc.getHeight()), 10, 5);
  }

  /**
   * Creates a new CircleCollider using the provided quadtree implementation
   * @param qt The custom quadtree to use
   */
  public CircleCollider(IQuadTree qt) {
    this.qt = qt;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void entityAdded(IEntity entity) {
    // Only add if components are present
    if (entity.hasComponents(Position.class, CircleBody.class, Velocity.class)) {
      entities.add(entity);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void update(int dt) {
    super.update(dt);
    // Construct the quad tree!
    List<CircleEntity> circles = new ArrayList<>();
    // Clear the quadtree each cycle
    qt.clear();
    for (IEntity e : entities) {
      CircleEntity ce = new CircleEntity(e);
      circles.add(ce);
      // Populate our quadtree
      qt.insert(ce);
    }
    //
    for (CircleEntity ce : circles) {
      // Get nearby circles from quad tree
      List<CircleEntity> nearbyCircles = qt.retrieve(ce);
      for (CircleEntity nc : nearbyCircles) {
        // Check that we aren't ourself
        if (ce == nc) continue;
        // Collision check these entities from CircleEntity struct
        collisionCheck(ce.getEntity(), nc.getEntity());
      }
    }
  }

  private void collisionCheck(IEntity a, IEntity b) {
    // Get collision overlap
    // +ve = Colliding
    // 0 or -ve = Not Colliding
    float dist = collisionDistance(
      a.getComponent(Position.class),
      b.getComponent(Position.class),
      a.getComponent(CircleBody.class),
      b.getComponent(CircleBody.class)
    );

    if (dist > 0) {
      collisionResponse(a, b, dist);
    }
  }

  private float collisionDistance(Position aP, Position bP, CircleBody aC, CircleBody bC) {
    float dist = aP.dist(bP);
    return aC.r + bC.r - dist;
  }

  private void collisionResponse(IEntity e, IEntity e2, float dist) {
    // Get our entity components needed for our collider
    Position pA = e.getComponent(Position.class);
    Velocity vA = e.getComponent(Velocity.class);
    InverseMass imA = e.getComponent(InverseMass.class);
    Position pB = e2.getComponent(Position.class);
    Velocity vB = e2.getComponent(Velocity.class);
    InverseMass imB = e2.getComponent(InverseMass.class);

    // Get the unit collision normal between our circles
    PVector cn = PVector.sub(pA, pB).normalize();

    // Calculate our Minimum Translation Vector (for each Circle)
    PVector mtv = PVector.mult(cn, dist).mult(.5f);
    // Add the mtv to our first entity
    pA.add(mtv);
    // Subtract from the second (to seperate them, rahter than translate them together)
    pB.sub(mtv);

    // Calculate the closing velocities with respect to the collision normal
    float cvA = vA.dot(cn);
    float cvB = vB.dot(cn);

    // Calculate the partial final velocities by multiplying the closing velocities by the collision normal
    PVector fcvA1 = PVector.mult(cn, cvB);
    PVector fcvA2 = PVector.mult(cn, cvA);

    // Calculate the final closing velocities from the partials
    PVector fcvA = PVector.sub(fcvA2, fcvA1);
    PVector fcvB = PVector.sub(fcvA1, fcvA2);

    // Fudge-factor with InverseMass to simulate elastic collisions
    if (imB != null) fcvA.mult(.95f - imB.inverseMass);
    if (imA != null) fcvB.mult(.95f - imA.inverseMass);

    // Add the final velocities to our circles
    vA.add(fcvB);
    vB.add(fcvA);

    // Fires a collision event
    Locator.get(IEventBus.class).fireEvent(new CollisionEvent(PhysicsEvents.CIRCLE_COLLISION, e, e2));
  }

  // Struct class for working with QuadTree
  private class CircleEntity implements IRect {

    private IEntity entity;

    private CircleEntity(IEntity entity) {
      this.entity = entity;
    }

    @Override
    public float getX() {
      return entity.getComponent(Position.class).x - entity.getComponent(CircleBody.class).r;
    }

    @Override
    public float getY() {
      return entity.getComponent(Position.class).y - entity.getComponent(CircleBody.class).r;
    }

    @Override
    public float getW() {
      return entity.getComponent(CircleBody.class).r * 2;
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


