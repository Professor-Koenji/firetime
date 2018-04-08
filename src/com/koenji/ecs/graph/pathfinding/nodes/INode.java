package com.koenji.ecs.graph.pathfinding.nodes;

import processing.core.PVector;

import java.util.List;

/**
 * A raw 2D waypoint-style graph node implementation.
 *
 * @author Brad Davies & Chris Williams
 * @version 1.0
 */
public interface INode {
  /**
   * @return The x-position of this Node.
   */
  int getX();

  /**
   * @return The y-position of this Node.
   */
  int getY();

  /**
   * @param position The position of this Node.
   */
  void set(PVector position);

  /**
   * @return A list of connected neighbouring Nodes.
   */
  List<INode> getNeighbours();

  /**
   * @param node Connect the given node to this node.
   */
  void addNeighbour(INode node);

  /**
   * @param node Disconnect the link between this node and the given node.
   */
  void removeNeighbour(INode node);

  /**
   * Remove all node connections to this node.
   */
  void removeAllNeighbours();
}
