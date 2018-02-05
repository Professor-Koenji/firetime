package com.koenji.firetime.scenes;

import com.koenji.ecs.ICore;
import com.koenji.ecs.events.IKeyPress;
import com.koenji.ecs.input.InputEventType;
import com.koenji.ecs.scene.Scene;
import com.koenji.firetime.entities.DebugBox;
import com.koenji.firetime.systems.Renderer;
import processing.event.KeyEvent;

public class Splash extends Scene {

  @Override
  public void added(ICore core) {
    super.added(core);

    System.out.println("Added!!!");

    // Observers

    // Entities
    for (int i = 0; i < 1000; ++i) {
      float w = core.gc().random(0f, core.gc().getWidth());
      float h = core.gc().random(0f, core.gc().getHeight());
      add(new DebugBox((int) w, (int) h));
    }

    // Systems
    Renderer r = new Renderer();
    add(r);
  }
}
