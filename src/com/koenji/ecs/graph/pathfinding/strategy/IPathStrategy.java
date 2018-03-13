package com.koenji.ecs.graph.pathfinding.strategy;

import com.koenji.ecs.graph.pathfinding.nodes.INode;

import java.util.List;

public interface IPathStrategy {
  List<INode> findPath(INode start, INode target);
}
