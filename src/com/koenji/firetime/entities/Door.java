package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.ConvexBody;
import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Rotation;
import com.koenji.ecs.component.render.RenderPolygon;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.ISubscriber;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.service.Locator;
import com.koenji.firetime.events.GameEvent;
import processing.core.PVector;

public class Door extends Entity {

  public static final int[] COLOURS = {0xFFFF0000, 0xFF00FF00, 0xFF0000FF};

  private int colour;
  private float x;
  private float y;
  private float w;
  private float h;
  private float angle;

  private ISubscriber handler;

  public Door(int colour, float x, float y, float w, float h, float angle) {
    this.colour = colour;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.angle = angle;
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    ConvexBody cb = new ConvexBody((int) Math.sqrt(w*w + h*h),
      new PVector(-w/2f, -h/2f),
      new PVector(w/2f, -h/2f),
      new PVector(w/2f, h/2f),
      new PVector(-w/2f, h/2f)
    );
    cb.isStatic = true;
    addComponents(
      new Position(x, y),
      new Rotation(angle),
      cb,
      new RenderPolygon(cb, COLOURS[this.colour])
    );

    // Listen for key collection of this colour
    handler = Locator.get(IEventBus.class).addEventHandler(GameEvent.GOT_KEY, e -> {
      if (e.data == this.colour) {
        scene.remove(this);
      }
    });
  }

  @Override
  public void removed() {
    super.removed();
    //
    handler.unsubscribe();
  }
}
