package com.koenji.firetime;

import com.koenji.ecs.Core;
import com.koenji.ecs.debug.DebugScene;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.graph.pathfinding.INode;
import com.koenji.ecs.graph.pathfinding.Node;
import com.koenji.ecs.graph.pathfinding.Pathfinder;
import com.koenji.ecs.graph.tree.IQuadTree;
import com.koenji.ecs.graph.tree.QuadTree;
import com.koenji.ecs.graph.tree.Rect;
import com.koenji.ecs.scene.example.CirclePhysics;
import com.koenji.ecs.scene.example.ConvexCollisions;
import com.koenji.firetime.event.Events;
import com.koenji.firetime.event.WeaponFireEvent;
import com.koenji.firetime.scene.Menu;
import javafx.scene.shape.Circle;
import jdk.internal.util.xml.impl.Input;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Game extends Core {

  List<INode> nodes;
  List<INode> path;

  public Game() {
    // Set initial resolution, fps & title
    super(800, 600, 60, "Firetime", 0x30000000);
  }

  @Override
  public void init() {
    nodes = new ArrayList<>();
    INode root = new Node(50, 50);
    INode b = new Node(200, 45);
    INode c = new Node(400, 190);
    INode d = new Node(200, 390);
    INode e = new Node(500, 400);
    INode f = new Node(40, 240);

    root.addNeighbour(b);
    root.addNeighbour(c);
    root.addNeighbour(d);
    root.addNeighbour(f);

    b.addNeighbour(d);

    d.addNeighbour(f);

    e.addNeighbour(d);

    Pathfinder pf = new Pathfinder(e);
    path = pf.findPath(c);

    nodes.add(root);
    nodes.add(b);
    nodes.add(c);
    nodes.add(d);
    nodes.add(e);
    nodes.add(f);
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    for (INode n : nodes) {
      List<INode> ns = n.getNeighbours();
      for (INode n2 : ns) {
        stroke(path.contains(n) && path.contains(n2) ? 0xFF3333FF : 0x30FFFFFF);
        strokeWeight(4);
        line(n.getX(), n.getY(), n2.getX(), n2.getY());
      }
      fill(path.contains(n) ? 0x603333FF : 0x30FFFFFF);
      stroke(path.contains(n) ? 0xFF3333FF : 0x30FFFFFF);
      arc(n.getX(), n.getY(), 16, 16, 0, (float) Math.PI * 2f);
      text("x: " + n.getX() + ", y: " + n.getY(), n.getX() + 16, n.getY() + 16);
    }
  }
}
