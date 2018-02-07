package com.koenji.ecs.system.physics;

import com.koenji.ecs.Core;
import com.koenji.ecs.component.physics.InverseMass;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.system.System;
import com.koenji.ecs.component.physics.Circle;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class CircleCollider extends System {

  private Core core;

  @Override
  public void added(IScene scene) {
    super.added(scene);
    this.core = scene.gc();
  }

  @Override
  public void update(Iterable<IEntity> entities, int dt) {
    super.update(entities, dt);
    //

    List<IEntity> circles = new ArrayList<>();

    for (IEntity e : entities) {
      if (e.hasComponents(Position.class, Circle.class, Velocity.class, InverseMass.class)) {
        circles.add(e);
      }
    }

    //
    for (int i = 0; i < circles.size() - 1; ++i) {
      IEntity a = circles.get(i);
      for (int j = i + 1; j < circles.size(); ++j) {
        IEntity b = circles.get(j);

        //
        float dist = collisionDistance(
          a.getComponent(Position.class),
          b.getComponent(Position.class),
          a.getComponent(Circle.class),
          b.getComponent(Circle.class)
        );

        if (dist > 0) {
          collisionResponse(a, b, dist);
        }

      }
    }
  }

  private float collisionDistance(Position aP, Position bP, Circle aC, Circle bC) {
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

    PVector mtv = PVector.mult(cn, dist);
    pA.add(mtv);
    pB.sub(mtv);

    float cvA = vA.dot(cn);
    float cvB = vB.dot(cn);

    core.stroke(0xFFFF3333);
    core.strokeWeight(8f);
    core.line(pA.x, pA.y, pB.x, pB.y);

    PVector fcvA1 = PVector.mult(cn, cvB);
    PVector fcvA2 = PVector.mult(cn, cvA);

    PVector fcvA = PVector.sub(fcvA2, fcvA1).mult(.6f);
    PVector fcvB = PVector.sub(fcvA1, fcvA2).mult(.6f);

    vA.add(fcvB);
    vB.add(fcvA);
  }
}
