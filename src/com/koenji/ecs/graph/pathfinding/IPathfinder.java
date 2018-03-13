package com.koenji.ecs.graph.pathfinding;

import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.strategy.IPathStrategy;

import java.util.List;

public interface IPathfinder {
  List<INode> findPath(INode target);
  List<INode> findPath(INode target, IPathStrategy strategy);
}
