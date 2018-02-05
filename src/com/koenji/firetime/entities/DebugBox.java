package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.Velocity;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.events.IKeyPress;
import com.koenji.ecs.events.IKeyRelease;
import com.koenji.ecs.events.IMousePress;
import com.koenji.ecs.events.IMouseRelease;
import com.koenji.ecs.scene.IScene;
import com.koenji.firetime.components.DebugDraw;
import com.koenji.ecs.component.physics.Position;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class DebugBox extends Entity implements IKeyPress, IKeyRelease, IMousePress, IMouseRelease {

  private Position pos;

  private int sX;
  private int sY;

  public DebugBox(int sX, int sY) {
    this.sX = sX;
    this.sY = sY;
  }

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    addComponent(pos = new Position(sX, sY));
    addComponent(new Velocity(0, 1));
    addComponent(new DebugDraw(20, 40, 0x508FFCFF));

    scene.gc().subscribe(IKeyPress.class, this);
    scene.gc().subscribe(IKeyRelease.class, this);
    scene.gc().subscribe(IMousePress.class, this);
    scene.gc().subscribe(IMouseRelease.class, this);
  }

  @Override
  public void keyPress(KeyEvent event) {
    pos.x += 20;
  }

  @Override
  public void mousePress(MouseEvent event) {
    pos.y += 20;
  }

  @Override
  public void keyRelease(KeyEvent event) {
    pos.x -= 10;
  }

  @Override
  public void mouseRelease(MouseEvent event) {
    pos.y -= 10;
  }
}
