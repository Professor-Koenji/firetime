package com.koenji.firetime.input.command;

public abstract class BaseCommand implements ICommand {

  float speed;

  public BaseCommand(float speed) {
    this.speed = speed;
  }
}
