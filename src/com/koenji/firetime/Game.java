package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.graph.pathfinding.heuristic.Heuristics;
import com.koenji.ecs.graph.pathfinding.strategy.Strategies;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.nodes.Node;
import com.koenji.ecs.graph.pathfinding.Pathfinder;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Game extends Core {

  List<INode> nodes;
  List<INode> bfsPath;
  List<INode> djkPath;
  List<INode> astPath;

  INode start;
  INode target;

  public Game() {
    // Set initial resolution, fps & title
    super(800, 600, 60, "Firetime", 0xFF000000);
  }

  @Override
  public void init() {
    int gap = 26;
    nodes = new ArrayList<>();
    for (int y = 0; y < 20; ++y) {
      for (int x = 0; x < 30; ++x) {
        INode n = new Node(gap * 3 + x * gap, gap * 3 + y * gap);
        nodes.add(n);
        if (y > 0) n.addNeighbour(nodes.get((y - 1) * 30 + x));
        if (x > 0) n.addNeighbour(nodes.get(y * 30 + x - 1));
      }
    }

    for (int i = 0; i < 150; ++i) {
      int index = (int) random(0, nodes.size());
      INode node = nodes.remove(index);
      node.removeAllNeighbours();
    }

    for (int i = 0; i < 10; ++i) {
      int index = (int) random(0, nodes.size());
      int index2 = (int) random(0, nodes.size());
      nodes.get(index).addNeighbour(nodes.get(index2));
    }

    start = nodes.get(0);
    target = nodes.get(38);
    Pathfinder pf = new Pathfinder(start);
    bfsPath = pf.findPath(target, Strategies.BFS);
    djkPath = pf.findPath(target, Strategies.Dijkstra);
    astPath = pf.findPath(target, Strategies.AStar);

    addEventHandler(InputEvents.MOUSE_PRESSED, event -> {
      // Find closest node to mouse cursor
      PVector targetPos = new PVector(target.getX(), target.getY());
      PVector mousePos = event.position();
      float minDist = PVector.dist(mousePos, targetPos);
      for (INode node : nodes) {
        float dist = PVector.dist(mousePos, new PVector(node.getX(), node.getY()));
        if (dist < minDist) {
          target = node;
          targetPos.set(target.getX(), target.getY());
          minDist = dist;
        }
      }
      bfsPath = pf.findPath(target, Strategies.BFS);
      djkPath = pf.findPath(target, Strategies.Dijkstra);
      astPath = pf.findPath(target, Strategies.AStar);

      // Path lengths
      float bfsLength = 0;
      for (int i = 0; i < bfsPath.size() - 1; ++i) {
        INode a = bfsPath.get(i);
        INode b = bfsPath.get(i + 1);
        bfsLength += Heuristics.euclidean.distance(a, b);
      }

      float djkLength = 0;
      for (int i = 0; i < djkPath.size() - 1; ++i) {
        INode a = djkPath.get(i);
        INode b = djkPath.get(i + 1);
        djkLength += Heuristics.euclidean.distance(a, b);
      }

      float astLength = 0;
      for (int i = 0; i < astPath.size() - 1; ++i) {
        INode a = astPath.get(i);
        INode b = astPath.get(i + 1);
        astLength += Heuristics.euclidean.distance(a, b);
      }

      System.out.println("BFS: " + bfsLength);
      System.out.println("DJK: " + djkLength);
      System.out.println("AST: " + astLength);
    });
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    strokeWeight(3);
    for (INode n : nodes) {
      List<INode> ns = n.getNeighbours();
      stroke(0x30999999);
      for (INode n2 : ns) {
        line(n.getX(), n.getY(), n2.getX(), n2.getY());
      }
    }

    strokeWeight(8);

    // BFS
    stroke(0x55FF3333);
    for (int i = 0; i < bfsPath.size() - 1; ++i) {
      INode a = bfsPath.get(i);
      INode b = bfsPath.get(i + 1);
      line(a.getX(), a.getY(), b.getX(), b.getY());
    }

    // DJK
    stroke(0x5533FF33);
    for (int i = 0; i < djkPath.size() - 1; ++i) {
      INode a = djkPath.get(i);
      INode b = djkPath.get(i + 1);
      line(a.getX(), a.getY(), b.getX(), b.getY());
    }

    // AST
    stroke(0x553333FF);
    for (int i = 0; i < astPath.size() - 1; ++i) {
      INode a = astPath.get(i);
      INode b = astPath.get(i + 1);
      line(a.getX(), a.getY(), b.getX(), b.getY());
    }

    fill(0xFFFF3333);
    text("BFS: " + bfsPath.size(), 10, 10);
    fill(0xFF33FF33);
    text("DJK: " + djkPath.size(), 10, 25);
    fill(0xFF3333FF);
    text("A* : " + astPath.size(), 10, 40);
  }
}
