package com.koenji.ecs.scene.example;

import com.koenji.ecs.ICore;
import com.koenji.ecs.component.physics.BoundingBox;
import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.component.render.Stroke;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.events.IMouseMove;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.ConvexCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import processing.core.PVector;
import processing.event.MouseEvent;

public class ConvexCollisions extends Scene implements IMouseMove {

  private Position mousePos;

  @Override
  public void added(ICore core) {
    super.added(core);

    // Add background EntityObject
//    add(EntityObject.create(new Background(0xff112233)));

    // Entities
    ConvexBody square = ConvexBody.square(100, -50, -50);
    ConvexBody hexagon = new ConvexBody(
      new PVector(0, -50),
      new PVector(43, -25),
      new PVector(43, 25),
      new PVector(0, 50),
      new PVector(-43, 25),
      new PVector(-43, -25)
    );

    for (int i = 0; i < 40; ++i) {
      float x = core.gc().random(0, core.getWidth());
      float y = core.gc().random(0, core.getHeight());
      float sx = core.gc().random(-2f, 2f);
      float sy = core.gc().random(-2f, 2f);
      add(EntityObject.create(
        new Position(x, y),
        new Velocity(sx, sy),
        new BoundingBox(0, 0, core.getWidth(), core.getHeight()),
        hexagon,
        new RenderPolygon(hexagon, 0x6022FF99),
        new Stroke(4, 0xFF22FF99)
      ));
    }

    add(EntityObject.create(
      mousePos = new Position(400, 400),
      square,
      new RenderPolygon(square, 0x60FF99FF),
      new Stroke(4, 0xFFFF99FF)
    ));

    // Systems
    add(new LinearMotion());
    add(new CircleCollider());
    add(new ConvexCollider());

    // Render systems
    add(new BasicRenderer());
  }

  @Override
  public void mouseMove(MouseEvent event) {
    mousePos.set(event.getX(), event.getY());
  }
}