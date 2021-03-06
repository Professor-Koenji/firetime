package com.koenji.ecs.graph.pathfinding.heuristic;

import com.koenji.ecs.graph.pathfinding.nodes.INode;

/**
 * Euclidean distance is guaranteed to find shortest path
 * in A* path finding of any node graph structure, as there
 * is no possible way of finding a shorter path between two
 * points than the direct path between them.
 *
 * @author Brad Davies
 * @version 1.0
 */
public class Euclidean implements IHeuristic {
  /**
   * The euclidean distance between two nodes.
   * @param a A node
   * @param b Another node
   * @return The euclidean (Straight Line) distance between the two nodes.
   */
  @Override
  public float distance(INode a, INode b) {
    float sx = (float) Math.pow(a.getX() - b.getX(), 2);
    float sy = (float) Math.pow(a.getY() - b.getY(), 2);
    return (float) Math.sqrt(sx + sy);
  }
}
