package com.koenji.ecs.graph.pathfinding.nodes;

import java.util.List;

public interface IPathNode {
  float getG();
  float getH();
  float getF();
  void setG(float g);
  INode getNode();
  void setPrevious(IPathNode previous);
  IPathNode getPrevious();
  List<INode> createPath();
}
