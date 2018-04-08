package com.koenji.ecs.scene.example;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.component.render.RenderLine;
import com.koenji.ecs.entity.EntityGroup;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.KeyEvent;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.graph.tree.IQuadTree;
import com.koenji.ecs.graph.tree.QuadTree;
import com.koenji.ecs.graph.tree.Rect;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.ISystem;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.ecs.system.render.QuadtreeRenderer;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.wrappers.IRandom;
import processing.core.PVector;

public class SimplePhysicsQT extends Scene {

  private IEntityGroup particles;
  private IEntity gravity;

  private ISystem qtRenderer;
  private boolean debug;

  @Override
  public void added() {
    super.added();
    //
    IRandom rng = Locator.get(IRandom.class);
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);
    //
    add(EntityObject.create(
      new Background(0xFFF8FBFE)
    ));
    //
    particles = new EntityGroup();
    for (int i = 0; i < 200; ++i) {
      float x = rng.random(0, gc.getWidth());
      float y = rng.random(0, gc.getHeight());
      PVector vel = PVector.fromAngle(rng.random(0f, 6.28f)).setMag(rng.random(.5f, 2f));
      float size = rng.random(4, 8);
      int colour = (int) rng.random(0, 0xFFFFFF);
      particles.add(EntityObject.create(
        new Position(x, y),
        new Velocity(vel),
        new Acceleration(),
        new CircleBody(size),
        new InverseMass(1 / size),
        //
        new BoundingBox(size, size, gc.getWidth() - size * 2, gc.getHeight() - size * 2),
        //
        new RenderCircle(size, 0xFF000000 + colour)
      ));
    }
    add(particles);
    gravity = EntityObject.create(new Position());
    add(gravity);
    // Systems
    IQuadTree qt = new QuadTree(new Rect(gc.getWidth(), gc.getHeight()), 10, 10);
    add(new LinearMotion());
    add(new CircleCollider(qt));
    add(new BasicRenderer());
    qtRenderer = new QuadtreeRenderer(qt, 0xFFFF3333);
    debug = false;
    // Add events
    addEventHandler(InputEvents.MOUSE_MOVED, this::mouseMove);
    addEventHandler(InputEvents.MOUSE_PRESSED, this::mousePress);
    addEventHandler(InputEvents.MOUSE_RELEASED, this::mouseRelease);
    addEventHandler(InputEvents.KEY_PRESSED, this::keyPressed);
  }

  private void keyPressed(KeyEvent e) {
    if (e.keyCode() == 32) {
      debug = !debug;
      if (debug) add(qtRenderer);
      else remove(qtRenderer);
    }
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
