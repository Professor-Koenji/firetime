package com.koenji.ecs.graph.pathfinding;

import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.strategy.IPathStrategy;
import com.koenji.ecs.graph.pathfinding.strategy.Strategies;

import java.util.List;

public class Pathfinder implements IPathfinder {

  private INode start;
  private IPathStrategy defaultStrategy;

  public Pathfinder(INode start) {
    this(start, null);
  }

  public Pathfinder(INode start, IPathStrategy defaultStrategy) {
    this.start = start;
    this.defaultStrategy = defaultStrategy;
  }

  public List<INode> findPath(INode target) {
    return findPath(target, null);
  }

  public List<INode> findPath(INode target, IPathStrategy strategy) {
    // If a strategy has been defined, use it.
    if (strategy != null) return strategy.findPath(start, target);
    // If no strategy was defined, but a default was, then use the default
    if (defaultStrategy != null) return defaultStrategy.findPath(start, target);
    // Finally, use the default engine strategy, which is A*.
    return Strategies.AStar.findPath(start, target);
  }
}
