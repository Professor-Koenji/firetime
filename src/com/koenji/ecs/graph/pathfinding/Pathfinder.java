package com.koenji.ecs.graph.pathfinding;

import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.strategy.IPathStrategy;
import com.koenji.ecs.graph.pathfinding.strategy.Strategies;

import java.util.List;

/**
 * Pathfinder class defines the concrete behaviour of finding a path within the system, using the strategy provided
 *
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public class Pathfinder implements IPathfinder {

  // starting node
  private INode start;
  // default strategy to use
  private IPathStrategy defaultStrategy;

  /**
   * Constructor: defaults to no strategy
   * @param start - INode to start from
   */
  public Pathfinder(INode start) {
    this(start, null);
  }

  /**
   * Constructor: set the start and strategy
   * @param start           - INode to start from
   * @param defaultStrategy - strategy to use
   */
  public Pathfinder(INode start, IPathStrategy defaultStrategy) {
    this.start = start;
    this.defaultStrategy = defaultStrategy;
  }

  /**
   * Method: used to return a path without a strategy, return a List of INodes
   * @param target - target INode
   * @return       - List of the path of INodes
   */
  public List<INode> findPath(INode target) {
    return findPath(target, null);
  }

  /**
   * Method: used to return a path with/without a strategy, returns a List of INodes
   * @param target    - target INode
   * @param strategy  - strategy to use for path
   * @return          - List of the path of INodes
   */
  public List<INode> findPath(INode target, IPathStrategy strategy) {
    // If a strategy has been defined, use it.
    if (strategy != null) return strategy.findPath(start, target);
    // If no strategy was defined, but a default was, then use the default
    if (defaultStrategy != null) return defaultStrategy.findPath(start, target);
    // Finally, use the default engine strategy, which is A*.
    return Strategies.AStar.findPath(start, target);
  }
}
