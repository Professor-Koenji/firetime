package com.koenji.ecs.system.physics;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.system.System;

public class LinearMotion extends System {

  @Override
  @SuppressWarnings("unchecked")
  public void update(int dt) {
    super.update(dt);
    //
    for (IEntity e : entities) {
      // Velocity & Friction
      if (e.hasComponents(Velocity.class, Friction.class)) {
        Velocity v = e.getComponent(Velocity.class);
        Friction f = e.getComponent(Friction.class);
        v.x *= f.x;
        v.y *= f.y;
      }

      // Velocity & Acceleration
      if (e.hasComponents(Velocity.class, Acceleration.class)) {
        Velocity v = e.getComponent(Velocity.class);
        Acceleration a = e.getComponent(Acceleration.class);
        v.add(a);
      }

      // Position & Velocity
      if (e.hasComponents(Position.class, Velocity.class)) {
        Position p = e.getComponent(Position.class);
        Velocity v = e.getComponent(Velocity.class);
        p.add(v);
      }

      // Reset acceleration
      if (e.hasComponents(Acceleration.class)) {
        Acceleration a = e.getComponent(Acceleration.class);
        if (e.hasComponents(Gravity.class)) {
          Gravity g = e.getComponent(Gravity.class);
          a.set(g);
        } else {
          a.set(0, 0);
        }
      }

      // Bounding box stuff
      if (e.hasComponents(Position.class, Velocity.class, BoundingBox.class)) {
        Position p = e.getComponent(Position.class);
        Velocity v = e.getComponent(Velocity.class);
        BoundingBox b = e.getComponent(BoundingBox.class);

        if (p.x < b.position.x) {
          switch(b.type) {
            case BoundingBox.REFLECT:
              p.x = b.position.x;
              v.x *= -1;
              break;
            case BoundingBox.WRAP:
              p.x = b.position.x + b.size.x;
              break;
          }
        } else if (p.x > b.position.x + b.size.x) {
          switch(b.type) {
            case BoundingBox.REFLECT:
              p.x = b.position.x + b.size.x;
              v.x *= -1;
              break;
            case BoundingBox.WRAP:
              p.x = b.position.x;
              break;
          }
        }

        if (p.y < b.position.y) {
          switch(b.type) {
            case BoundingBox.REFLECT:
              p.y = b.position.y;
              v.y *= -1;
              break;
            case BoundingBox.WRAP:
              p.y = b.position.y + b.size.y;
              break;
          }
        } else if (p.y > b.position.y + b.size.y) {
          switch(b.type) {
            case BoundingBox.REFLECT:
              p.y = b.position.y + b.size.y;
              v.y *= -1;
              break;
            case BoundingBox.WRAP:
              p.y = b.position.y;
              break;
          }
        }
      }
    }
  }
}
