package com.koenji.ecs.scene.example;

import com.koenji.ecs.ICore;
import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.component.render.RenderLine;
import com.koenji.ecs.component.render.Stroke;
import com.koenji.ecs.entity.EntityGroup;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import processing.core.PVector;

public class CirclePhysics extends Scene {

  private IEntityGroup particles;
  private IEntity gravity;

  @Override
  public void added(ICore core, IEventBus eventBus) {
    super.added(core, eventBus);
    //
    particles = new EntityGroup();
    for (int i = 0; i < 100; ++i) {
      float x = core.random(0, core.getWidth());
      float y = core.random(0, core.getHeight());
      PVector vel = PVector.fromAngle(core.random(0f, 6.28f)).setMag(core.random(.5f, 2f));
      float size = core.random(4, 16);
      particles.add(EntityObject.create(
        new Position(x, y),
        new Velocity(vel),
        new Acceleration(),
        new CircleBody(size),
        new InverseMass(1 / size),
        //
        new BoundingBox(0, 0, core.getWidth(), core.getHeight()),
        //
        new RenderCircle(size, 0x606666FF),
        new Stroke(4, 0xFF6666FF)
      ));
    }
    add(particles);
    gravity = EntityObject.create(new Position());
    add(gravity);
    // Systems
    add(new LinearMotion());
    add(new CircleCollider());
    add(new BasicRenderer());
    // Add events
    addEventHandler(InputEvents.MOUSE_MOVED, this::mouseMove);
    addEventHandler(InputEvents.MOUSE_PRESSED, this::mousePress);
    addEventHandler(InputEvents.MOUSE_RELEASED, this::mouseRelease);
  }

  public void mouseMove(MouseEvent event) {
    RenderLine l = gravity.getComponent(RenderLine.class);
    if (l != null) {
      l.to.set(event.position().x, event.position().y);
    }
  }

  public void mousePress(MouseEvent event) {
    gravity.getComponent(Position.class).set(event.position().x, event.position().y);
    gravity.addComponent(new RenderLine(event.position().x, event.position().y, 0xFFFF88FF, 6));
  }

  public void mouseRelease(MouseEvent event) {
    PVector grav = PVector.sub(gravity.getComponent(RenderLine.class).to, gravity.getComponent(Position.class)).setMag(.1f);
    particles.addComponent(new Gravity(grav));
    gravity.removeComponent(RenderLine.class);
  }
}
