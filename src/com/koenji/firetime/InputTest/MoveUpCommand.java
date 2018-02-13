package com.koenji.firetime.InputTest;

import com.koenji.ecs.component.physics.Acceleration;
import com.koenji.ecs.entity.IEntity;

public class MoveUpCommand implements ICommand {

  private final float SPEED = .13f;

  @Override
  public void execute(IEntity entity) {

    //
    Acceleration a = entity.getComponent(Acceleration.class);

    a.add(0, -SPEED);
  }
}
