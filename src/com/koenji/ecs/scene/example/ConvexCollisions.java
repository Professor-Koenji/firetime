package com.koenji.ecs.scene.example;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.component.render.Stroke;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.ConvexCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.wrappers.IRandom;

public class ConvexCollisions extends Scene {

  private Position mousePos;

  @Override
  public void added() {
    super.added();

    IRandom rng = Locator.get(IRandom.class);
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);

    add(EntityObject.create(
      new Background(0x30000099)
    ));

    // Entities
    for (int i = 0; i < 20; ++i) {
      float x = rng.random(0, gc.getWidth());
      float y = rng.random(0, gc.getHeight());
      float sx = rng.random(-2f, 2f);
      float sy = rng.random(-2f, 2f);
      int sides = (int) rng.random(4, 8);
      ConvexBody body = ConvexBody.polygon(sides, 32);

      int colour = (int) rng.random(0, 0xFFFFFF);
      add(EntityObject.create(
        new Position(x, y),
        new Velocity(sx, sy),
        new Acceleration(),
        new Friction(0.98f),
        new Gravity(.1f),
        new BoundingBox(BoundingBox.REFLECT, 32, 32, gc.getWidth() - 64, gc.getHeight() - 64),
        body,
        new RenderPolygon(body, 0x60000000 + colour),
        new Stroke(4, 0xFF000000 + colour)
      ));
    }

    ConvexBody circle = ConvexBody.polygon(16, 64);
    circle.setStatic(true);

    add(EntityObject.create(
      mousePos = new Position(400, 400),
      circle,
      new RenderCircle(64, 0x60FF99FF),
//      new RenderPolygon(circle, 0x60FF99FF),
      new Stroke(4, 0xFFFF99FF)
    ));

    // Systems
    add(new LinearMotion());
    add(new CircleCollider());
    add(new ConvexCollider());

    // Render systems
    add(new BasicRenderer());

    addEventHandler(InputEvents.MOUSE_MOVED, this::mouseMove);
  }

  private void mouseMove(MouseEvent event) {
    mousePos.set(event.position());
  }
}
