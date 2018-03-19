package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.graph.pathfinding.heuristic.Heuristics;
import com.koenji.ecs.graph.pathfinding.strategy.Strategies;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.nodes.Node;
import com.koenji.ecs.graph.pathfinding.Pathfinder;
import com.koenji.ecs.scene.example.ConvexCollisions;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Game extends Core {

  public Game() {
    // Set initial resolution, fps & title
    super(800, 600, 60, "Firetime", 0xFF000000);
  }

  @Override
  public void init() {
    super.init();
    //
    add(new ConvexCollisions());
  }
}
