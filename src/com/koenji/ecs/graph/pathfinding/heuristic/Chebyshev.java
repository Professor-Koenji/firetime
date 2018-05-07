package com.koenji.ecs.graph.pathfinding.heuristic;

import com.koenji.ecs.graph.pathfinding.nodes.INode;

/**
 * Chebyshev is simply the distance between two points,
 * assuming that movement in any 8-way direction is equal
 * in cost. Also known as the Chess King distance.
 *
 * @author Brad Davies
 * @version 1.0
 */
public class Chebyshev implements IHeuristic {
  /**
   * The chebyshev distance between two nodes.
   * @param a A node
   * @param b Another node
   * @return The chebyshev (Chess King) distance between the two nodes.
   */
  @Override
  public float distance(INode a, INode b) {
    return Math.max(Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
  }
}
