package com.koenji.firetime.InputTest.Command;

import com.koenji.ecs.component.physics.Acceleration;
import com.koenji.ecs.entity.IEntity;

public class MoveLeftCommand implements ICommand {

  private final float SPEED = .13f;

  @Override
  public void execute(IEntity entity) {

    //
    Acceleration a = entity.getComponent(Acceleration.class);

    a.add(-SPEED, 0);
  }
}
