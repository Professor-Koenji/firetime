package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.scene.IScene;
import processing.core.PVector;

public class Wall extends Entity {

  private float x;
  private float y;
  private float w;
  private float h;

  public Wall(float x, float y, float w, float h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  @Override
  public void added(IScene scene, IEventController eventController) {
    super.added(scene, eventController);
    //
    ConvexBody cb = new ConvexBody((int) Math.sqrt(w*w + h*h),
      new PVector(-w/2f, -h/2f),
      new PVector(w/2f, -h/2f),
      new PVector(w/2f, h/2f),
      new PVector(-w/2f, h/2f)
    );
    cb.isStatic = true;
    addComponents(
      new Position(x, y),
      cb,
      new RenderPolygon(cb, 0xFFFFFFFF)
    );
  }
}