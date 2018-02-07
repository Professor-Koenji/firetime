package com.koenji.firetime.scenes;

import com.koenji.ecs.ICore;
import com.koenji.ecs.events.IKeyPress;
import com.koenji.ecs.input.InputEventType;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.Moveable;
import com.koenji.firetime.entities.DebugBox;
import com.koenji.firetime.entities.Particle;
import com.koenji.firetime.systems.Renderer;
import processing.event.KeyEvent;

public class TestScene extends Scene {

  @Override
  public void added(ICore core) {
    super.added(core);

    // Observers

    // Entities
    for (int i = 0; i < 100; ++i) {
      float w = core.gc().random(0f, core.gc().getWidth());
      float h = core.gc().random(0f, core.gc().getHeight());
      Particle p = new Particle(w, h);
      core.subscribe(IKeyPress.class, p);
      add(p);
    }

    // Systems
    add(new Moveable());
    add(new CircleCollider());

    // Render system
    add(new Renderer());
  }
}
