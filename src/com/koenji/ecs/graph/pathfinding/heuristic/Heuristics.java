package com.koenji.ecs.graph.pathfinding.heuristic;

/**
 * Heuristics class defines the most popular heurisitics used for pathfinding
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */
public class Heuristics {

  public static final IHeuristic manhattan = new Manhattan();
  public static final IHeuristic euclidean = new Euclidean();
  public static final IHeuristic octile = new Octile();
  public static final IHeuristic chebyshev = new Chebyshev();

}
