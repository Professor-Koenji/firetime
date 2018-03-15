package com.koenji.ecs.graph.pathfinding.heuristic;

import com.koenji.ecs.graph.pathfinding.nodes.INode;

/**
 * Octile heuristic is something of a half-step between
 * Manhattan and Euclidean heuristics. It generally expands
 * fewer nodes than the Euclidean heuristic.
 */
public class Octile implements IHeuristic {

  private static float SQRT_2 = (float) Math.sqrt(2);

  @Override
  public float distance(INode a, INode b) {
    float x = Math.abs(a.getX() - b.getX());
    float y = Math.abs(a.getY() - b.getY());
    return Math.max(x, y) + SQRT_2 * Math.min(x, y);
  }
}
