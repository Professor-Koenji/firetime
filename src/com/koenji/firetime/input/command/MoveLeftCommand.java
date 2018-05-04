package com.koenji.firetime.input.command;

import com.koenji.ecs.component.physics.Acceleration;
import com.koenji.ecs.entity.IEntity;

public class MoveLeftCommand extends BaseCommand {
  public MoveLeftCommand(float speed) {
    super(speed);
  }

  @Override
  public void execute(IEntity entity) {
    //
    Acceleration a = entity.getComponent(Acceleration.class);

    a.add(-speed, 0);
  }
}
