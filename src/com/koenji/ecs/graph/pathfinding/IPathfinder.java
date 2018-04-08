package com.koenji.ecs.graph.pathfinding;

import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.strategy.IPathStrategy;

import java.util.List;

/**
 * IPathfinder interface defines the behaviour for finding a path within the system
 *
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public interface IPathfinder {
  List<INode> findPath(INode target);
  List<INode> findPath(INode target, IPathStrategy strategy);
}
