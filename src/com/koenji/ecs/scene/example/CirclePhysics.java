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
import com.koenji.ecs.event.bus.IEventBus;
import com.koenji.ecs.event.observer.IMouseMove;
import com.koenji.ecs.event.observer.IMousePress;
import com.koenji.ecs.event.observer.IMouseRelease;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import processing.core.PVector;
import processing.event.MouseEvent;

public class CirclePhysics extends Scene implements IMousePress, IMouseMove, IMouseRelease {

  private IEntityGroup particles;
  private IEntity gravity;

  @Override
  public void added(ICore core, IEventBus eventBus) {
    super.added(core, eventBus);
    //
    particles = new EntityGroup();
    for (int i = 0; i < 25; ++i) {
      float x = core.random(0, core.getWidth());
      float y = core.random(0, core.getHeight());
      PVector vel = PVector.fromAngle(core.random(0f, 6.28f)).setMag(core.random(.5f, 2f));
      float size = core.random(8, 32);
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
    add(gravity = EntityObject.create(
      new Position()
    ));
    // Systems
    add(new LinearMotion());
    add(new CircleCollider());
    add(new BasicRenderer());
  }

  @Override
  public void mouseMove(MouseEvent event) {
    RenderLine l = gravity.getComponent(RenderLine.class);
    if (l != null) {
      l.to.set(event.getX(), event.getY());
    }
  }

  @Override
  public void mousePress(MouseEvent event) {
    gravity.getComponent(Position.class).set(event.getX(), event.getY());
    gravity.addComponent(new RenderLine(event.getX(), event.getY(), 0xFFFF88FF, 6));
  }

  @Override
  public void mouseRelease(MouseEvent event) {
    PVector grav = PVector.sub(gravity.getComponent(RenderLine.class).to, gravity.getComponent(Position.class)).setMag(.1f);
    particles.addComponent(new Gravity(grav));
    gravity.removeComponent(RenderLine.class);
  }
}
