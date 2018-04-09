package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.CircleBody;
import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;
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
      new Velocity(PVector.fromAngle(angle).setMag(5f)),
      new CircleBody(8),
      cb,
      new RenderCircle(8, 0xFF3322FF)
    );
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    Position p = getComponent(Position.class);
    if (p.x < -4 || p.x > gc.getWidth() + 4 || p.y < -4 || p.y > gc.getHeight() + 4) {
      scene.remove(this);
    }
  }
}
