package com.koenji.ecs.system.physics;

import com.koenji.ecs.component.physics.Acceleration;
import com.koenji.ecs.component.physics.Friction;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.system.System;

public class Moveable extends System {

  @Override
  public void update(Iterable<IEntity> entities, int dt) {
    super.update(entities, dt);
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
    }
  }
}
