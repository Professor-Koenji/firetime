package com.koenji.firetime.entity.game;

import com.koenji.ecs.component.physics.*;
import com.koenji.ecs.component.render.RenderCircle;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.event.observer.IKeyPress;
import com.koenji.ecs.event.observer.IKeyRelease;
import com.koenji.ecs.event.observer.IMouseMove;
import com.koenji.ecs.event.observer.IMousePress;
import com.koenji.ecs.scene.IScene;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Player extends Entity implements IKeyPress, IKeyRelease, IMousePress, IMouseMove {

  private boolean[] keys = new boolean[]{false,false,false,false};

  private Acceleration accel;

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    addComponents(
      new Position(50, 50),
      new Velocity(0, 0),
      accel = new Acceleration(0, 0),
      new Friction(0.975f),
      new CircleBody(16),
      new InverseMass(1f),
      new BoundingBox(0, 0, scene.gc().getWidth(), scene.gc().getHeight()),
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

  @Override
  public void keyPress(KeyEvent event) {
    switch(event.getKeyCode()) {
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

  @Override
  public void keyRelease(KeyEvent event) {
    switch(event.getKeyCode()) {
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

  @Override
  public void mousePress(MouseEvent event) {

    Position p = getComponent(Position.class);

    PVector v = PVector.sub(new PVector(event.getX(), event.getY()), p);

    Bullet b = new Bullet(PVector.add(p, v.setMag(20f)), v.setMag(5f));
    scene.add(b);
  }

  @Override
  public void mouseMove(MouseEvent event) {

  }
}
