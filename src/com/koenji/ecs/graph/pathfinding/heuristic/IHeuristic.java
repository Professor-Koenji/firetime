package com.koenji.ecs.graph.pathfinding.heuristic;

import com.koenji.ecs.graph.pathfinding.nodes.INode;

public interface IHeuristic {
  float distance(INode a, INode b);
}
