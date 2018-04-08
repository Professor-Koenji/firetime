package com.koenji.firetime.entity.game;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.IEventController;
import com.koenji.ecs.event.events.IKeyEvent;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.scene.IScene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.firetime.event.WeaponFireEvent;
import processing.core.PVector;

public class Player extends Entity {

  private boolean[] keys = new boolean[]{false,false,false,false};

  private Acceleration accel;

  @Override
  public void added(IScene scene, IEventController eventController) {
    super.added(scene, eventController);
    //
    IGraphicsContext gc = Locator.get(IGraphicsContext.class);
    //
    addComponents(
      new Position(50, 50),
      new Velocity(0, 0),
      accel = new Acceleration(0, 0),
      new Friction(0.975f),
      new CircleBody(16),
      new InverseMass(1f),
      new BoundingBox(0, 0, gc.getWidth(), gc.getHeight()),
      new RenderCircle(16, 0xFF0000FF)
    );
  }

  @Override
  public void update(int dt) {
     super.update(dt);

     float speed = .13f;

    if (keys[0]) {
      accel.add(0, -speed);
    }
    if (keys[2]) {
      accel.add(0, speed);
    }
    if (keys[1]) {
      accel.add(-speed, 0);
    }
    if (keys[3]) {
      accel.add(speed, 0);
    }
  }

  public void keyPress(IKeyEvent event) {
    switch(event.keyCode()) {
      case 87: // W
        keys[0] = true;
        break;
      case 65: // A
        keys[1] = true;
        break;
      case 83: // S
        keys[2] = true;
        break;
      case 68: // D
        keys[3] = true;
        break;
    }
  }

  public void keyRelease(IKeyEvent event) {
    switch(event.keyCode()) {
      case 87: // W
        keys[0] = false;
        break;
      case 65: // A
        keys[1] = false;
        break;
      case 83: // S
        keys[2] = false;
        break;
      case 68: // D
        keys[3] = false;
        break;
    }
  }

  public void mousePress(MouseEvent event) {
    eventController.fireEvent(new WeaponFireEvent(), false);

    Position p = getComponent(Position.class);

    PVector v = PVector.sub(event.position(), p);

    Bullet b = new Bullet(PVector.add(p, v.setMag(20f)), v.setMag(5f));
    scene.add(b);
  }
}
