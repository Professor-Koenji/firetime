package com.koenji.firetime.entity.game;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.scene.IScene;
import processing.core.PVector;

public class Wall extends Entity {

  private Rotation rot;

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    ConvexBody wall = new ConvexBody(
      250,
      new PVector(-200, -20),
      new PVector(200, -20),
      new PVector(200, 20),
      new PVector(-200, 20)
    );
    wall.isStatic = true;
    addComponents(
      new Position(300, 400),
      new Velocity(),
      new Acceleration(),
      rot = new Rotation(0f),
      wall,
      new RenderPolygon(wall, 0xFF3399CC)
    );
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    rot.angle += .01f;
  }
}
