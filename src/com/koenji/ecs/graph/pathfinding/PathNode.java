package com.koenji.ecs.graph.pathfinding;

public class PathNode implements IPathNode {

  private INode node;
  private IPathNode previous;
  private float g;
  private float h;

  public PathNode(INode node, INode target) {
    this.node = node;
    this.g = -1;
    this.h = Math.abs(node.getX() - target.getX()) + Math.abs(node.getY() - target.getY());
  }

  public void setG(float g) {
    this.g = g;
  }

  public float getG() {
    return g;
  }

  public float getH() {
    return h;
  }

  public float getF() {
    return g + h;
  }

  public INode getNode() {
    return node;
  }

  public void setPrevious(IPathNode previous) {
    this.previous = previous;
  }

  public IPathNode getPrevious() {
    return this.previous;
  }

}
