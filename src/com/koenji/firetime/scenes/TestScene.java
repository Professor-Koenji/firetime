package com.koenji.firetime.scenes;

import com.koenji.ecs.ICore;
import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Gravity;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.RenderConvex;
import com.koenji.ecs.entity.EntityGroup;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.events.IMouseMove;
import com.koenji.ecs.events.IMousePress;
import com.koenji.ecs.events.IMouseRelease;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.firetime.entities.Line;
import com.koenji.firetime.entities.Particle;
import processing.core.PVector;
import processing.event.MouseEvent;

public class TestScene extends Scene implements IMouseMove {

  private Position mousePos;

  @Override
  public void added(ICore core) {
    super.added(core);

    // Add background EntityObject
    add(EntityObject.create(new Background(0xff112233)));

    // Entities
    ConvexBody cb = new ConvexBody(
      new PVector(0, 0),
      new PVector(70, 20),
      new PVector(140, 90),
      new PVector(20, 100)
    );

    add(EntityObject.create(
      new Position(600, 420),
      cb,
      new RenderConvex(cb, 0xFF99CCFF)
    ));

    add(EntityObject.create(
      mousePos = new Position(400, 400),
      cb,
      new RenderConvex(cb, 0xFFFF99FF)
    ));

    // Systems
    add(new LinearMotion());
    add(new CircleCollider());

    // Render systems
    add(new BasicRenderer());
  }

  @Override
  public void mouseMove(MouseEvent event) {
    mousePos.set(event.getX(), event.getY());
  }
}
