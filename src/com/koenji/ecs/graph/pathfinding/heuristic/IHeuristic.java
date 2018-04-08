package com.koenji.ecs.graph.pathfinding.heuristic;

import com.koenji.ecs.graph.pathfinding.nodes.INode;

/**
 * IHeuristic interface defines the behaviour of each heuristic implementation within the system
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public interface IHeuristic {

  float distance(INode a, INode b);
}
