package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Rotation;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.component.render.Stroke;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.scene.IScene;
import processing.core.PVector;

public class Wall extends Entity {

  private float x;
  private float y;
  private float w;
  private float h;
  private float angle;

  public Wall(float x, float y, float w, float h, float angle) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.angle = angle;
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);
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
      new Rotation(angle),
      cb,
      new RenderPolygon(cb, 0xFFFFFFFF)
    );
  }
}
