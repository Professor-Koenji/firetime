package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.firetime.components.CanKill;
import processing.core.PVector;

public class Bullet extends Entity {

  private float x;
  private float y;
  private float angle;
  private IGraphicsContext gc;

  public Bullet(float x, float y, float angle) {
    this.x = x;
    this.y = y;
    this.angle = angle;
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    gc = Locator.get(IGraphicsContext.class);
    //
    ConvexBody cb = ConvexBody.polygon(8, 10);
    addComponents(
      new Position(x, y),
      new Velocity(PVector.fromAngle(angle).setMag(10f)),
      new CircleBody(8),
      cb,
//      new RenderCircle(8, 0x99FF3322),
      new RenderPolygon(
        0x99FF3322,
        new PVector(-8, -4),
        new PVector(8, -4),
        new PVector(8, 4),
        new PVector(-8, 4)
      ),
      new CanKill()
    );
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //d
    Velocity v = getComponent(Velocity.class);
    addComponent(new Rotation(v.heading()));
    if (v.mag() < 4f) {
      scene.remove(this);
    }
  }
}
