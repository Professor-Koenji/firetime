package com.koenji.firetime.entities;

import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.events.IKeyPressEvent;
import com.koenji.ecs.input.InputEventType;
import com.koenji.ecs.scene.IScene;
import com.koenji.firetime.components.DebugDraw;
import com.koenji.firetime.components.Position;
import processing.event.KeyEvent;

public class DebugBox extends Entity implements IKeyPressEvent {

  private Position pos;

  @Override
  public void added(IScene scene) {
    super.added(scene);
    //
    addComponent(pos = new Position(0, 0));
    addComponent(new DebugDraw(scene.getWidth() / 2, scene.getHeight() / 2, 0xFF3399FF));

    scene.gc().subscribe(InputEventType.KEY_PRESS, this);
  }

  @Override
  public void keyPress(KeyEvent event) {
    pos.x += 20;
  }
}
