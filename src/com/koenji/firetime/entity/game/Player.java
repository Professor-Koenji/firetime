package com.koenji.firetime.entity.game;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.events.*;
import com.koenji.ecs.scene.IScene;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Player extends Entity {

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    addComponents(
      new Position(50, 50),
      new Velocity(0, 0),
      new Acceleration(0, 0),
      new Friction(0.975f),
      new CircleBody(16),
      new InverseMass(1f),
      new BoundingBox(0, 0, scene.gc().getWidth(), scene.gc().getHeight()),
      new RenderCircle(16, 0xFF0000FF)
    );
  }

}
