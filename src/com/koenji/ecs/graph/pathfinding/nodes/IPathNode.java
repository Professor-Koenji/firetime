package com.koenji.ecs.graph.pathfinding.nodes;

import java.util.List;

/**
 * An extension of the Node that provides information about
 * the previous node chain, with convenience for common PathFinding
 * strategies. A linked-list implementation.
 *
 * @author Brad Davies & Chris Williams.
 * @version 1.0
 */
public interface IPathNode {
  /**
   * @return The G cost to reach this node.
   */
  float getG();

  /**
   * @return The estimated heuristic (H cost) to reach the target node.
   */
  float getH();

  /**
   * @return The total estimated cost to reach the target + the current cost.
   */
  float getF();

  /**
   * @param g The g score.
   */
  void setG(float g);

  /**
   * @return Get the actual underlying node.
   */
  INode getNode();

  /**
   * @param previous The previous path node.
   */
  void setPrevious(IPathNode previous);

  /**
   * @return Returns the previous path node.
   */
  IPathNode getPrevious();

  /**
   * @return A list of raw INode's from this IPathNode
   */
  List<INode> createPath();
}
