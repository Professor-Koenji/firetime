package com.koenji.ecs.system.physics;

import com.koenji.ecs.component.physics.InverseMass;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.event.IEventController;
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

  private IQuadTree quadTree;

  @Override
  public void added(IScene scene, IEventController eventController) {
    super.added(scene, eventController);
    //
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);
    //
    quadTree = new QuadTree(new Rect(gc.getWidth(), gc.getHeight()), 10, 5);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void entityAdded(IEntity entity) {
    // Only add if components are present
    if (entity.hasComponents(Position.class, CircleBody.class, Velocity.class, InverseMass.class)) {
      entities.add(entity);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void update(int dt) {
    super.update(dt);
    // Construct the quad tree!
    List<CircleEntity> circles = new ArrayList<>();
    quadTree.clear();
    for (IEntity e : entities) {
      CircleEntity ce = new CircleEntity(e);
      circles.add(ce);
      quadTree.insert(ce);
    }
    //
    for (CircleEntity ce : circles) {
      List<CircleEntity> nearbyCircles = quadTree.retrieve(ce);
      for (CircleEntity nc : nearbyCircles) {
        collisionCheck(ce.getEntity(), nc.getEntity());
      }
    }
  }

  private void collisionCheck(IEntity a, IEntity b) {
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
    Position pA = e.getComponent(Position.class);
    Velocity vA = e.getComponent(Velocity.class);
    InverseMass imA = e.getComponent(InverseMass.class);
    Position pB = e2.getComponent(Position.class);
    Velocity vB = e2.getComponent(Velocity.class);
    InverseMass imB = e2.getComponent(InverseMass.class);

    PVector cn = PVector.sub(pA, pB).normalize();

    PVector mtv = PVector.mult(cn, dist).mult(.5f);
    pA.add(mtv);
    pB.sub(mtv);

    float cvA = vA.dot(cn);
    float cvB = vB.dot(cn);

    PVector fcvA1 = PVector.mult(cn, cvB);
    PVector fcvA2 = PVector.mult(cn, cvA);

    PVector fcvA = PVector.sub(fcvA2, fcvA1).mult(.95f - imB.inverseMass);
    PVector fcvB = PVector.sub(fcvA1, fcvA2).mult(.95f - imA.inverseMass);

    vA.add(fcvB);
    vB.add(fcvA);
  }

  private class CircleEntity implements IRect {

    private IEntity entity;

    public CircleEntity(IEntity entity) {
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


