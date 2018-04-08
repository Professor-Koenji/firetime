package com.koenji.ecs.scene.example;

import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.event.InputEvents;
import com.koenji.ecs.graph.pathfinding.Pathfinder;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.nodes.Node;
import com.koenji.ecs.graph.pathfinding.strategy.Strategies;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.ecs.wrappers.IRandom;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * A demonstration of pathfinding using the Pathfinding functionality.
 *
 * @author Brad Davies &amp; Chris Williams
 * @version 1.1
 */
public class Pathfinding extends Scene {

  private List<INode> nodes;
  private IGraphicsContext gc;

  private int countX;
  private int countY;
  private float sliceX;
  private float sliceY;
  private boolean randomAngles;

  private INode start;
  private INode target;

  private List<INode> bfsPath;
  private List<INode> astPath;

  public Pathfinding() {
    this(false);
  }

  public Pathfinding(boolean randomAngles) {
    this(randomAngles, false);
  }

  public Pathfinding(boolean randomAngles, boolean big) {
    this.countX = 21 * (big ? 2 : 1);
    this.countY = 14 * (big ? 2 : 1);
    this.randomAngles = randomAngles;
  }

  @Override
  public void added() {
    super.added();
    //
    nodes = new ArrayList<>();
    gc = Locator.get(IGraphicsContext.class);

    // Add background
    add(EntityObject.create(
      new Background(0xFFF8FBFE)
    ));
    add(new BasicRenderer());

    sliceX = gc.getWidth() / countX;
    sliceY = gc.getHeight() / countY;

    // Loops!
    for (int y = 0; y < countY; ++y) {
      for (int x = 0; x < countX; ++x) {
        INode node = new Node(x, y);
        nodes.add(node);
        if (x > 0) {
          // Add left node
          nodes.get(y * countX + x - 1).addNeighbour(node);
        }
        if (y > 0) {
          // Add top node
          nodes.get((y - 1) * countX + x).addNeighbour(node);
        }
      }
    }

    IRandom rng = Locator.get(IRandom.class);
    // Ok, now randomly remove some nodes
    for (int i = 0; i < countY / 2 * countX / 2; ++i) {
      int index = (int) Math.floor(rng.random(0, nodes.size() - 1));
      INode n = nodes.get(index);
      n.removeAllNeighbours();
      nodes.remove(n);
    }

    // Ok, now make some random connections
    if (randomAngles) {
      for (int i = 0; i < countY; ++i) {
        int a = (int) Math.floor(rng.random(0, nodes.size() - 1));
        int b = (int) Math.floor(rng.random(0, nodes.size() - 1));
        if (a == b) continue;
        nodes.get(a).addNeighbour(nodes.get(b));
      }
    }

    // Pick a random start
    start = nodes.get((int) Math.floor(rng.random(0, nodes.size() - 1)));
    target = nodes.get((int) Math.floor(rng.random(0, nodes.size() - 1)));

    addEventHandler(InputEvents.MOUSE_PRESSED, e -> {
      INode nearest = null;
      float dist = 9999;
      for (INode n : nodes) {
        PVector pos = new PVector(n.getX() * sliceX + sliceX * .5f, n.getY() * sliceY + sliceY * .5f);
        float d = PVector.dist(pos, e.position());
        if (nearest == null || d < dist) {
          nearest = n;
          dist = d;
        }
      }
      //
      switch (e.button()) {
        case 37: // left
          start = nearest;
          calculate();
          break;
        case 39: // Right
          target = nearest;
          calculate();
          break;
      }
    });

    calculate();
  }

  private void calculate() {
    Pathfinder pathfinder = new Pathfinder(start);
    bfsPath = pathfinder.findPath(target, Strategies.BFS);
    astPath = pathfinder.findPath(target, Strategies.AStar_Euclidean);

    System.out.println("BFS Length: " + pathLength(bfsPath));
    System.out.println(" A* Length: " + pathLength(astPath));
    System.out.println(" BFS Nodes: " + (bfsPath != null ? bfsPath.size() : -1));
    System.out.println("  A* Nodes: " + (astPath != null ? astPath.size() : -1));
    System.out.println("----------------------");
  }

  private float pathLength(List<INode> path) {
    if (path == null) return -1;
    float len = 0;
    for (int i = 0; i < path.size() - 1; ++i) {
      INode a = path.get(i);
      INode b = path.get(i + 1);
      float dist = PVector.dist(new PVector(a.getX(), a.getY()), new PVector(b.getX(), b.getY()));
      len += dist;
    }
    return len;
  }

  private void drawPath(List<INode> path) {
    if (path == null) return;
    for (int i = 0; i < path.size() - 1; ++i) {
      INode a = path.get(i);
      INode b = path.get(i + 1);
      gc.line(a.getX() * sliceX, a.getY() * sliceY, b.getX() * sliceX, b.getY() * sliceY);
    }
  }

  @Override
  public void update(int dt) {
    super.update(dt);

    gc.pushMatrix();
    gc.translate(sliceX * .5f, sliceY * .5f);

    // Draw lines
    gc.noFill();
    gc.stroke(0xFF000000);
    gc.strokeWeight(2);
    for (INode n : nodes) {
      List<INode> ns = n.getNeighbours();
      for (INode n2 : ns) {
        gc.line(n.getX() * sliceX, n.getY() * sliceY, n2.getX() * sliceX, n2.getY() * sliceY);
      }
    }
    // Draw dijk lines
    gc.noFill();
    gc.stroke(0xFF444488);
    gc.strokeWeight(16);
    drawPath(bfsPath);

    // Draw AStar lines
    gc.noFill();
    gc.stroke(0xFF228822);
    gc.strokeWeight(16);
    drawPath(astPath);

    // Draw actual nodes
    gc.stroke(0xFF000000);
    for (INode n : nodes) {
      boolean big = false;
      gc.fill(0xFFFFFFFF);
      if (bfsPath != null && bfsPath.contains(n)) {
        big = true;
        gc.fill(0xFFAAAAFF);
      }
      if (astPath != null && astPath.contains(n)) {
        big = true;
        gc.fill(0xFF99FF99);
      }

      //
      int size = big ? 32 : 16;
      gc.stroke(0xFF000000);
      gc.strokeWeight(2);
      if (n == start || n == target) {
        gc.rect(n.getX() * sliceX - size * .5f, n.getY() * sliceY - size * .5f, size, size);
      } else {
        gc.arc(n.getX() * sliceX, n.getY() * sliceY, size, size, 0, gc.TWO_PI);
      }
    }
    gc.popMatrix();
  }
}
