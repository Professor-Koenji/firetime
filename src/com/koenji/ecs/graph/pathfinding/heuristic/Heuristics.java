package com.koenji.ecs.graph.pathfinding.heuristic;

/**
 * A static list of out-of-the-box Heuristics provided.
 */
public class Heuristics {

  public static final IHeuristic manhattan = new Manhattan();
  public static final IHeuristic euclidean = new Euclidean();
  public static final IHeuristic octile = new Octile();
  public static final IHeuristic chebyshev = new Chebyshev();

}
