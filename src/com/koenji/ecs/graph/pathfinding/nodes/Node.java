package com.koenji.ecs.graph.pathfinding.nodes;

import com.koenji.ecs.graph.pathfinding.nodes.INode;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Node class defines the behaviour of a node used in the system, implementing the INode interface.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.0
 */
public class Node implements INode {

  // int values of position x,y
  private int x;
  private int y;

  // List of node neighbours
  private transient List<INode> neighbours;

  public Node() {
    this(0, 0);
  }

  /**
   * Constructor: set the x,y of the node and create the neighbours List
   * @param x
   * @param y
   */
  public Node(int x, int y) {
    this.x = x;
    this.y = y;
    this.neighbours = new ArrayList<>();
  }

  /**
   * Method: GETTER for the x value
   * @return - int of x
   */
  public int getX() {
    return x;
  }

  /**
   * Method: GETTER for the y value
   * @return - int of y
   */
  public int getY() {
    return y;
  }

  /**
   * Method: SETTER for the nodes position
   * @param position - PVector class of position
   */
  public void set(PVector position) {
    this.x = (int) position.x;
    this.y = (int) position.y;
  }

  /**
   * Method: returns a List of neighbouring Nodes
   * @return - List of neighbours
   */
  public List<INode> getNeighbours() {
    return neighbours;
  }

  /**
   * Method: adds a node to the neighbours List and tries to add itself to neighbour
   * @param node - INode neighbour
   */
  public void addNeighbour(INode node) {
    if (neighbours.contains(node)) return;
    neighbours.add(node);
    node.addNeighbour(this);
  }

  /**
   * Method: removes a node from neighbours List and tries to remove itself from neighbour
   * @param node
   */
  public void removeNeighbour(INode node) {
    if (!neighbours.contains(node)) return;
    neighbours.remove(node);
    node.removeNeighbour(this);
  }

  /**
   * Method: calls the removeNeighbour method on all neighbours
   */
  public void removeAllNeighbours() {
    for (int i = neighbours.size() - 1; i >= 0; --i) removeNeighbour(neighbours.get(i));
  }
}
