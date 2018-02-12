package com.koenji.firetime.entity.game;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.scene.IScene;
import processing.core.PVector;

public class Bullet extends Entity {

  public Bullet(PVector pos, PVector vel) {
    addComponents(
      new Position(pos),
      new Velocity(vel),
      new CircleBody(4),
      ConvexBody.polygon(8, 4),
      new InverseMass(.001f),
      new RenderCircle(4, 0xFFFF0000)
    );
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    addComponents(new BoundingBox(BoundingBox.WRAP, 0, 0, scene.gc().getWidth(), scene.gc().getHeight()));
  }
}
