package com.koenji.ecs.graph.pathfinding.nodes;

import processing.core.PVector;

import java.util.List;

public interface INode {
  int getX();
  int getY();
  void set(PVector position);
  List<INode> getNeighbours();
  void addNeighbour(INode node);
  void removeNeighbour(INode node);
  void removeAllNeighbours();
}
