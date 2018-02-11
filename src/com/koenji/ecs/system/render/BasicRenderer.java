package com.koenji.ecs.system.render;

import com.koenji.ecs.Core;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.render.*;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.system.System;
import processing.core.PVector;

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
      Stroke stroke = e.getComponent(Stroke.class);

      if (e.hasComponents(Background.class)) {
        Background b = e.getComponent(Background.class);
        core.noStroke();
        core.fill(b.rgba);
        core.rect(0, 0, core.getWidth(), core.getHeight());
      }

      if (e.hasComponents(Position.class, RenderPolygon.class)) {
        Position p = e.getComponent(Position.class);
        RenderPolygon rc = e.getComponent(RenderPolygon.class);
        core.pushMatrix();
        core.translate(p.x, p.y);
        core.fill(rc.rgba);
        if (stroke != null) {
          core.strokeWeight(stroke.weight);
          core.stroke(stroke.rgba);
        } else {
          core.noStroke();
        }
        core.beginShape();
        for (PVector v : rc.vertices) core.vertex(v.x, v.y);
        core.endShape(core.CLOSE);
        core.popMatrix();
      }

      if (e.hasComponents(Position.class, RenderCircle.class)) {
        Position p = e.getComponent(Position.class);
        RenderCircle rc = e.getComponent(RenderCircle.class);
        core.fill(rc.rgba);
        if (stroke != null) {
          core.strokeWeight(stroke.weight);
          core.stroke(stroke.rgba);
        } else {
          core.noStroke();
        }
        core.arc(p.x, p.y, rc.r*2, rc.r*2, 0, core.TWO_PI);
      }

      if (e.hasComponents(Position.class, RenderLine.class)) {
        Position p = e.getComponent(Position.class);
        RenderLine rl = e.getComponent(RenderLine.class);
        core.strokeWeight(rl.weight);
        core.stroke(rl.rgba);
        core.line(p.x, p.y, rl.to.x, rl.to.y);
      }
    }
  }
}
