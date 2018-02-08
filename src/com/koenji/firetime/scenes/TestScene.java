package com.koenji.firetime.scenes;

import com.koenji.ecs.ICore;
import com.koenji.ecs.component.physics.Gravity;
import com.koenji.ecs.component.render.Background;
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

public class TestScene extends Scene implements IMousePress, IMouseRelease {

  private IEntityGroup particles;
  private PVector mousePos;

  @Override
  public void added(ICore core) {
    super.added(core);

    // Add background EntityObject
    add(EntityObject.create(new Background(0xff112233)));

    // Entities
    particles = new EntityGroup();
    for (int i = 0; i < 90; ++i) {
      float w = core.gc().random(0f, core.gc().getWidth());
      float h = core.gc().random(0f, core.gc().getHeight());
      Particle p = new Particle(w, h);
      particles.add(p);
    }
    add(particles);

    Line line = new Line();
    core.subscribe(IMousePress.class, line);
    core.subscribe(IMouseRelease.class, line);
    core.subscribe(IMouseMove.class, line);
    add(line);

    // Systems
    add(new LinearMotion());
    add(new CircleCollider());

    // Render systems
    add(new BasicRenderer());
  }

  @Override
  public void mousePress(MouseEvent event) {
    mousePos = new PVector(event.getX(), event.getY());
  }

  @Override
  public void mouseRelease(MouseEvent event) {
    PVector endPos = new PVector(event.getX(), event.getY());
    endPos.sub(mousePos).limit(.1f);
    //
    particles.addComponent(new Gravity(endPos));
  }
}
