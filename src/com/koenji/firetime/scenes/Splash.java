package com.koenji.firetime.scenes;

import com.koenji.ecs.ICore;
import com.koenji.ecs.events.IKeyPressEvent;
import com.koenji.ecs.input.InputEventType;
import com.koenji.ecs.scene.Scene;
import com.koenji.firetime.entities.DebugBox;
import com.koenji.firetime.systems.Renderer;
import processing.event.KeyEvent;

public class Splash extends Scene implements IKeyPressEvent {

  @Override
  public void added(ICore core) {
    super.added(core);

    System.out.println("Added!!!");

    // Observers
    core.subscribe(InputEventType.KEY_PRESS, this);

    // Entities
    add(new DebugBox());

    // Systems
    Renderer r = new Renderer();
    add(r);
  }

  @Override
  public void keyPress(KeyEvent event) {
    System.out.println("HELLO!");
  }
}
