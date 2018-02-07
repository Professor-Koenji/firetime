package com.koenji.ecs.system.render;

import com.koenji.ecs.Core;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.system.System;
import processing.opengl.PShader;

public class BasicRenderer extends System {

  private Core core;

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    this.core = scene.gc();
  }

  @Override
  @SuppressWarnings("unchecked")
  public void update(Iterable<IEntity> entities, int dt) {
    super.update(entities, dt);
    //
    for (IEntity e : entities) {
      if (e.hasComponents(Background.class)) {
        Background b = e.getComponent(Background.class);
        core.noStroke();
        core.fill(b.rgba);
        core.rect(0, 0, core.getWidth(), core.getHeight());
      }

      if (e.hasComponents(Position.class, RenderCircle.class)) {
        Position p = e.getComponent(Position.class);
        RenderCircle rc = e.getComponent(RenderCircle.class);
        core.noStroke();
        core.fill(rc.rgba);
        core.arc(p.x, p.y, rc.r*2, rc.r*2, 0, core.TWO_PI);
      }
    }
  }
}
