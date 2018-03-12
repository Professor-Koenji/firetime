package com.koenji.ecs.graph.pathfinding;

import java.util.List;

public interface INode {
  int getX();
  int getY();
  List<INode> getNeighbours();
  void addNeighbour(INode node);
  void removeNeighbour(INode node);
}
