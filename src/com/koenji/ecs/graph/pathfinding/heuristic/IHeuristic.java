package com.koenji.ecs.graph.pathfinding.heuristic;

import com.koenji.ecs.graph.pathfinding.nodes.INode;

/**
 * The interface that any Heuristic must implement.
 */
public interface IHeuristic {
  /**
   * @param a A node.
   * @param b Another node.
   * @return The heuristic distance estimate between the two nodes.
   */
  float distance(INode a, INode b);
}
