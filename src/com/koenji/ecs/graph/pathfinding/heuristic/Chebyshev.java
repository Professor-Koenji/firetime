package com.koenji.ecs.graph.pathfinding.heuristic;

import com.koenji.ecs.graph.pathfinding.nodes.INode;

/**
 * Chebyshev is simply the distance between two points,
 * assuming that movement in any 8-way direction is equal
 * in cost. Also known as the Chess King distance.
 */
public class Chebyshev implements IHeuristic {
  @Override
  public float distance(INode a, INode b) {
    return Math.max(Math.abs(a.getX() - b.getX()), Math.abs(a.getY() - b.getY()));
  }
}
