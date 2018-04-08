package com.koenji.ecs.graph.pathfinding.nodes;

import processing.core.PVector;

import java.util.List;

/**
 * INode interface defines the behaviour of a Node implementation within the system, this is used to set its position
 * and neighbour(s)
 *
 * @author Brad Davis & Chris Williams
 * @version 1.0
 */

public interface INode {
  int getX();
  int getY();
  void set(PVector position);
  List<INode> getNeighbours();
  void addNeighbour(INode node);
  void removeNeighbour(INode node);
  void removeAllNeighbours();
}
