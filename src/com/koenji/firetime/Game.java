package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.debug.DebugScene;
import com.koenji.ecs.scene.example.CirclePhysics;
import com.koenji.ecs.scene.example.ConvexCollisions;
import com.koenji.firetime.scene.Menu;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(800, 600, 60, "Firetime", 0x10000000);
  }

  @Override
  public void init() {
    ConvexCollisions cc = new ConvexCollisions();
    add(cc);

    DebugScene ds = new DebugScene();
    add(ds);
  }
}
