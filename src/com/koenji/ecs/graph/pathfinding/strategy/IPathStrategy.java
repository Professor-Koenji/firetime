package com.koenji.ecs.graph.pathfinding.strategy;

import com.koenji.ecs.graph.pathfinding.nodes.INode;

import java.util.List;

/**
 * IPathStrategy interface defines the behaviour for a search algorithm within the system
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */

public interface IPathStrategy {
  /**
   * Method: used to find a path between two given nodes
   * @param start   - starting INode
   * @param target  - target INode
   * @return
   */
  List<INode> findPath(INode start, INode target);
}
