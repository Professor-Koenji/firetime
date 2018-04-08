package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.scene.example.CollisionDetection;
import com.koenji.ecs.scene.example.SimplePhysics;
import com.koenji.firetime.scenes.Menu;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(1200, 800, 60, "Firetime", 0xFFF8FBFE);
  }

  @Override
  public void init() {
    super.init();
    //
    add(new Menu());
  }
}
