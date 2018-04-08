package com.koenji.ecs.graph.pathfinding.strategy;

import com.koenji.ecs.graph.pathfinding.heuristic.Heuristics;

/**
 * Strategies class containing all default list of algorithms and strategies
 *
 * @author Brad Davies & Chris Williams
 * @version 1.0
 */

public class Strategies {

  public static final IPathStrategy AStar = new AStar();
  public static final IPathStrategy AStar_Chebyshev = new AStar(Heuristics.chebyshev);
  public static final IPathStrategy AStar_Euclidean = new AStar(Heuristics.euclidean);
  public static final IPathStrategy AStar_Manhattan = new AStar(Heuristics.manhattan);
  public static final IPathStrategy AStar_Octile = new AStar(Heuristics.octile);

  public static final IPathStrategy Dijkstra = new Dijkstra();
  public static final IPathStrategy BFS = new BFS();

}
