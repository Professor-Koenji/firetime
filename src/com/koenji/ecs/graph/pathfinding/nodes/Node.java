package com.koenji.ecs.graph.pathfinding.nodes;

import com.koenji.ecs.graph.pathfinding.nodes.INode;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * A Node implementation used within the provided Path Findings systems.
 *
 * @author Brad Davies & Chris Williams
 * @version 1.1
 */
public class Node implements INode {

  private int x;
  private int y;
  private List<INode> neighbours;

  /**
   * Constructs a new Node.
   * @param x The X position of the node.
   * @param y The Y position of the node.
   */
  public Node(int x, int y) {
    this.x = x;
    this.y = y;
    this.neighbours = new ArrayList<>();
  }

  /**
   * @return Gets the X position of the Node.
   */
  public int getX() {
    return x;
  }

  /**
   * @return Gets the Y position of the node.
   */
  public int getY() {
    return y;
  }

  /**
   * Sets the Node to the vector position.
   * @param position The desired position of this Node.
   */
  public void set(PVector position) {
    this.x = (int) position.x;
    this.y = (int) position.y;
  }

  /**
   * @return Returns all neighbours of this Node.
   */
  public List<INode> getNeighbours() {
    return neighbours;
  }

  /**
   * Connects the nodes together.
   * @param node Connect the given node to this node.
   */
  public void addNeighbour(INode node) {
    if (neighbours.contains(node)) return;
    neighbours.add(node);
    node.addNeighbour(this);
  }

  /**
   * Removes the graph connection between the two nodes.
   * @param node Disconnect the link between this node and the given node.
   */
  public void removeNeighbour(INode node) {
    if (!neighbours.contains(node)) return;
    neighbours.remove(node);
    node.removeNeighbour(this);
  }

  /**
   * Remove all neighbours from this node.
   */
  public void removeAllNeighbours() {
    for (int i = neighbours.size() - 1; i >= 0; --i) removeNeighbour(neighbours.get(i));
  }
}
