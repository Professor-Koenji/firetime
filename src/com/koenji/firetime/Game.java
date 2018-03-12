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

  INode start;
  INode target;

  public Game() {
    // Set initial resolution, fps & title
    super(800, 600, 60, "Firetime", 0xFF000000);
  }

  @Override
  public void init() {
    nodes = new ArrayList<>();
    for (int y = 0; y < 18; ++y) {
      for (int x = 0; x < 24; ++x) {
        INode n = new Node(32 + x * 32, 32 + y * 32);
        nodes.add(n);
        if (y > 0) n.addNeighbour(nodes.get((y - 1) * 24 + x));
        if (x > 0) n.addNeighbour(nodes.get(y * 24 + x - 1));
      }
    }

    for (int i = 0; i < 150; ++i) {
      int index = (int) random(0, nodes.size());
      INode node = nodes.remove(index);
      node.removeAllNeighbours();
    }

    start = nodes.get(0);
    target = nodes.get(38);
    Pathfinder pf = new Pathfinder(start);
    path = pf.findPath(target);

    addEventHandler(InputEvents.MOUSE_MOVED, event -> {
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
      path = pf.findPath(target);
    });
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    //
    for (INode n : nodes) {
      List<INode> ns = n.getNeighbours();
      for (INode n2 : ns) {
        stroke(path != null && path.contains(n) && path.contains(n2) ? 0xFF3333FF : 0x10FFFFFF);
        strokeWeight(3);
        line(n.getX(), n.getY(), n2.getX(), n2.getY());
      }
      fill(path != null && path.contains(n) ? 0x603333FF : 0x30FFFFFF);
      stroke(path != null && path.contains(n) ? 0xFF3333FF : 0x30FFFFFF);
      if (n == start) {
        fill(0xFF66FF66);
        stroke(0xFF66FF66);
      } else if (n == target) {
        fill(0xFFFF6666);
        stroke(0xFFFF6666);
      }
      arc(n.getX(), n.getY(), 12, 12, 0, (float) Math.PI * 2f);
    }
  }
}
