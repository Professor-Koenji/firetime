package com.koenji.firetime.entities;

import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.events.IKeyPress;
import com.koenji.ecs.events.IKeyRelease;
import com.koenji.ecs.events.IMousePress;
import com.koenji.ecs.events.IMouseRelease;
import com.koenji.ecs.input.InputEventType;
import com.koenji.ecs.scene.IScene;
import com.koenji.firetime.components.DebugDraw;
import com.koenji.firetime.components.Position;
import jdk.internal.util.xml.impl.Input;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class DebugBox extends Entity implements IKeyPress, IKeyRelease, IMousePress, IMouseRelease {

  private Position pos;

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    addComponent(pos = new Position(0, 0));
    addComponent(new DebugDraw(scene.getWidth() / 2, scene.getHeight() / 2, 0xFF3399FF));

    scene.gc().subscribe(InputEventType.KEY_PRESS, (IKeyPress) this);
    scene.gc().subscribe(InputEventType.KEY_RELEASE, (IKeyRelease) this);
    scene.gc().subscribe(InputEventType.MOUSE_PRESS, (IMousePress) this);
    scene.gc().subscribe(InputEventType.MOUSE_RELEASE, (IMouseRelease) this);
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
