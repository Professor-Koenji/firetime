package com.koenji.firetime.systems;

import com.koenji.ecs.Core;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.system.System;
import com.koenji.firetime.components.DebugDraw;
import com.koenji.firetime.components.Position;

public class Renderer extends System {
  public void update(Iterable<IEntity> entities, int dt) {
    // Get the drawing context
    Core gc = scene.gc();
    // Loop thru each entity, and draw it
    for (IEntity entity : entities) {
      if (entity.hasComponents(Position.class, DebugDraw.class)) {
        Position p = (Position) entity.getComponent(Position.class);
        DebugDraw dd = (DebugDraw) entity.getComponent(DebugDraw.class);

        gc.fill(dd.rgba);
        gc.rect(p.x, p.y, dd.w, dd.h);
      }
    }
    gc.fill(0xFFFFFFFF);
    gc.text("FPS: " + gc.frameRate, 8, 8);
  }
}
