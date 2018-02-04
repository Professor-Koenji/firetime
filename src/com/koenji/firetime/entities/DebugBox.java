package com.koenji.firetime.entities;

import com.koenji.ecs.entity.Entity;
import com.koenji.firetime.components.Position;

public class DebugBox extends Entity {

  public DebugBox() {
    addComponent(new Position(10, 10));
  }

}
