package com.koenji.firetime;

import com.koenji.ecs.Core;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(800, 600, 60, "Firetime", 0xFF000000);
  }
}
