package com.koenji.firetime.input;

import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.ISubscriber;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.event.events.KeyEvent;
import com.koenji.ecs.event.events.MouseEvent;
import com.koenji.ecs.service.Locator;
import com.koenji.firetime.input.command.ICommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputHandler implements IInputHandler {

  private Map<Integer, ICommand> commands;
  private Map<Integer, Boolean> keyPressed;
  private List<ICommand> commandList;

  private ISubscriber keyPressedEvent;
  private ISubscriber keyReleasedEvent;
  private ISubscriber mouseEvent;

  public InputHandler() {
    commands = new HashMap<>();
    keyPressed = new HashMap<>();
    commandList = new ArrayList<>();

    IEventBus eb = Locator.get(IEventBus.class);
    keyPressedEvent = eb.addEventHandler(InputEvents.KEY_PRESSED, this::keyPressed);
    keyReleasedEvent = eb.addEventHandler(InputEvents.KEY_RELEASED, this::keyReleased);
    //mouseEvent = eb.addEventHandler(InputEvents.MOUSE_PRESSED, this::mousePressed);
  }

  @Override
  public void bindKeyCommand(int keyCode, ICommand c) {
    commands.put(keyCode, c);
    keyPressed.put(keyCode, false);
  }

  private void keyPressed(KeyEvent e) {
    if(keyPressed.containsKey(e.keyCode())) {
      keyPressed.put(e.keyCode(), true);
    }
  }

  private void keyReleased(KeyEvent e) {
    if(keyPressed.containsKey(e.keyCode())) {
      keyPressed.put(e.keyCode(), false);
    }
  }

  private void mousePressed(MouseEvent e) {

  }

  @Override
  public List<ICommand> update() {
    // rtn all commands that should be called/executed
    commandList.clear();

    List<Integer> pressedKeys = keyPressed.entrySet()
      .stream()
      .filter(k -> k.getValue().equals(true))
      .map(Map.Entry::getKey)
      .collect(Collectors.toList());

    for(Integer k : pressedKeys) {
      if(commands.containsKey(k)) {
        commandList.add(commands.get(k));
      }
    }

    return commandList;
  }

  @Override
  public void end() {
    keyPressedEvent.unsubscribe();
    keyReleasedEvent.unsubscribe();
    //mouseEvent.unsubscribe();
  }
}
