package com.koenji.ecs.graph.pathfinding.nodes;

import java.util.List;

/**
 * IPathNode interface defines the behaviour of each PathNode implementation within the system, this is used to set the cost
 *
 *
 * @author Brad Davies & Chris Williams
 * @version 1.0
 */

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
