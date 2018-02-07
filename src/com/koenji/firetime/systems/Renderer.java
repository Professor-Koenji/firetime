package com.koenji.firetime.systems;

import com.koenji.ecs.Core;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.system.System;
import com.koenji.ecs.component.physics.Circle;
import com.koenji.ecs.component.physics.Position;

public class Renderer extends System {
  public void update(Iterable<IEntity> entities, int dt) {
    // Get the drawing context
    Core gc = scene.gc();
    // Loop thru each entity, and draw it
    for (IEntity entity : entities) {
      if (entity.hasComponents(Position.class, Circle.class)) {
        Position p = entity.getComponent(Position.class);
        Circle c = entity.getComponent(Circle.class);

        gc.noStroke();
        gc.fill(0x30FFFFFF);
        gc.arc(p.x, p.y, c.r * 2, c.r * 2, 0, 6.28f);
      }
    }
    gc.fill(0xFFFFFFFF);
    gc.text("FPS: " + gc.frameRate, 8, 8);
  }
}
