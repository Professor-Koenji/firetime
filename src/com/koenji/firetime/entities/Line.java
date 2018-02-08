package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.render.RenderLine;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.events.IMousePress;
import com.koenji.ecs.events.IMouseRelease;
import processing.event.MouseEvent;

public class Line extends Entity implements IMousePress, IMouseRelease {

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    addComponent(new RenderLine(scene.gc().mouseX, scene.gc().mouseY, 0xFFFFFFFF, 8));
  }

  @Override
  public void mousePress(MouseEvent event) {
    addComponent(new Position(event.getX(), event.getY()));
  }

  @Override
  public void mouseRelease(MouseEvent event) {
    removeComponent(Position.class);
  }
}
