package com.koenji.firetime.scenes;

import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.component.render.CameraOffset;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.graph.pathfinding.nodes.INode;
import com.koenji.ecs.graph.pathfinding.nodes.Node;
import com.koenji.ecs.scene.Scene;
import com.koenji.ecs.service.Locator;
import com.koenji.ecs.system.physics.CircleCollider;
import com.koenji.ecs.system.physics.ConvexCollider;
import com.koenji.ecs.system.physics.LinearMotion;
import com.koenji.ecs.system.render.BasicRenderer;
import com.koenji.ecs.wrappers.IGraphicsContext;
import com.koenji.firetime.entities.Bullet;
import com.koenji.firetime.entities.Guard;
import com.koenji.firetime.entities.Player;
import com.koenji.firetime.events.EmitBulletEvent;
import com.koenji.firetime.level.LevelObject;
import processing.core.PVector;

import java.util.*;

public class GamePrototype extends Scene {

  private IGraphicsContext gc;
  public List<INode> nodes;

  private boolean showPaths = false;

  private Player p;

  @Override
  public void added() {
    super.added();
    //
    gc = Locator.get(IGraphicsContext.class);
    nodes = new ArrayList<>();
    // Construct the nodes!!!
    Node a = new Node(64, 64);
    Node b = new Node(600, 64);
    Node c = new Node(1136, 64);
    Node d = new Node(300, 350);
    Node e = new Node(1000, 500);
    Node f = new Node(600, 500);
    Node g = new Node(64, 736);
    Node h = new Node(600, 736);
    Node i = new Node(1000, 736);
    Node j = new Node(1136, 736);
    // Add nodes to list
    nodes.add(a);
    nodes.add(b);
    nodes.add(c);
    nodes.add(d);
    nodes.add(e);
    nodes.add(f);
    nodes.add(g);
    nodes.add(h);
    nodes.add(i);
    nodes.add(j);
    // CONNECT THE NODES
    a.addNeighbour(b);
    a.addNeighbour(d);
    a.addNeighbour(g);
    b.addNeighbour(c);
    b.addNeighbour(d);
    b.addNeighbour(e);
    c.addNeighbour(e);
    c.addNeighbour(i);
    c.addNeighbour(j);
    d.addNeighbour(g);
    d.addNeighbour(f);
    d.addNeighbour(h);
    e.addNeighbour(i);
    e.addNeighbour(j);
    f.addNeighbour(g);
    f.addNeighbour(h);
    g.addNeighbour(h);
    i.addNeighbour(j);
    //
    p = new Player(new PVector(0, 0));
    Guard guard = new Guard(Arrays.asList(i, e, b, c, j));
    // Add walls

    add(EntityObject.create(new Background(0xFF002299)));

    add(p);
    add(guard);

    add(new LinearMotion());
    add(new CircleCollider());
    add(new ConvexCollider());
    add(new BasicRenderer());

    // Serialize level
    LevelObject lo = new LevelObject();
    List<Node> nodes = Arrays.asList(a, b, c, d, e, f, g, h, i, j);
    lo.addNodes(nodes);
    List<String> connections = new ArrayList<>();
    for (INode n : nodes) {
      List<INode> ns = n.getNeighbours();
      int in = nodes.indexOf(n);
      //
      for (INode n2 : ns) {
        int in2 = nodes.indexOf(n2);
        int min = Math.min(in, in2);
        int max = Math.max(in, in2);
        String s = Integer.toString(min) + '-' + Integer.toString(max);
        if (connections.indexOf(s) == -1) {
          connections.add(s);
        }
      }
    }
    lo.addConnections(connections);
    //


    // Event listeners
    addEventHandler(EmitBulletEvent.EMIT_BULLET, this::fireBullet);
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    // Draw le nodes
    CameraOffset co = p.getComponent(CameraOffset.class);
    gc.pushMatrix();
    gc.translate(-co.offset.x, -co.offset.y);
    if (showPaths) {
      gc.stroke(0xFFFFFFFF);
      gc.strokeWeight(3);
      for (int i = 0; i < nodes.size(); ++i) {
        INode n = nodes.get(i);
        List<INode> ns = n.getNeighbours();
        for (INode n2 : ns) {
          gc.line(n.getX(), n.getY(), n2.getX(), n2.getY());
        }
      }
      for (INode n : nodes) {
        gc.fill(0xFFFF0000);
        gc.rect(n.getX() - 8, n.getY() - 8, 16, 16);
      }
    }
    //
    gc.popMatrix();
    //
    gc.fill(0xFF000000);
    gc.textSize(14);
    gc.text("Entities: " + entityCount(), 20, 20);
    gc.text("CO: " + co.offset.toString(), 20, 60);
  }

  private void fireBullet(EmitBulletEvent e) {
    Bullet b = new Bullet(e.getX(), e.getY(), e.angle());
    add(b);
  }
}
