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
    super(800, 600, 60, "Firetime", 0x30000000);
  }

  @Override
  public void init() {
    nodes = new ArrayList<>();
    randomSeed(millis());

    for (int i = 0; i < 50; ++i) {
      INode n = new Node((int) random(0, 800), (int) random(0, 600));
      nodes.add(n);
      //
      for (INode n2 : nodes) {
        if (random(1,10) > 7) n.addNeighbour(n2);
      }
    }

    start = nodes.get(0);
    target = new Node(100, 100);
    target.addNeighbour(nodes.get(10));
    nodes.add(target);
    Pathfinder pf = new Pathfinder(start);
    path = pf.findPath(target);

    addEventHandler(InputEvents.MOUSE_MOVED, event -> {
      start.set(event.position());
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
        stroke(path != null && path.contains(n) && path.contains(n2) ? 0xFF3333FF : 0x01FFFFFF);
        strokeWeight(1);
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
      arc(n.getX(), n.getY(), 16, 16, 0, (float) Math.PI * 2f);
//      text("x: " + n.getX() + ", y: " + n.getY(), n.getX() + 16, n.getY() + 16);
    }
  }
}
