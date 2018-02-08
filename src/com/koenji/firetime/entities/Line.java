package com.koenji.firetime.entities;

import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.render.RenderLine;
import com.koenji.ecs.entity.Entity;
import com.koenji.ecs.events.IMouseMove;
import com.koenji.ecs.events.IMousePress;
import com.koenji.ecs.events.IMouseRelease;
import processing.event.MouseEvent;

public class Line extends Entity implements IMousePress, IMouseRelease, IMouseMove {

  @Override
  public void mousePress(MouseEvent event) {
    addComponent(new Position(event.getX(), event.getY()));
  }

  @Override
  public void mouseRelease(MouseEvent event) {
    removeComponent(Position.class);
  }

  @Override
  public void mouseMove(MouseEvent event) {
    addComponent(new RenderLine(event.getX(), event.getY(), 0xFFFF3399, 12));
  }
}
