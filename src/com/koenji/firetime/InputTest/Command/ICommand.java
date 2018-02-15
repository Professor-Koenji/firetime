package com.koenji.firetime.InputTest.Command;

import com.koenji.ecs.entity.IEntity;

public interface ICommand {

  void execute (IEntity entity);
}
