package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.CircleBody;
import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventController;
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
  public void added(IScene scene, IEventController eventController) {
    super.added(scene, eventController);
    //
    gc = Locator.get(IGraphicsContext.class);
    //
    ConvexBody cb = ConvexBody.polygon(8, 10);
    addComponents(
      new Position(x, y),
      new Velocity(PVector.fromAngle(angle).setMag(10f)),
      new CircleBody(8),
      cb,
      new RenderCircle(8, 0xFF3322FF),
      new CanKill()
    );
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    Velocity v = getComponent(Velocity.class);
    if (v.mag() < 4f) {
      scene.remove(this);
    }
  }
}
