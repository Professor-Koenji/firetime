package com.koenji.ecs.graph.pathfinding.heuristic;

import com.koenji.ecs.graph.pathfinding.nodes.INode;

/**
 * In non-cardinal node graphs, Manhattan approximation is
 * NOT GUARANTEED to find the shortest path within A* or similar
 * path strategies, as it can over-approximate the remaining path
 * distance and produce potentially longer paths than it otherwise
 * could find, however doing so can increase the speed at which
 * A* is able to 'home-in' on a 'good-enough' path, although that
 * only is really an advantage with extremely large graphs.
 */
public class Manhattan implements IHeuristic {

  @Override
  public float distance(INode a, INode b) {
    return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
  }
}
