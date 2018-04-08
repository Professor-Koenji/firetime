package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.event.IEventBus;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.graph.pathfinding.heuristic.Heuristics;
import com.koenji.ecs.graph.pathfinding.strategy.Strategies;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.nodes.Node;
import com.koenji.ecs.graph.pathfinding.Pathfinder;
import com.koenji.ecs.scene.example.ConvexCollisions;
import com.koenji.firetime.scene.GamePrototype;
import com.koenji.firetime.scene.Menu;
import processing.core.PVector;
import com.koenji.ecs.service.Locator;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import java.io.File;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(800, 600, 60, "Firetime", 0xFF000000);
  }

  @Override
  public void init() {
    super.init();

    add(new Menu());
  }
}
