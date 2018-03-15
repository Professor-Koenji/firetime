package com.koenji.ecs.graph.pathfinding.heuristic;

public class Heuristics {

  public static final IHeuristic manhattan = new Manhattan();
  public static final IHeuristic euclidean = new Euclidean();
  public static final IHeuristic octile = new Octile();

}
