package com.koenji.firetime.InputTest;

import com.koenji.firetime.InputTest.Command.ICommand;
import processing.event.KeyEvent;

import java.util.List;

public interface IInputHandler {
  void bindCommand(int keyCode, ICommand c);
  ICommand executeOnce(KeyEvent event);
  void handleKeyPress(KeyEvent event);
  void handleKeyRelease(KeyEvent event);
  List<ICommand> update();
}
