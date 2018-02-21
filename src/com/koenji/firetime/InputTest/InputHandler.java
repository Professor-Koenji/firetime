package com.koenji.firetime.InputTest;

import com.koenji.firetime.InputTest.Command.ICommand;
import processing.event.Event;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputHandler {

  private Map<Integer, ICommand> commands;
  private Map<Integer, Boolean> keyPressed;
  private List<ICommand> commandList;

  public InputHandler() {
    commands = new HashMap<>();
    keyPressed = new HashMap<>();
    commandList = new ArrayList<>();
  }

  public void bindCommand(int keyCode, ICommand c) {
    commands.put(keyCode, c);
    keyPressed.put(keyCode, false);
  }

  public ICommand executeOnce(KeyEvent event) {
    return commands.getOrDefault(event.getKeyCode(), null);
  }

  public void handleKeyPress(KeyEvent event) {
    if(keyPressed.containsKey(event.getKeyCode())) {
      keyPressed.put(event.getKeyCode(), true);
    }
  }

  public void handleKeyRelease(KeyEvent event) {
    if(keyPressed.containsKey(event.getKeyCode())) {
      keyPressed.put(event.getKeyCode(), false);
    }
  }

  public List<ICommand> update() {
    // rtn all commands that should be called/executed
    commandList.clear();

    List<Integer> pressedKeys = keyPressed.entrySet()
                                        .stream()
                                        .filter(k -> k.getValue().equals(true))
                                        .map(k -> k.getKey())
                                        .collect(Collectors.toList());

    for(Integer k : pressedKeys) {
      if(commands.containsKey(k)) {
        commandList.add(commands.get(k));
      }
    }

    return commandList;
  }
}
