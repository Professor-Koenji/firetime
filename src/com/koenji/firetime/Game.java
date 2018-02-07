package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.debug.DebugScene;
import com.koenji.ecs.events.IKeyPress;
import com.koenji.ecs.events.IMousePress;
import com.koenji.ecs.events.IMouseRelease;
import com.koenji.firetime.scenes.TestScene;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(1600, 900, 60, "Firetime", 0xFF030056);
  }

  @Override
  public void init() {
    TestScene ts = new TestScene();
    subscribe(IMousePress.class, ts);
    subscribe(IMouseRelease.class, ts);
    add(ts);

    DebugScene ds = new DebugScene();
    subscribe(IKeyPress.class, ds);
    add(ds);
  }
}
