package com.koenji.ecs.graph.pathfinding.nodes;

import com.koenji.ecs.graph.pathfinding.heuristic.IHeuristic;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.nodes.IPathNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathNode implements IPathNode {

  private INode node;
  private IPathNode previous;
  private float g;
  private float h;

  public PathNode(INode node) {
    this.node = node;
    this.g = Float.MAX_VALUE;
    this.h = 0;
  }

  public PathNode(INode node, INode target, IHeuristic heuristic) {
    this.node = node;
    this.g = Float.MAX_VALUE;
    this.h = heuristic.distance(node, target);
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

  public List<INode> createPath() {
    // Create a list of the nodes to follow
    List<INode> path = new ArrayList<>();
    IPathNode end = this;
    // Add this node to the path
    path.add(end.getNode());
    // While there are still more nodes to path through
    while (end.getPrevious() != null) {
      // End is the previous PathNode
      end = end.getPrevious();
      // Add this node to the path
      path.add(end.getNode());
    }
    // Flip the path around, so it goes from start -> target
    Collections.reverse(path);
    return path;
  }

}
