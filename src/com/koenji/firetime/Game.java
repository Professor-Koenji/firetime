package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.firetime.scenes.TestScene;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(1600, 900, 60, "Firetime", 0xFF030056);
  }

  @Override
  public void init() {
    add(new TestScene());
  }
}
