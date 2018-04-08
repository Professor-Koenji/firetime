package com.koenji.ecs.scene.example;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.component.render.Stroke;
import com.koenji.ecs.entity.EntityGroup;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.entity.IEntity;
import com.koenji.ecs.entity.IEntityGroup;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.PhysicsEvents;
import com.koenji.ecs.event.events.KeyEvent;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.graph.tree.IQuadTree;
import com.koenji.ecs.graph.tree.QuadTree;
import com.koenji.ecs.graph.tree.Rect;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.ISystem;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.ConvexCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.ecs.system.render.QuadtreeRenderer;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.wrappers.IRandom;
import processing.core.PVector;

import java.security.Key;

/**
 * A demonstration of SAT collision detection using the ConvexCollider system.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.1
 */
public class CollisionDetection extends Scene {

  private ISystem qtRenderer;
  private boolean debug;

  @Override
  public void added() {
    super.added();

    IRandom rng = Locator.get(IRandom.class);
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);

    add(EntityObject.create(
      new Background(0xFFF8FBFE)
    ));

    // Entities
    for (int i = 0; i < 100; ++i) {
      float x = rng.random(0, gc.getWidth());
      float y = rng.random(0, gc.getHeight());
      float sx = rng.random(-2f, 2f);
      float sy = rng.random(-2f, 2f);
      int sides = (int) rng.random(4, 10);
      ConvexBody body = ConvexBody.polygon(sides, rng.random(10, 28));

      int colour = (int) rng.random(0, 0xFFFFFF);
      add(EntityObject.create(
        new Position(x, y),
        new Velocity(sx, sy),
        new Acceleration(),
        new Rotation(rng.random(0f, gc.TWO_PI)),
        new Friction(0.98f),
        new Gravity(.1f),
        new BoundingBox(BoundingBox.REFLECT, 32, 32, gc.getWidth() - 64, gc.getHeight() - 64),
        body,
        new RenderPolygon(body, 0xFF000000 + colour)
      ));
    }

    // Spinners
    IEntityGroup spinners = new EntityGroup();
    ConvexBody cb = new ConvexBody(250,
      new PVector(-100, 0),
      new PVector(0, -20),
      new PVector(100, 0),
      new PVector(0, 20)
    );
    cb.isStatic = true;
    int total = 4;
    float slice = gc.getWidth() / total;
    float current = slice / 2f;
    for (int i = 0; i < total; ++i) {
      spinners.add(EntityObject.create(
        new Position(slice / 2f + slice * i, gc.getHeight() - 150),
        cb,
        new RenderPolygon(cb, 0xFF335533),
        new Rotation(),
        new AngularVelocity(i % 2 == 0 ? .1f : -.1f)
      ));
    }
    add(spinners);

    // Systems
    IQuadTree qt = new QuadTree(new Rect(gc.getWidth(), gc.getHeight()), 3, 8);
    add(new LinearMotion());
    add(new ConvexCollider(qt));

    // Render systems
    add(new BasicRenderer());

    debug = false;
    qtRenderer = new QuadtreeRenderer(qt, 0xFFFF3333);

    addEventHandler(InputEvents.KEY_PRESSED, this::keyPressed);
  }

  @Override
  public void update(int dt) {
    super.update(dt);
  }

  private void keyPressed(KeyEvent e) {
    if (e.keyCode() == 32) {
      debug = !debug;
      if (debug) add(qtRenderer);
      else remove(qtRenderer);
    }
  }
}
