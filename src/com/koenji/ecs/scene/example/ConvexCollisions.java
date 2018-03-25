package com.koenji.ecs.scene.example;

import com.koenji.ecs.ICore;
import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.component.render.Stroke;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.ConvexCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;

public class ConvexCollisions extends Scene {

  private Position mousePos;

  @Override
  public void added(ICore core) {
    super.added(core);

    add(EntityObject.create(
      new Background(0x30000099)
    ));

    // Entities
    for (int i = 0; i < 20; ++i) {
      float x = core.gc().random(0, core.getWidth());
      float y = core.gc().random(0, core.getHeight());
      float sx = core.gc().random(-2f, 2f);
      float sy = core.gc().random(-2f, 2f);
      int sides = (int) core.random(3, 8);
      ConvexBody body = ConvexBody.polygon(sides, 32);

      int colour = (int) core.random(0, 0xFFFFFF);
      add(EntityObject.create(
        new Position(x, y),
        new Velocity(sx, sy),
        new Acceleration(),
        new Friction(0.98f),
        new Gravity(.1f),
        new BoundingBox(BoundingBox.REFLECT, 0, 0, core.getWidth(), core.getHeight()),
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
