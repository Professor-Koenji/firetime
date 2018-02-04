package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.firetime.scenes.Splash;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(1024, 768, 60, "Firetime");
  }

  @Override
  public void init() {
    add(new Splash());
  }
}
