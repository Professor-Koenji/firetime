package com.koenji.firetime.input.command;

import com.koenji.ecs.entity.IEntity;

public interface ICommand {
  void execute (IEntity entity);
}
