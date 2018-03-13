package com.koenji.ecs.graph.pathfinding.strategy;

import com.koenji.ecs.graph.pathfinding.strategy.AStar;
import com.koenji.ecs.graph.pathfinding.strategy.IPathStrategy;

public class Strategies {

  public static final IPathStrategy AStar = new AStar();
  public static final IPathStrategy Dijkstra = new Dijkstra();
  public static final IPathStrategy BFS = new BFS();

}
