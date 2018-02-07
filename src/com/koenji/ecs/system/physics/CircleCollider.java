package com.koenji.ecs.system.physics;

import com.koenji.ecs.component.physics.InverseMass;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.system.System;
import com.koenji.ecs.component.physics.CircleBody;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class CircleCollider extends System {

  @Override
  @SuppressWarnings("unchecked")
  public void update(Iterable<IEntity> entities, int dt) {
    super.update(entities, dt);
    //

    List<IEntity> circles = new ArrayList<>();

    for (IEntity e : entities) {
      if (e.hasComponents(Position.class, CircleBody.class, Velocity.class, InverseMass.class)) {
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
          a.getComponent(CircleBody.class),
          b.getComponent(CircleBody.class)
        );

        if (dist > 0) {
          collisionResponse(a, b, dist);
        }

      }
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
}
