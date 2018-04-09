package com.koenji.firetime.scenes;

import com.koenji.ecs.component.physics.Position;
import com.koenji.ecs.component.physics.Rotation;
import com.koenji.ecs.component.render.Background;
import com.koenji.ecs.entity.EntityObject;
import com.koenji.ecs.event.InputEvents;
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
import com.koenji.firetime.entities.Wall;
import com.koenji.firetime.events.EmitBulletEvent;
import javafx.event.Event;
import javafx.event.EventType;
import jdk.internal.util.xml.impl.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GamePrototype extends Scene {

  private IGraphicsContext gc;
  public List<INode> nodes;

  private boolean showPaths = false;

  @Override
  public void added() {
    super.added();
    //
    gc = Locator.get(IGraphicsContext.class);
    nodes = new ArrayList<>();
    // Construct the nodes!!!
    INode a = new Node(64, 64);
    INode b = new Node(600, 64);
    INode c = new Node(1136, 64);
    INode d = new Node(300, 350);
    INode e = new Node(1000, 500);
    INode f = new Node(600, 500);
    INode g = new Node(64, 736);
    INode h = new Node(600, 736);
    INode i = new Node(1000, 736);
    INode j = new Node(1136, 736);
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
    Player p = new Player();
    Guard guard = new Guard(Arrays.asList(i, e, b, c, j), p.getComponent(Position.class));
    // Add walls

    add(EntityObject.create(new Background(0xFF002299)));

    add(p);
    add(guard);
    add(new Wall(0, gc.getHeight() / 2f, 32, gc.getHeight()));
    add(new Wall(gc.getWidth() / 2f, 0, gc.getWidth(), 32));
    add(new Wall(gc.getWidth() / 2f + 100, 650, 32, 300));

    Wall w = new Wall(gc.getWidth() / 2, gc.getHeight() / 2, 300, 32);
    w.addComponent(new Rotation((float) Math.PI / 4f));
    add(w);

    add(new LinearMotion());
    add(new CircleCollider());
    add(new ConvexCollider());
    add(new BasicRenderer());

    // Event listeners
    addEventHandler(EmitBulletEvent.EMIT_BULLET, this::fireBullet);
    addEventHandler(InputEvents.KEY_PRESSED, event -> {
      if (event.keyCode() == 32) {
        showPaths = !showPaths;
      } else if (event.keyCode() == 77) {
        int state = guard.getState();
        if (state == Guard.PATROL_STATE) {
          guard.setState(Guard.CHASE_STATE);
        } else if (state == Guard.CHASE_STATE) {
          guard.setState(Guard.PATROL_STATE);
        }
      }
    });
  }

  @Override
  public void update(int dt) {
    super.update(dt);
    // Draw le nodes
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
    gc.fill(0xFFFFFFFF);
    gc.textSize(14);
    gc.text("Entities: " + entityCount(), 20, 20);
  }

  private void fireBullet(EmitBulletEvent e) {
    Bullet b = new Bullet(e.getX(), e.getY(), e.angle());
    add(b);
  }
}
