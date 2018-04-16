package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.firetime.level.LevelObject;
import com.koenji.firetime.scenes.Level;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps&amp;title
    super(1200, 800, 60, "Firetime", 0xFFF8FBFE);
  }

  @Override
  public void init() {
    super.init();
    //
    LevelObject lo = LevelObject.fromPath("bob-1523896826");
    add(new Level(lo));
  }
}
