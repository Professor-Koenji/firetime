package com.koenji.firetime.InputTest;

import processing.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class InputHandler {

  private Map<Integer, ICommand> commands;

  public InputHandler() {
    commands = new HashMap<>();
  }

  public void bindCommand(int keyCode, ICommand c) {
    commands.put(keyCode, c);
  }

  public ICommand handleInput(KeyEvent event) {

    return commands.getOrDefault(event.getKeyCode(), null);
  }
}
