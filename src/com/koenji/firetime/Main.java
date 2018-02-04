package com.koenji.firetime;

import com.koenji.ecs.Core;

public class Main {
  public static void main(String[] args) {
    // Give the engine core our Game class
    // Sets up and runs the class via reflection
    Core.main(Game.class);
  }
}
