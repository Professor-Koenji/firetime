package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.firetime.scenes.Splash;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(1600, 900, 30, "Firetime", 0x06030056);
  }

  @Override
  public void init() {
    add(new Splash());
  }
}
