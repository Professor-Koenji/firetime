package com.koenji.firetime.input;

import com.koenji.firetime.input.command.ICommand;

import java.util.List;

public interface IInputHandler {
  void bindKeyCommand(int keyCode, ICommand c);
  List<ICommand> update();
  void end();
}
