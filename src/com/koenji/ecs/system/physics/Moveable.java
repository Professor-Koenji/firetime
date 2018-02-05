package com.koenji.ecs.system.physics;

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
      // Position & Velocity
      if (e.hasComponents(Position.class, Velocity.class)) {
        Position p = (Position) e.getComponent(Position.class);
        Velocity v = (Velocity) e.getComponent(Velocity.class);

        p.add(v);
      }
    }
  }
}
