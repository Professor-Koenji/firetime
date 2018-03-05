package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.debug.DebugScene;
import com.koenji.ecs.event.observer.IKeyPress;
import com.koenji.ecs.event.observer.IMousePress;
import com.koenji.firetime.scene.Menu;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(800, 600, 60, "Firetime", 0x10000000);
  }

  @Override
  public void init() {

    Menu menu = new Menu();
    subscribe(IMousePress.class, menu);
    add(menu);

    DebugScene ds = new DebugScene();
    subscribe(IKeyPress.class, ds);
    add(ds);
  }
}
