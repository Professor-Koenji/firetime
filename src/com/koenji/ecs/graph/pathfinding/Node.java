package com.koenji.ecs.graph.pathfinding;

import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Node implements INode {

  private int x;
  private int y;
  private List<INode> neighbours;

  public Node(int x, int y) {
    this.x = x;
    this.y = y;
    this.neighbours = new ArrayList<>();
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void set(PVector position) {
    this.x = (int) position.x;
    this.y = (int) position.y;
  }

  public List<INode> getNeighbours() {
    return neighbours;
  }

  public void addNeighbour(INode node) {
    if (neighbours.contains(node)) return;
    neighbours.add(node);
    node.addNeighbour(this);
  }

  public void removeNeighbour(INode node) {
    if (!neighbours.contains(node)) return;
    neighbours.remove(node);
    node.removeNeighbour(this);
  }
}
